package com.Abhinandan.Ecommerce.Filters;

import com.Abhinandan.Ecommerce.Utils.JwtUtility;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        if (requestURI.equals("/user/register") || requestURI.equals("/user/login")) {
            filterChain.doFilter(request, response);
        } else {
            try {
                final String jwtToken = getTokenFromRequest(request);
                try {
                    String email = jwtUtility.getUsernameFromToken(jwtToken);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                    if (StringUtils.hasText(email) && jwtUtility.validateToken(jwtToken, userDetails)) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                        );
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                } catch (IllegalArgumentException e) {
                    logger.info("Illegal Argument while fetching the username !!");
                } catch (ExpiredJwtException e) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    logger.info("Given jwt token is expired !!");
                } catch (MalformedJwtException e) {
                    logger.info("Some changes has done in token !! Invalid Token");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                    filterChain.doFilter(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && StringUtils.startsWithIgnoreCase(token, "Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
}
