package game.test;

import org.springframework.util.StringUtils;

import java.io.*;
import java.util.*;

public class InputTest {
  
  private final static int minL = 3;
  private final static int maxL = 7;
  private final static int vertex = 30000;
  private static int count = 0;
  private static List<List<Integer>> result = new ArrayList<>();
  
  static class Edge {
    int to;
    int money;
    
    public Edge(int to, int money) {
      this.to = to;
      this.money = money;
    }
  }
  
  static class Graph {
    Map<Integer, Integer> map;
    int[] indexToId;
    List<List<Edge>> edgeFrom;
    
    public Graph() {
      this.map = new HashMap<>(vertex);
      this.indexToId = new int[vertex];
      this.edgeFrom = new ArrayList<>(vertex);
      for (int i = 0; i < vertex; i++) {
        this.edgeFrom.add(new ArrayList<>());
      }
      System.out.println("init ok");
    }
    
    public int getSize() {
      return map.size();
    }
  }
  
  public static void loadData(File file, Graph graph) {
    try {
      BufferedReader reader = new BufferedReader(
        new InputStreamReader(
          new FileInputStream(
            file)));
      String line;
      int from, to, money;
      int count = 0;
      while (!StringUtils.isEmpty(line = reader.readLine())) {
        String[] nums = line.split(",");
        from = Integer.parseInt(nums[0]);
        to = Integer.parseInt(nums[1]);
        money = Integer.parseInt(nums[2]);
        if (graph.map.get(from) == null) {
          graph.indexToId[count] = from;
          graph.map.put(from, count++);
        }
        if (graph.map.get(to) == null) {
          graph.indexToId[count] = to;
          graph.map.put(to, count++);
        }
        graph.edgeFrom.get(graph.map.get(from)).add(new Edge(graph.map.get(to), money));
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static int bytesToInt(byte[] src, int offset) {
    int value;
    value = (int) ((src[offset] & 0xFF)
      | ((src[offset + 1] & 0xFF) << 8)
      | ((src[offset + 2] & 0xFF) << 16)
      | ((src[offset + 3] & 0xFF) << 24));
    return value;
  }
  
  public static void loadData2(File file, Graph graph) {
    try {
      FileInputStream fileInputStream = new FileInputStream(file);
      // 把每次读取的内容写入到内存中，然后从内存中获取
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];
      int len = 0;
      // 只要没读完，不断的读取
      while ((len = fileInputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, len);
      }
      // 得到内存中写入的所有数据
      byte[] data = outputStream.toByteArray();
      String str = new String(data, "gbk");
      String[] lines = str.split("\r\n");
      int from, to, money;
      int count = 0;
      for (int i = 0; i < lines.length; i++) {
        String[] nums = lines[i].split(",");
        from = Integer.parseInt(nums[0]);
        to = Integer.parseInt(nums[1]);
        money = Integer.parseInt(nums[2]);
        if (graph.map.get(from) == null) {
          graph.indexToId[count] = from;
          graph.map.put(from, count++);
        }
        if (graph.map.get(to) == null) {
          graph.indexToId[count] = to;
          graph.map.put(to, count++);
        }
        graph.edgeFrom.get(graph.map.get(from)).add(new Edge(graph.map.get(to), money));
      }
      fileInputStream.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void loadData3(File file, Graph graph) {
    try {
      FileInputStream fileInputStream = new FileInputStream(file);
      // 把每次读取的内容写入到内存中，然后从内存中获取
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];
      int len = 0;
      // 只要没读完，不断的读取
      while ((len = fileInputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, len);
      }
      // 得到内存中写入的所有数据
      byte[] data = outputStream.toByteArray();
      int sum = 0, e = 1;
      int from = 0;
      int c = 0;
      int f = 0, cost = 0, to = 0;
      for (int i = 0; i < data.length; i++) {
        if (data[i] == 44 || data[i] == 13) {
          sum = 0;
          e = 1;
          for (int k = i - 1; k >= from; k--) {
            sum = sum + (data[k] - 48) * e;
            e *= 10;
          }
          c++;
          if (c == 0) {
            f = sum;
          }
          if (c == 1) {
            cost = sum;
          }
          if (c == 2) {
            to = sum;
            c = 0;
            if (graph.map.get(from) == null) {
              graph.indexToId[count] = from;
              graph.map.put(from, count++);
            }
            if (graph.map.get(to) == null) {
              graph.indexToId[count] = to;
              graph.map.put(to, count++);
            }
            graph.edgeFrom.get(graph.map.get(from)).add(new Edge(graph.map.get(to), cost));
          }
          if (data[i] == 13) {
            i += 1;
          }
          from = i + 1;
        }
      }
      System.out.println();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    Graph graph = new Graph();
    String filePath = "C:/test_data.txt";
    long startTime = System.currentTimeMillis();    //获取开始时间

//    loadData(new File(filePath),graph);
//    loadData2(new File(filePath),graph);
    loadData3(new File(filePath), graph);
    
    long endTime = System.currentTimeMillis();    //获取结束时间
    System.out.println("程序读取文件时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
  }
  
}
