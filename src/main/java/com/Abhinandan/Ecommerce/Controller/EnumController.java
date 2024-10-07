package com.Abhinandan.Ecommerce.Controller;

import com.Abhinandan.Ecommerce.Enum.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enums")
public class EnumController {

    @GetMapping("/categoryTypes")
    public CategoryType[] categoryTypes(){
        return CategoryType.values();
    }

    @GetMapping("/orderStatuses")
    public orderStatus[] orderStatuses(){
        return orderStatus.values();
    }
}
