import java.util.ArrayList;

public class Sort {
	public static void sortByLength(ArrayList<String> toSort) {
	  	boolean shouldStop; 
	  	do {
	  		shouldStop = true;
	    	for (int i = 0; i < toSort.size() - 1; i++) {
	    		if (toSort.get(i).length() < toSort.get(i+1).length()) {
	    			String tmp = toSort.get(i+1);
	    			toSort.set(i+1, toSort.get(i));
	    			toSort.set(i, tmp);;
	    			shouldStop = false;
	        } 
	      }
	    } while (!shouldStop);
	  }
}
