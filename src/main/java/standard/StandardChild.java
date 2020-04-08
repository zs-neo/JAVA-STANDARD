package standard;

public class StandardChild extends Standard {
	
	public StandardChild(String data) {
		super(data);
	}
	
	public static void main(String[] args) {
		StandardChild standardChild = new StandardChild("2");
		standardChild.protectedData = "2";
	}
}