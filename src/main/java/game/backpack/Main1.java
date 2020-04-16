package game.backpack;

import org.springframework.util.StringUtils;

import java.io.*;
import java.util.*;


public class Main1 {
  
  private final static int minL = 3;
  private final static int maxL = 7;
  private final static int vertex = 612000;
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
  
  static void dfs(int cur, List<Integer> path, boolean[] mark, Graph graph) {
    for (Edge e : graph.edgeFrom.get(cur)) {
      int next = e.to;
      if (next == path.get(0)) {
        if (path.size() < minL) {
          continue;
        } else {
          result.add(new ArrayList<>(path));
        }
      } else {
        if (path.size() == maxL) {
          continue;
        }
        if (mark[next]) {
          continue;
        }
        if (graph.indexToId[next] < graph.indexToId[path.get(0)]) {
          continue;
        }
        mark[next] = true;
        path.add(next);
        dfs(next, path, mark, graph);
        path.remove(path.size() - 1);
        mark[next] = false;
      }
    }
  }
  
  public static void convertIndexToId(Graph graph) {
    int temp;
    for (int i = 0; i < result.size(); i++) {
      for (int j = 0; j < result.get(i).size(); j++) {
        temp = result.get(i).get(j);
        result.get(i).set(j, graph.indexToId[temp]);
      }
    }
  }
  
  public static void main(String[] args) {
    Graph graph = new Graph();
    String filePath = "C:/test_data.txt";
    long startTime = System.currentTimeMillis();    //获取开始时间
    long start = startTime;
    try {
      BufferedReader reader = new BufferedReader(
        new InputStreamReader(
          new FileInputStream(
            new File(filePath))));
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
    long endTime = System.currentTimeMillis();    //获取结束时间
    System.out.println("程序读取文件时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    startTime = endTime;
    
    boolean[] mark = new boolean[vertex];
    for (int i = 0; i < graph.getSize(); i++) {
      List<Integer> path = new ArrayList<>();
      mark[i] = true;
      path.add(i);
      dfs(i, path, mark, graph);
      mark[i] = false;
      path.remove(path.size() - 1);
    }
    endTime = System.currentTimeMillis();    //获取结束时间
    System.out.println("递归求解时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    startTime = endTime;
    
    convertIndexToId(graph);
    result.sort(new Comparator<List<Integer>>() {
      @Override
      public int compare(List<Integer> o1, List<Integer> o2) {
        if (o1.size() != o2.size()) {
          return o1.size() - o2.size();
        } else {
          for (int i = 0; i < o1.size(); i++) {
            if (!o1.get(i).equals(o2.get(i))) {
              return o1.get(i) - o2.get(i);
            }
          }
        }
        return 0;
      }
    });
    endTime = System.currentTimeMillis();    //获取结束时间
    System.out.println("排序时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    startTime = endTime;
    
    try {
      File file = new File("C:/result.txt");
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
      int i = 0;
      String row = "";
      writer.write(result.size() + "\r\n");
      while (i < result.size()) {
        row = "";
        for (int k = 0; k < result.get(i).size(); k++) {
          if (k != result.get(i).size() - 1) {
            row += result.get(i).get(k) + ",";
          } else {
            row += result.get(i).get(k);
          }
        }
        writer.write(row + "\r\n");
        i++;
      }
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    endTime = System.currentTimeMillis();    //获取结束时间
    System.out.println("输出文件时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    System.out.println("---------------");
    System.out.println("总时间：" + (endTime - start) + "ms");    //输出程序运行时间
  }
  
}
