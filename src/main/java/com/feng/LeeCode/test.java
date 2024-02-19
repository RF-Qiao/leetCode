package com.feng.LeeCode;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

    // 1. 水仙花
    public static void main(String[] args) throws IOException {
        for (int i = 100; i < 1000; i++) {
            int hundreds = i / 100;
            int tens = (i - hundreds * 100) / 10;
            int u = i % 10;
            if (Math.pow(hundreds, 3) + Math.pow(tens, 3) + Math.pow(u, 3) == i) {
                System.out.println(i);
            }
        }

        // 2. 从文本文件每行中求和， 取出数字
        String fileName = "test.txt";
        int sum = 0;
        Pattern pattern = Pattern.compile("");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()){
                    sum+=Integer.parseInt(matcher.group());
                }
            }
        }catch (IOException e){
            System.out.println("12345"+ e );
        }
        System.out.println(sum);


        //3 . x 盘路径，把X文件路径输出，包括子目录

        String path = "X:\\";
        File file = new File(path);
        printFilePaths(file);
    }

    public static void printFilePaths(File file) {
        if (file.isFile()) {
            System.out.println(file.getAbsoluteFile());
        } else {
            for (File sub : file.listFiles()) {
                printFilePaths(sub);
            }
        }
    }


}
