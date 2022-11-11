package com.demo.util;
import java.io.*;
import java.util.Properties;
public class Proper {
    private static String Filedata = "./contract.properties";
    private static Properties Getproper() {   //获取proper配置文件信息
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(Filedata);
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                System.out.println("文件关闭失败");
            }
        }
        return properties;
    }

    static void setproper(String Key, String value) {//输入一个配置文件信息
        Properties properties = Getproper();
        properties.setProperty(Key, value);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(Filedata);
            properties.store(outputStream, "注释");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static String getvalue(String Key) {
        Properties properties = Getproper();
        String value = properties.getProperty(Key);
        return value;
    }
}