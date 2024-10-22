package com.dwind.mywork.controller;

import com.dwind.mywork.service.BookContent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileControllerTest {


    @Autowired
    private BookContent bookContent;

    @Test
    void helloWorld() {
        bookContent.fileReader(10,100);
    }
}