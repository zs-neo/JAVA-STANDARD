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
