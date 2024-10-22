package com.dwind.mywork;

import com.dwind.mywork.service.BookContent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.redisson.api.SortOrder;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class MyWorkApplicationTests {


    @Test
    void contextLoads() {

        String prefix = "data:application/picture.png;base64";

        Pattern p = Pattern.compile("data:application/(.*);base64");
        Matcher m = p.matcher(prefix);
        boolean result = m.find();
        String fiileName = "";
        if (result) {
            fiileName = m.group(1);
            System.out.println(fiileName);
        } else {
            System.out.println("有问题");
        }
    }

    @Test
    void matchTest(){
        String text = "Java编程123，正则表达式345";
        Pattern pattern = Pattern.compile("(d+)"); // 匹配一个或多个数字
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String group = matcher.group();
            matcher.group(1);
            System.out.println("找到数字: " + matcher.group()); // 输出最近的匹配结果
        }
    }


    @Test
    void orderTest(){
        SortOrder order;
        String string = SortOrder.ASC.name();


        System.out.println(string);

    }

    @Test
    void timeTest(){
        long time = 1701500399257L;

        Date date = new Date(time);

        System.out.println(date);

    }

}
