package com.dwind.mywork.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class TextReader {
    public static String calculatePi = "src/main/resources/90/calculatePi.txt";

    public static String calRelPi = "src/main/resources/90/calRelPi.txt";


    public static int fileCharContrast(int length){

        File relFile = new File(calRelPi);
        File calFile = new File(calculatePi);
        char[] charSet = new char[length];
        char[] calCharSet = new char[length];
        int rightLength = -2;
        try {

            FileReader relFileReader = new FileReader(relFile);
            FileReader calFileReader = new FileReader(calFile);
            int read = 1;
            int readCal = 1;
            for ( ; read > 0 && readCal > 0 ; ) {
                read = relFileReader.read(charSet);
                readCal = calFileReader.read(calCharSet);

                String rel = new String(charSet);
                String calPi = new String(calCharSet);
                if (rel.equals(calPi)) {
                    rightLength = rightLength + charSet.length;
                    continue;
                }
                for (int j = 0; j < charSet.length; j++) {
                    if (charSet[j] != calCharSet[j]) {
                        rightLength = rightLength + j;
                        break;
                    }
                }
                break;
            }
            return rightLength;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void piWriter(BigDecimal pi, int circle) {
        String piFilePath = "src/main/resources/test/" + DateUtil.format(new Date(), cn.hutool.core.date.DatePattern.NORM_DATE_PATTERN);
        File file = new File(piFilePath);
        FileWriter fileWriter = new FileWriter(file, StandardCharsets.UTF_8);
        fileWriter.append("第" + circle + "次迭代输出PI的计算结果，精度：" + pi.scale() +"\n" );
        fileWriter.append(pi+ "\n");
    }

    public static void logWriter(String content) {
        String piFilePath = "src/main/resources/test/log" + DateUtil.format(new Date(), cn.hutool.core.date.DatePattern.NORM_DATE_PATTERN);
        File file = new File(piFilePath);
        FileWriter fileWriter = new FileWriter(file, StandardCharsets.UTF_8);
        fileWriter.append(content + "           " + DateUtil.format(new Date(), cn.hutool.core.date.DatePattern.NORM_DATETIME_MS_PATTERN) + "\n");
    }
}
