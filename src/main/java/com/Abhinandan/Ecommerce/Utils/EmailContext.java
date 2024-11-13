package com.Abhinandan.Ecommerce.Utils;

public class EmailContext {
    private static final ThreadLocal<String> emailHolder = new ThreadLocal<>();

    public static void setEmail(String email) {
        emailHolder.set(email);
    }

    public static String getEmail() {
        return emailHolder.get();
    }

    public static void clear() {
        emailHolder.remove();
    }
}
