package solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  
  /**
   * read
   * read[  addr=0x17 , mask=0xff , val=0x7],
   * read_his[  addr=0xff,mask=0xff,val=0x1],
   * read[  addr=0xf0,mask=0xff,val=0x80]
   *
   * @param args
   */
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String target = in.next();
    String str = in.next();
    boolean get = false;
    String[] registers = str.split(",");
    List<Integer> res = new ArrayList<Integer>();
    for (int i = 0; i < registers.length; i += 3) {
      String[] temp = registers[i].split("=");
      int k;
      for (k = 0; k < temp[0].length(); k++) {
        if (temp[0].charAt(k) == '[') {
          break;
        }
      }
      String name = temp[0].substring(0, k);
      if (name.equals(target)) {
        res.add(i);
      }
    }
    if (res.size() == 0) {
      System.out.println("FAIL");
    } else {
      for (int i = 0; i < res.size(); i++) {
        String s1 = registers[res.get(i)];
        String s2 = registers[res.get(i) + 1];
        String s3 = registers[res.get(i) + 2];
        String[] part1 = s1.split("=");
        int k;
        for (k = part1[0].length() - 1; k >= 0; k--) {
          if (part1[0].charAt(k) == '[') {
            break;
          }
        }
        String title1 = part1[0].substring(k + 1, part1[0].length());
        String[] part2 = s2.split("=");
        String[] part3 = s3.split("=");
//        System.out.println(title1+" "+part2[0]+" "+part3[0]);
        if (title1.equals("addr") && part2[0].equals("mask") && part3[0].equals("val")) {
          String a = part1[1];
          String b = part2[1];
          String c = part3[1].substring(0, part3[1].length() - 1);
          if ((a.substring(0,2).equals("0X")||a.substring(0,2).equals("0x"))&&
            (b.substring(0,2).equals("0X")||b.substring(0,2).equals("0x"))&&
            (c.substring(0,2).equals("0X")||c.substring(0,2).equals("0x"))){
            get = true;
            System.out.println(a + " " + b + " " + c);
          }
        }
      }
      if (!get) {
        System.out.println("FAIL");
      }
    }
  }
}
