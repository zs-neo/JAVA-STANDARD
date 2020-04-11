package standard;

public class Standard {
	
	private String data;
	protected String protectedData;
	protected static String staticData;
	
	public Standard(String data) {
		this.data = data;
	}
	
	public String getData() {
		return data;
	}
	
}
/**
 * overload（重载）
 * 参数类型、个数、顺序至少有一个不相同。
 * 不能重载只有返回值不同的方法名。
 * 存在于父类和子类、同类中。
 *
 * override（重写）
 * 方法名、参数、返回值相同。
 * 子类方法不能缩小父类方法的访问权限。
 * 子类方法不能抛出比父类方法更多的异常(但子类方法可以不抛出异常)。
 * 存在于父类和子类之间。
 * 方法被定义为final不能被重写。
 *
 */
