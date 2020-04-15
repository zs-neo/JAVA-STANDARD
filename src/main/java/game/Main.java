package game;

import org.springframework.util.StringUtils;

import java.io.*;
import java.util.*;


public class Main {
  
  private final static int minL = 3;
  private final static int maxL = 7;
  private final static int vertex = 30000;
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
    
    public int getSize(){
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
        if (graph.indexToId[next] < graph.indexToId[path.get(0)]){
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
  
  public static void printRes(){
    for(int i=0;i<result.size();i++){
      for(int j=0;j<result.get(i).size();j++){
        System.out.print(result.get(i).get(j)+" ");
      }
      System.out.println();
    }
  }
  
  public static void main(String[] args) {
    Graph graph = new Graph();
    String filePath = "C:/Users/Administrator/JAVA-STANDARD/src/main/java/test_data.txt";
    try {
      BufferedReader reader = new BufferedReader(
        new InputStreamReader(
          new FileInputStream(
            new File(filePath))));
      String line;
      int from, to, money, count = 0;
      while (!StringUtils.isEmpty(line = reader.readLine())) {
        String[] nums = line.split(",");
        from = Integer.parseInt(nums[0]);
        to = Integer.parseInt(nums[1]);
        money = Integer.parseInt(nums[2]);
        graph.indexToId[count] = from;
        graph.map.putIfAbsent(from, count++);
        graph.indexToId[count] = to;
        graph.map.putIfAbsent(to, count++);
        graph.edgeFrom.get(graph.map.get(from)).add(new Edge(graph.map.get(to), money));
      }
      reader.close();
    } catch (IOException e) {
      //什么也不做
      e.printStackTrace();
    }
    boolean[] mark = new boolean[vertex];
    for(int i=0;i<graph.getSize();i++){
      List<Integer> path = new ArrayList<>();
      mark[i] = true;
      path.add(i);
      dfs(i,path,mark,graph);
      mark[i] = false;
      path.remove(path.size()-1);
    }
    System.out.println(result.size());
    printRes();
  }
  
}
