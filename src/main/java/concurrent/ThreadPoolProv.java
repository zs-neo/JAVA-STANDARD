package concurrent;

import concurrent.excutor.entity.User;
import concurrent.excutor.entity.UserRoleMid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolProv {
  
//  volatile HashSet userIdSet = new HashSet();
//
//  /**
//   * 通过角色获取用户信息列表
//   * @param sysRoleId
//   * @return
//   * @throws InterruptedException
//   */
//  public RestResponse<List<User>> getUserListByRoleId(String sysRoleId) throws InterruptedException{
//    //通过角色查询出用户信息  由于线程,设置final
//    final List<UserRoleMid> userRoleMidList = service.getListByRoleId(sysRoleId);
//    final List<User> userList = new ArrayList<User>();
//    final User user = null;
//    ExecutorService executorService = Executors.newCachedThreadPool();
//    CountDownLatch countDownLatch = new CountDownLatch(userRoleMidList.size());
//
//    for (int i = 0; i < 20 ; i++) {
//      executorService.execute(new Runnable() {
//        @Override
//        public void run() {
//          for (final UserRoleMid userRoleMid: userRoleMidList){
//            if(userRoleMid.getUserId() != null){
//              //查询用户
//              boolean flag = false;
//              synchronized (this){
//                flag = userIdSet.add(userRoleMid.getUserId());
//              }
//              if(flag){
//                queryUser(user,userRoleMid,userList,sysRoleId,countDownLatch);
//              }
//            }
//          }
//        }
//      });
//    }
//    //阻塞 直到超时或者countdown userrolelist.count 次后
//    countDownLatch.await(30, TimeUnit.SECONDS);
//    return null;
//
//  }
//
//  /**
//   * 用于获取用户信息
//   * @param user
//   * @param record
//   * @param userList
//   * @param sysRoleId
//   * @param countDownLatch
//   */
//  public void queryUser(User user, UserRoleMid userRoleMid, List<User> userList, String sysRoleId, CountDownLatch countDownLatch){
//    try{
//      //用户用户信息
//      user = RemoteUser.getUserByRemote(userRoleMid.getUserId());
//      userList.add(user);
//      countDownLatch.countDown();
//    }catch{
//      countDownLatch.countDown();
//    }
//  }
  
}
