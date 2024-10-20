package ie.atu.sw;

/**
 * 
 * This class wraps the key and the double value in a single object so it can be stored and compared
 * inside an array.
 */

public class EntryWrapper  implements Comparable<EntryWrapper> {
	String key;
	double value;
	static boolean descendingOrder = true; // static works across all instances of this class, saves memory
	// and stops us from needing to change the var on all instances
	
	public EntryWrapper(String key, double value)
	{
		this.key = key;
		this.value = value;
	}

	@Override
	public int compareTo(EntryWrapper o) {
		if (descendingOrder) 
		{
			Double nonePrimitive1 = o.value;
			return nonePrimitive1.compareTo(value);
		}
		else
		{
			Double nonePrimitive2 = value;
			return nonePrimitive2.compareTo(o.value);
		}
	}
}
