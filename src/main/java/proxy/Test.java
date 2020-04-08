package proxy;

public class Test {
	
	public static void testProxy() throws Throwable {
		UserService userService = new UserServiceImpl();
		UserInvokeHandler handler = new UserInvokeHandler(userService);
		UserService proxy = (UserService) handler.getProxy();
		proxy.add();
	}
	
	public static void main(String[] args) {
		try {
			testProxy();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
}
