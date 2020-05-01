package paxos;

public class Test {
	
	/**
	 * start
	 * step1
	 * - proposer选择提案M0向Acceptor的超过半数的子集发送prepare(M0)请求
	 *
	 * - 如果一个Acceptor收到请求prepar(MN),且MN大于该Acceptor已经响应过的所有Prepare请求的编号,
	 * 那么它就会将它已经批准的最大编号响应给Preposer,同时承诺不会批准任何编号小于MN的提案。
	 * 即Acceptor已响应过Prepare(1,2,3...5,7),此时接收到prepare(8)就会响应编号为7的提案给proposer
	 *
	 * step2
	 * - 如果proposer收到半数以上的Acceptor对于其发出的prepare(MN)的响应,那么proposer就会发送一个针对[MN,VN]提案的Accept请求给Acceptor
	 *
	 * - [MN,VN]中VN指响应中编号最大的提案的值,如果响应中不包含任何提案那么他就是任意值
	 *
	 * - Acceptor收到[MN,VN]的Accept请求,只要该Acceptor尚未对编号大于MN的prepare请求做出响应,他就可以通过这个提案
	 *
	 * step3
	 * - learner获取提案
	 * 		- 方案一：一旦Acceptor批准了提案就将改提案发给所有learner,通信次数太多
	 * 		- 方案二：搞一个learner主节点接收所有Acceptor的请求,同时主节点负责通知其他的leaner,单点故障
	 * 		- 方案三：搞一个主learner集合,每个都可以通知其他的结点,网络通信的复杂度过高
	 *
	 * end
	 *
	 * 主proposer：但凡主proposer提出一个编号更高的提案该提案最终会被批准.解决的问题是两个proposer死循环的问题.
	 *
	 */
	
	
}
