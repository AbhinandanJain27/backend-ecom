package com.Abhinandan.Ecommerce.Controller;

import com.Abhinandan.Ecommerce.Enum.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enums")
public class EnumController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/categoryTypes")
    public CategoryType[] categoryTypes(){
        return CategoryType.values();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/orderStatuses")
    public orderStatus[] orderStatuses(){
        return orderStatus.values();
    }
}
