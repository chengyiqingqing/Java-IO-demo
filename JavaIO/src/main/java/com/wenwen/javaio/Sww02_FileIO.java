package com.wenwen.javaio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by sww on 2018/4/10.
 * 1.流的分类：
 *      数据流向：输入流，输出流；
 *      按照处理数据单位不同：字节流，字符流（处理文本文件）；
 *      按照角色不同： 节点流（直接作用于文件）； 处理流；
 *
 * 2.IO的体系：
 *      抽象基类        节点流（直接作用域文件的“文件流”）   缓冲流（处理流的一种）
 *      InputStream     FileInputStream                         BufferedInputStream
 *      OutputStream    FileOutputStream                        BufferedOutputStream
 *      Reader          FileReader                              BufferedReader
 *      Writer          FileWriter                              BufferedWriter
 *
 */

public class Sww02_FileIO {

    static String path = "F:/testFile/helloworld.txt";
    static String out_path = "F:/testFile/helloworldoutput.txt";

//    static String path = "F:/testFile/wenwens.jpg";
//    static String out_path = "F:/testFile/hellowordoutput.jpg";

    public static void main(String[] args)throws IOException{
        testFileInputStream();
//        testFileOutputStream();

        //看一下总共使用了多少时间：
//        long start=System.currentTimeMillis();
//        testFileInOrOutputStream();
//        long end=System.currentTimeMillis();
//        System.out.println(end-start);

//        testFileReader();
        testFileReaderAndWriter();
    }

    public static void testFileInputStream() throws IOException {

        File file=new File(path);
        FileInputStream fis=new FileInputStream(file);
        //1.使用read()读取一个字节即为char类型；
        /*int b=fis.read();//read()为读取一个字节，读到文件结尾则完备；
        while (b!=-1){
            System.out.println(b+" "+(char)b);//它读的会是一个整型--》char需要转换成char.
            b=fis.read();
        }
        fis.close();*/
        //进行使用try-catch;因为try-catch能够捕获异常；

        //2.使用read(byte[] byteArr); 一次读取若干个字节；一定要是2的倍数；不然一个Str是两个字节，拆开就傻了；
        byte[] bytes=new byte[256];
        int len=-1;
        while ((len=fis.read(bytes))!=-1){
            //长度不等于-1；这就很nice了；
            /*for (int i=0;i<len;i++){//写成bytes.length; abcde ghcde;
                System.out.println((char)bytes[i]);
            }*/
            String str=new String(bytes,0,len);  //有时候需要转码，就使用Utf-8就可以了；
            System.out.println(str);
        }
        fis.close();

    }

    public static void testFileOutputStream(){
        //1.创建一个File对象，表明数据要写入哪个文件中； new File没有该文件，它会创建该文件；
        File file=new File(out_path);
        //2.创建一个FileOutputStream，表明通过节点输出流FileOutputStream，将文件写入File对象中；
        FileOutputStream fos=null;
        try {
            fos=new FileOutputStream(file,true);//这里true是追加；false是不追加；
            //write()方法里只能传递字节；String.getBytes();
            fos.write(new String("/n sww,邵文文 sss ").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //3.关闭流；
            try {
                if (fos!=null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 文件的复制与拷贝就是使用这种方式！！！可以可以；
     */
    public static void testFileInOrOutputStream(){
        File readfile=new File(path);
        File writefile=new File(out_path);
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            fis=new FileInputStream(readfile);
            fos=new FileOutputStream(writefile);
            byte[] bytes=new byte[20];
            int len=0;
            while ((len=fis.read(bytes))!=-1){
                fos.write(bytes,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用FileReader,FileWriter只能处理字符文件。
     * 若是处理“图片，音频，视频，那么就只能使用--> 字节流了 。”
     */
    public static void testFileReader(){
        File file=new File(path);
        FileReader fr= null;
        try {
            fr = new FileReader(file);  //因为它读的char类型。所以这个是难以成立的。
            char[] chars=new char[24];
            int len=0;
            while ((len=fr.read(chars))!=-1){
                String str=new String(chars,0,len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void testFileWriter(){

    }

    public static void testFileReaderAndWriter(){
        File file1=new File(path);
        File file2=new File(out_path);
        FileReader fileReader=null;
        FileWriter fileWriter=null;
        try {
            fileReader=new FileReader(file1);
            fileWriter=new FileWriter(file2,true);
            char[] chars=new char[256];
            int len=0;
            while ((len=fileReader.read(chars))!=-1){
//                String str=new String(chars,0,len); 直接读char数组也是可以的。
                fileWriter.write(chars,0,len);
            }
            fileWriter.write("wenwenwenwen文文文文");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileReader.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
