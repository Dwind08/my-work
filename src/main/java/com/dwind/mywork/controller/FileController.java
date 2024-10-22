package com.dwind.mywork.controller;

import com.dwind.mywork.service.BookContent;
import com.dwind.mywork.util.ChineseNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private BookContent bookContent;

    @Autowired
    private NameController nameController;



    @GetMapping("/range")
    String RangeFile(int begin, int end){
        System.out.println("begin");
        bookContent.fileReader(begin,end);
        System.out.println("end");
        return "success";
    }

    @GetMapping("/char/range")
    String RangeCharFile(int begin, int end){

        System.out.println("begin");
        bookContent.fileCharReader(begin,end);
        System.out.println("end");
        return "success";
    }


    @GetMapping("/hello")
    public String HelloWorld(String price){
        System.out.println(nameController.name);
        System.out.println("&*&(7324");
        nameController.yourMethod();
        System.out.println("hello !");
        String chineseNumber = ChineseNumber.getChineseNumber(price);
        return "Hello lch! you can read " + chineseNumber;
    }
}
