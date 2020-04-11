package concurrent;

import concurrent.excutor.entity.Node;

import java.util.List;
import java.util.concurrent.Executor;

public class RecursiveSolution {
  
  /**
   * 把顺序递归转换为并行递归
   *
   * @param nodes
   * @param res
   */
  public void sequentialRecursice(List<Node> nodes, List<Node> res) {
    for (Node node : nodes) {
      res.add(node);
      sequentialRecursice(node.getChildren(), res);
    }
  }
  
  public void parrallelRecursive(final Executor executor,List<Node> nodes,final List<Node> res){
    for(final Node node : nodes){
      executor.execute(new Runnable() {
        @Override
        public void run() {
          res.add(node);
        }
      });
      parrallelRecursive(executor,node.getChildren(),res);
    }
  }
  
}
