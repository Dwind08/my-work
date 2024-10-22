package com.dwind.mywork.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameController {
    @Value("空山")
    String name;

    @Value("${mail.send:abba}")
    String sending;

    {
        yourMethod();
    }

    String yourMethod() {
        System.out.println(name);
        return name;
    }

    @GetMapping("/status")
    String yourStatus(){
        System.out.println("准备输出");
        System.out.println(sending);
        System.out.println("&&&&&&");
        System.out.println(name);

        return "success";
    }


}
