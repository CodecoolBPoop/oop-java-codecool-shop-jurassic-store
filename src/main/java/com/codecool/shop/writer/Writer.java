package com.codecool.shop.writer;

import java.io.File;
import java.io.FileOutputStream;

public abstract class Writer {
    public static void writeToFile(String filename,String toWrite){
        try{
            FileOutputStream fout = new FileOutputStream("/Desktop/log.txt");
            byte[] text = toWrite.getBytes();
            fout.write(text);
            fout.flush();
            fout.close();
            System.out.println("order logged");
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
}
