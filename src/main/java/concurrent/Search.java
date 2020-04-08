package concurrent;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class Search {

	private int value;
	
	public int getNext(){
		return value++;
	}
	
	public synchronized int getNextValue(){
		return value++;
	}

}
