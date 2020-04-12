package io;

import concurrent.excutor.entity.User;

import java.io.*;
import java.util.Scanner;

public class IOStandard {
  
  public void createNewFile(String fileName) throws IOException {
    File file = new File(fileName);
    String fName = "D:" + File.separator + fileName;
    File file1 = new File(fName);
    try {
      file.createNewFile();
      file1.createNewFile();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println(File.separator);
    System.out.println(File.pathSeparator);
    
    if (file.exists()) {
      file.delete();
    } else {
      //文件不存在
    }
    
    file.mkdir();
    
    String[] names = file.list();
    for (int i = 0; i < names.length; i++) {
      System.out.println(names[i]);
    }
    
    File[] files = file.listFiles();
    for (int i = 0; i < files.length; i++) {
      System.out.println(files[i]);
    }
    
    if (file.isDirectory()) {
      System.out.println("directory");
    }
    
    RandomAccessFile demo = new RandomAccessFile(file, "rw");
    demo.writeBytes("asdsad");
    demo.writeInt(12);
    demo.writeBoolean(true);
    demo.writeChar('A');
    demo.writeFloat(1.21f);
    demo.writeDouble(12.123);
    demo.close();
    
    OutputStream out = new FileOutputStream(file);
    String str = "你好";
    byte[] b = str.getBytes();
    out.write(b);
    out.close();
    
    InputStream in = new FileInputStream(file);
    in.read(b);
    in.close();
    System.out.println(new String(b));
    
    Writer writer = new FileWriter(file);
    writer.write("hello");
    writer.close();
    
    Reader read = new FileReader(file);
    char[] chars = new char[100];
    int count = read.read(chars);
    read.close();
    
    int temp = 0;
    count = 0;
    while ((temp = read.read()) != (-1)) {
      chars[count++] = (char) temp;
    }
    read.close();
    System.out.println(new String(chars, 0, count));
    
    if ((in != null) && (out != null)) {
      temp = 0;
      while ((temp = in.read()) != (-1)) {
        out.write(temp);
      }
    }
    in.close();
    out.close();
    
    /**
     * 关于字节流和字符流的差别
     *    - 实际上字节流在操作的时候本身是不会用到缓冲区的，是文件本身的直接操作的，
     *    可是字符流在操作的时候下后是会用到缓冲区的，是通过缓冲区来操作文件的。
     *    - 读者能够试着将上面的字节流和字符流的程序的最后一行关闭文件的代码凝视掉，然后执行程序看看。
     *    你就会发现使用字节流的话，文件里已经存在内容。可是使用字符流的时候，文件里还是没有内容的。这个时候就要刷新缓冲区。
     *
     * 使用字节流好还是字符流好呢？
     *    - 答案是字节流。首先由于硬盘上的全部文件都是以字节的形式进行传输或者保存的。包含图片等内容。
     *    可是字符仅仅是在内存中才会形成的。所以在开发中，字节流使用广泛。
     *
     */
    
    /**
     * InputStreamReader将输入的字节流转换为字符流
     * OutputStreramWriter将输出的字符流转化为字节流
     */
    //字节输出流转换成字符输出流
    Writer writer1 = new OutputStreamWriter(new FileOutputStream(file));
    writer1.close();
    
    //字节输入流转换成字符输入流
    Reader reader = new InputStreamReader(new FileInputStream(file));
    reader.close();
    
    //输入输出重定向
    try {
      System.setOut(new PrintStream(new FileOutputStream(file)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println("string in txt can see it");
    
    //输入输出重定向
    try {
      System.setIn(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    byte[] bytes = new byte[1024];
    int len = 0;
    try {
      len = System.in.read(bytes);
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("string in console can see it");
    
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    str = bufferedReader.readLine();
    
    Scanner scanner = new Scanner(System.in);
    
    Scanner scanner1 = new Scanner(file);
    str = scanner1.next();
    System.out.println(str);
    
    char[] ch = {'A', 'B', 'C'};
    DataOutputStream dataOut = null;
    dataOut = new DataOutputStream(new FileOutputStream(file));
    for (char c : ch) {
      dataOut.writeChar(c);
    }
    
    DataInputStream input = new DataInputStream(new FileInputStream(file));
    ch = new char[10];
    count = 0;
    char tempChar;
    while ((tempChar = input.readChar()) != 'C') {
      chars[count++] = tempChar;
    }
    System.out.println(tempChar);
    
    OutputStream out3 = new FileOutputStream(file);
    byte[] bytes1 = "你好".getBytes("ISO8859-1");
    out3.write(bytes1);
    out3.close();
  }
  
  public void toLower(File file) throws IOException {
    String string = "string test";
    ByteArrayInputStream in = new ByteArrayInputStream(string.getBytes());
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int temp = 0;
    while ((temp = in.read()) != (-1)) {
      char ch = (char) temp;
      out.write(Character.toLowerCase(ch));
    }
    String outStr = out.toString();
    in.close();
    out.close();
    System.out.println(outStr);
  }
  
  public void mergeStream(File file1, File file2,File file3) throws IOException {
    InputStream in1 = new FileInputStream(file1);
    InputStream in2 = new FileInputStream(file2);
    OutputStream output = new FileOutputStream(file3);
    SequenceInputStream stream = new SequenceInputStream(in1,in2);
    int temp = 0;
    while((temp=stream.read())!=(-1)){
      output.write(temp);
    }
    in1.close();
    in2.close();
    output.close();
    stream.close();
  }
  
  public void serialiTry(File file) throws IOException,ClassNotFoundException{
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
    oos.writeObject(new User());
    oos.close();
    
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
    Object user = ois.readObject();
    ois.close();
  }
  
}
