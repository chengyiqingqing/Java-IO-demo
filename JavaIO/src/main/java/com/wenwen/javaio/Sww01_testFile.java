package com.wenwen.javaio;

import java.io.File;
import java.io.IOException;

/**
 * Created by sww on 2018/4/10.
 * File类的操作：（F:\testFile\helloworld.txt）;
 * test1.名字和路径的操作；
 *      getName();  //获取文件名； -----------    helloworld.txt
 *      getPath();  //默认为获取绝对路径；----------   F:\testFile\helloworld.txt
 *      getAbsolutePath();  //获取绝对路径；----------  F:\testFile\helloworld.txt
 *      getParent()   //获取所在目录；----------- F:\testFile
 *          getParentFile获取父目录,返回值为File类型；
 *      renameTo(File newName);
 *
 * test2.是否存在？可读？可写？是否是文件？是否是目录？最后修改时间 ？长度？
 *      exists();
 *      canWrite();
 *      canRead();
 *      isFile();
 *      isDirectory();
 *      lastModified();
 *      length();
 *
 * test3.操作一个文件？
 *      createNewFile();  跟“文件”操作相关
 *      delete();        以下跟“文件夹”操作相关；
 *      mkDir();  创建一个目录；上层文件存在；
 *      mkDirs(); 创建一个目录；上层文件不存在，连上层文件一起创建；
 *      list();   返回的是字符串列表；
 *      listFiles();    返回的是文件File对象的列表；
 */

public class Sww01_testFile {

    public static void main(String[] args) throws IOException {
//        test1();
//        test2();
        test3();
    }

    public static void test1(){
        String path="F:\\testFile\\helloworld.txt";
        File file=new File(path);
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getParent());
        //文件重命名
        String path2="F:\\testFile\\helloworldsss.txt";
        File file2=new File(path2);
        boolean it= file.renameTo(file2);//将file重命名为file2的名字；并移动该文件夹；
        System.out.println(it);
    }

    /**
     * 是否存在？可读？可写？是否是文件？是否是目录？最后修改时间 ？长度？
     *      exists();
     *      canWrite();
     *      canRead();
     *      isFile();
     *      isDirectory();
     *      lastModified();
     *      length();
     */
    public static void test2(){
        String path="F:\\testFile\\helloworld.txt";
        File file=new File(path);
        System.out.println(file.exists());
        System.out.println(file.canWrite());
        System.out.println(file.canRead());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        System.out.println(file.lastModified());
        System.out.println(file.length());
    }

    public static void test3() throws IOException {
        String path = "F:/testFile/helloworld.txt";
        File file = new File(path);
//        System.out.println(file.delete());

        //1.创建新的文件；createNewFile
        if (!file.exists()){
            boolean b= false;
            b = file.createNewFile();
//            System.out.println(b);
        }
        //2.创建新的文件夹即目录：mkdirs();
        File file2=new File("F:/testFile/file2");
        if ((!file2.exists())){
            boolean b=file.mkdirs();
            System.out.println(b);
        }

        //3.遍历目录下的所有文件获取文件名字：list();
        File file3=new File("F:\\code_am\\amberweather");
        String[] str=file3.list();//拿到的是所有子文件的名字；
        for(int i=0;i<str.length;i++){
//            System.out.println(""+str[i]);
        }

        //4.遍历所有的文件；
        File file4=new File("F:\\code_am\\amberweather");
        File[] files=file3.listFiles();//拿到的是所有子文件的名字；
        for(int i=0;i<str.length;i++){
            System.out.println(""+files[i].getName());
        }
    }

}
