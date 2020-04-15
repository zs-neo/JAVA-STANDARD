package solution.test;

// 本题为考试单行多行输入输出规范示例，无需提交，不计分。

import java.util.*;

public class Main {
  
  static class Edge {
    public int to;
    public int cost;
    
    public Edge(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }
  }
  
  static List<List<Edge>> edges = new ArrayList<>();
  static int testCaseNumber;
  static int size = 10000;
  
  public static boolean checkR() {
    boolean[] visit = new boolean[testCaseNumber + 2];
    Queue<Integer> queue = new ArrayDeque<Integer>();
    queue.add(1);
    while (!queue.isEmpty()) {
      int now = queue.poll();
      visit[now] = true;
      for (Edge edge : edges.get(now)) {
        if (!visit[edge.to]) {
          queue.add(edge.to);
        } else {
          return false;
        }
      }
    }
    return true;
  }
  
  public static int dij(int i) {
    boolean[] visit = new boolean[size];
    int pos;
    int[] dist = new int[size];
    for (int k = 0; k < dist.length; k++) {
      dist[k] = 0;
      visit[k] = false;
    }
    dist[i] = 0;
    visit[i] = true;
    pos = i;
    for (int k = 0; k < testCaseNumber; k++) {
      int max = -1;
      int maxIndex = -1;
      for(Edge edge : edges.get(pos)){
        if(!visit[edge.to]){
          if(max < dist[pos] + edge.cost){
            max = dist[pos] + edge.cost;
            maxIndex = edge.to;
          }
        }
      }
      if(maxIndex == -1){
        break;
      }
      for (Edge edge : edges.get(pos)) {
        if (!visit[edge.to]) {
          if (dist[edge.to] < dist[pos] + dist[edge.to]) {
            dist[edge.to] = dist[pos] + dist[edge.to];
          }
        }
      }
    }
    return dist[i];
  }
  
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    testCaseNumber = in.nextInt();
    int[] rowNumber = new int[size];
    for(int i=0;i<size;i++){
      edges.add(new ArrayList<>());
    }
    for (int i = 0; i < testCaseNumber; i++) {
      rowNumber[i] = in.nextInt();
    }
    int from, to, cost;
    for (int i = 0; i < testCaseNumber; i++) {
      from = in.nextInt();
      cost = in.nextInt();
      for (int j = 0; j < rowNumber[i]; j++) {
        to = in.nextInt();
        edges.get(from - 1).add(new Edge(to - 1, cost));
      }
    }
    if (!checkR()) {
      System.out.println("R");
    } else {
      int max = -1;
      for(int i=0;i<testCaseNumber;i++){
        int temp = dij(i);
        if(temp > max)max = temp;
      }
      System.out.println(max);
    }
  }
}
