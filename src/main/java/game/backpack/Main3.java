package game;

import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main3 {
  
  private final static int minL = 3;
  private final static int maxL = 7;
  private static int vertex = 0;
  private final static int process = 10;
  private final static int timeOut = 20000;
  private final static String filePath = "C:/";
  private static List<List<Integer>> result = new ArrayList<>();
  private static ThreadPoolExecutor executor = new ThreadPoolExecutor(process, process, 0L,
    TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(process));
  
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
          synchronized (result) {
            result.add(new ArrayList<>(path));
          }
        }
      } else {
        if (path.size() == maxL) continue;
        if (mark[next]) continue;
        if (graph.indexToId[next] < graph.indexToId[path.get(0)]) continue;
        mark[next] = true;
        path.add(next);
        dfs(next, path, mark, graph);
        path.remove(path.size() - 1);
        mark[next] = false;
      }
    }
  }
  
  public static void jobs(int left, int right, Graph graph) {
    boolean[] visit = new boolean[vertex];
    for (int i = left; i < right; i++) {
      if (graph.edgeFrom.get(i).size() == 0) continue;
      List<Integer> path = new ArrayList<>();
      visit[i] = true;
      path.add(i);
      dfs(i, path, visit, graph);
      visit[i] = false;
      path.remove(path.size() - 1);
    }
//    System.out.println("- doing job " + left + " " + (right - 1) + " over");
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
    File file = new File(filePath + "test_data.txt");
    vertex = ((int) file.length() / 73557) * 6000;
    Graph graph = new Graph();
    long startTime = System.currentTimeMillis();
    long start = startTime;
    
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
      int count = 0;
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
          if (c == 0) {
            f = sum;
            c++;
          } else if (c == 1) {
            to = sum;
            c++;
          } else if (c == 2) {
            cost = sum;
            c = 0;
            if (graph.map.get(f) == null) {
              graph.indexToId[count] = f;
              graph.map.put(f, count++);
            }
            if (graph.map.get(to) == null) {
              graph.indexToId[count] = to;
              graph.map.put(to, count++);
            }
            graph.edgeFrom.get(graph.map.get(f)).add(new Edge(graph.map.get(to), cost));
          }
          if (data[i] == 13) {
            i += 1;
          }
          from = i + 1;
        }
      }
      fileInputStream.close();
      outputStream.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    long endTime = System.currentTimeMillis();
    System.out.println("程序读取文件时间：" + (endTime - startTime) + "ms");
    startTime = endTime;
    
    int partNumber = graph.getSize() / process;
    int lastNumber = graph.getSize() % process;
    int jobNumber = 0;
    if (lastNumber == 0) {
      jobNumber = process;
    } else {
      jobNumber = process + 1;
    }
    
    CountDownLatch countDownLatch = new CountDownLatch(jobNumber);
    for (int i = 0; i < graph.getSize(); i += partNumber) {
      final int startIndex = i;
      if (i + partNumber >= graph.getSize()) {
        final int endIndex = i;
        executor.submit(new Runnable() {
          @Override
          public void run() {
            jobs(endIndex, graph.getSize(), graph);
            countDownLatch.countDown();
          }
        });
      } else {
        executor.submit(new Runnable() {
          @Override
          public void run() {
            jobs(startIndex, startIndex + partNumber, graph);
            countDownLatch.countDown();
          }
        });
      }
    }
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("result " + result.size());
    endTime = System.currentTimeMillis();
    System.out.println("递归求解时间：" + (endTime - startTime) + "ms");
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
    endTime = System.currentTimeMillis();
    System.out.println("排序时间：" + (endTime - startTime) + "ms");
    startTime = endTime;
    try {
      File outFile = new File(filePath + "result1.txt");
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
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
    endTime = System.currentTimeMillis();
    System.out.println("输出文件时间：" + (endTime - startTime) + "ms");
    System.out.println("---------------");
    System.out.println("总时间：" + (endTime - start) + "ms");
  }
  
}
