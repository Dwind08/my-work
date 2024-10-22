package com.dwind.mywork.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

@Service
@Slf4j
public class BookContent {
    public static String source = "src/main/resources/2023091501jzjg.txt";

    public static String source2 = "src/main/resources/90/redBuildingInternet.txt";

    public void fileReader(int begin, int end){
        File file = new File(source2);
        byte[] bytes = new byte[1024];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int read = 1;
            int iCopy = 0;
            for (int i = 0; read > 0 ; i++) {
                read = fileInputStream.read(bytes);

                if (i < begin){
                    continue;
                }
                if (i > end){
                    break;
                }
                String s = new String(bytes);
                log.info(s);
                iCopy = i;
            }
            log.info("current code sign : " + iCopy);
            fileInputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 预防侵吞，逐字校验
     * @param begin
     * @param end
     */
    public void fileCharReader(int begin, int end){
//        String source = "src/main/resources/2023091501jzjg.txt";
        File file = new File(source2);
        char[] charSet = new char[300];
        try {
            int iCopy = 0;
            FileReader fileReader = new FileReader(file);
            int read = 1;
            for (int i = 0; read > 0 ; i++) {
                read = fileReader.read(charSet);

                if (i < begin){
                    continue;
                }
                if (i > end){
                    iCopy = i;
                    break;
                }
                String s = new String(charSet);
                log.info(s);
                iCopy = i;
            }
            fileReader.close();
            log.info("current code sign : " + iCopy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) {
        File file = new File(source2);
        byte[] bytes = new byte[1024];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int read = 1;
            for (int i = 0; read > 0 ; i++) {
                read = fileInputStream.read(bytes);

                if (i < 5){
                    continue;
                }
                if (i > 15){
                    break;
                }
                String s = new String(bytes, Charset.forName("GBK"));
//                String s = new String(bytes, StandardCharsets.UTF_8);
                log.info(s);
                System.out.print(s);
            }
            fileInputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void fileCharContrast(int begin, int end){
//        String source = "src/main/resources/2023091501jzjg.txt";
        File file = new File(source2);
        char[] charSet = new char[300];
        try {
            int iCopy = 0;
            FileReader fileReader = new FileReader(file);
            int read = 1;
            for (int i = 0; read > 0 ; i++) {
                read = fileReader.read(charSet);

                if (i < begin){
                    continue;
                }
                if (i > end){
                    iCopy = i;
                    break;
                }
                String s = new String(charSet);
                log.info(s);
                iCopy = i;
            }
            fileReader.close();
            log.info("current code sign : " + iCopy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
