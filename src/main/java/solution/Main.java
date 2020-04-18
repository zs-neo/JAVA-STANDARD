package solution;

import java.util.ArrayList;
import java.util.List;

public class Main {
  
  static class Edge {
    private int to;
    
    public Edge(int to) {
      this.to = to;
    }
  }
  
  private static List<List<Edge>> edgesFrom;
  
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    edgesFrom = new ArrayList<>(n + 1);
    for (int i = 0; i < n; i++) edgesFrom.add(new ArrayList<>());
    int[] indegree = new int[n + 1];
    for (int[] a : edges) {
      edgesFrom.get(a[0]).add(new Edge(a[1]));
      edgesFrom.get(a[1]).add(new Edge(a[0]));
    }
    int[] queue = new int[10000];
    int start = 0, end = 0;
    int[] dist = new int[n + 1];
    int[] visit = new int[n + 1];
    //遍历每一个点，找到树高
    for (int i = 0; i < n; i++) {
      for (int j : dist) j = 0;
      for (int j : visit) j = 0;
      start = 0;
      end = 0;
      queue[end++] = i;
      dist[i] = 1;
      visit[i] = 1;
      while (start < end) {
        int top = queue[start++];
        System.out.print(top+"-");
        for (Edge edge : edgesFrom.get(top)) {
          System.out.print(edge.to+" ");
          if(visit[edge.to]==1)continue;
          dist[edge.to] = dist[top] + 1;
          visit[edge.to] = 1;
          queue[end++] = edge.to;
        }
      }
      System.out.println();
      for (int j : dist) System.out.print(j + " ");
      System.out.println();
    }
    return null;
  }
  
  
  public static void main(String[] args) {
    Main main = new Main();
    int[][] a = {{1, 0}, {1, 2}, {1, 3}};
    main.findMinHeightTrees(4, a);
  }
}
