import java.util.ArrayList;
import java.util.Map.Entry;

public class Tuple implements Comparable {
	ArrayList<String> sentence;
	Double sentenceScore;
	
	public Tuple(ArrayList<String> sentence, Double sentenceScore) {
		this.sentence = sentence;
		this.sentenceScore = sentenceScore;
	}
	
	public Tuple(Entry<ArrayList<String>, Double> tuple) {
		this.sentence = tuple.getKey();
		this.sentenceScore = tuple.getValue();
	}
	
	public int compareTo(Object otherTuple) {
		return this.sentenceScore.compareTo(((Tuple) otherTuple).sentenceScore);
	}
}