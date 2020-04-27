package netty.chap1;

public class Chap1 {
	
	/**
	 * linux将所有外部设备看做文件
	 * file->fileFd(file描述符)   socket->socketFd(socket描述符) 描述符指向内核中的一个结构体
	 *
	 * UNIX IO模型
	 * - 阻塞IO：进程在调用recvfrom开始到结束都是阻塞的
	 * - 非阻塞IO：recvfrom从应用层到内核的时候如果缓冲区没有数据的话就直接返回错误，否则轮询，看内核是否有数据到来
	 * - IO复用模型：select/poll 多个fd阻塞在select上,select顺序扫描fd是否准备就绪，而且支持的fd数量有限.
	 * 				epoll使用基于事件驱动的方式代替顺序扫描,当fd准备就绪时立即回调函数rollback.
	 * - 信号驱动IO模型：开启接口信号驱动IO功能,当数据准备就绪时发送一个SIGIO信号通知应用回调recvfrom读取数据
	 * - 异步IO模型：告知内核启动某个操作并在数据读取完后通知我们
	 *
	 * epoll的改进
	 * - 支持一个进程打开的socket描述符不受限
	 * - IO效率不受fd数目增加而线性下降
	 * - 使用mmap加速内核和用户空间的消息传递
	 * - epoll的api更简单
	 */
	
}
