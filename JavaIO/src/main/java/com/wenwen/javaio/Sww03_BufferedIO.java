package com.wenwen.javaio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by sww on 2018/4/10.
 * IO的体系：
 *      抽象基类        节点流（直接作用域文件的“文件流”）   缓冲流（处理流的一种,可以提升文件操作的效率）
 *      InputStream     FileInputStream                         BufferedInputStream
 *      OutputStream    FileOutputStream                        BufferedOutputStream
 *      Reader          FileReader                              BufferedReader
 *      Writer          FileWriter                              BufferedWriter
 */

public class Sww03_BufferedIO {

//    static String path = "F:/testFile/wenwens.jpg";
//    static String out_path = "F:/testFile/hellowordoutput.jpg";

    static String path = "F:/testFile/helloworld.txt";
    static String out_path = "F:/testFile/helloworldoutput.txt";

    public static void main(String[] args){
        bufferInputOrOutputStream();
    }

    public static void bufferInputOrOutputStream(){
        File file=new File(path);
        File file1=new File(out_path);
        BufferedInputStream bis=null;
        BufferedOutputStream bos=null;
        try {
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(file1);
            bis = new BufferedInputStream(fis);
            bos=new BufferedOutputStream(fos);
            byte[] bytes=new byte[256];
            int len=0;
            while ((len=bis.read(bytes))!=-1){
                fos.write(bytes,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void bufferReaderOrWriter(){
        File file=new File(path);
        File file1=new File(out_path);
        BufferedReader br=null;
        BufferedWriter bw=null;
        try {
            FileReader fr = new FileReader(file);
            FileWriter fw = new FileWriter(file1);
            br = new BufferedReader(fr);
            bw=new BufferedWriter(fw);
            char[] chars=new char[256];
            int len=0;
            while ((len=br.read(chars))!=-1){
                bw.write(chars,0,len);
            }
            fw.flush();
            /*String str=null;
            while((str=br.readLine())!=null){
                bw.write(str);
                bw.newLine();
            }
            bw.flush();
            //这样也是可以的*/

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
