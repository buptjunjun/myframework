package junjun.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List c = new ArrayList(); 
		int x = 128; 
		c.add("hello"); 
		c.add(new Integer(x)); 
		System.out.println(c); 
		for (Iterator ii = c.iterator(); ii.hasNext();) { 
		if (ii.next().equals(new Integer(x))) { 
		ii.remove(); 
		} 
		} 
		System.out.println(c);
	}

}
