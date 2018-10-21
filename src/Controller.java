
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Controller extends Application {
	static String textFx = "";
  /**
  * Counts the number of occurrence of each String in an ArrayList of String
  * @return a HashMap of String keys to their (# of occurrence) values
  */
	public static HashMap<String, Integer> countWordOccurrences(ArrayList<ArrayList<String>> cleanedSentences) {
		
		HashMap<String, Integer> occurrences = new HashMap<String, Integer>();
		
			for (ArrayList<String> sentence : cleanedSentences) {
				for (String word : sentence) {
					if (!occurrences.containsKey(word)) {
						occurrences.put(word, 1);
					}
					else {
						occurrences.replace(word, occurrences.get(word) + 1);
					}
				}
			}
		
		return occurrences;
	}
  
  // Global ArrayList of String suffixes to check
  
  public static void sortByLength(ArrayList<String> toSort) {
  	Boolean shouldStop; 
  	do {
    	shouldStop = true;
    	for (int i = 0; i < toSort.size() - 1; i++) {
      	if (toSort.get(i).length() < toSort.get(i+1).length()) {
        	String tmp = toSort.get(i+1);
          toSort.set(i+1, toSort.get(i));
          toSort.set(i, tmp);
          shouldStop = false;
        } 
      }
    } while (!shouldStop);
  }
  
  // Stems and array of words 
  public static void stemWords(ArrayList<String> words) {
	  for (int i = 0; i < words.size(); i++) {
		  words.set(i, makeBaseWord(words.get(i)));
		  }
	  }
  
  
  static ArrayList<String> stopWords = new ArrayList<String>(Arrays.asList("a", "about", "above", "after", "against", "all", "am", "an",
                                                                    "and", "any", "are", "aren't", "as", "at", "be", "because", "been",
                                                                    "before", "be", "below", "between", "both", "but", "by", "can't",
                                                                    "cannot", "could", "couldn't", "did", "didn't", "does", "doesn't",
                                                                    "do", "don't", "down", "each", "few", "for", "from", "further",
                                                                    "had", "hadn't", "hasn't", "have", "haven't", "he", "he'd", "he'll",
                                                                    "her", "here", "herself", "him", "himself", "hi", "how", "i", "i'd",
                                                                    "i'll", "i'm", "i've", "if", "in", "into", "is", "isn't", "it", "it's",
                                                                    "its", "itself", "let's", "me", "more", "most", "mustn't", "my",
                                                                    "myself", "no", "nor", "not", "of", "off", "on", "or", "other",
                                                                    "ought", "our", "ours", "ourselves", "out", "over", "own", "same",
                                                                    "shan't", "she", "she'd", "she'll", "she's", "should", "shouldn't",
                                                                    "so", "some", "such", "than", "that", "that's", "the", "their", "them",
                                                                    "themselves", "then", "there", "there's", "these", "their", "they'd",
                                                                    "they'll", "they're", "they've", "this", "those", "through", "to", "too",
                                                                    "under", "until", "up", "was", "wasn't", "we", "we'd", "we'll", "we're",
                                                                    "we've", "were", "weren't", "what", "what's", "when", "when's", "where",
                                                                    "where's", "which", "while", "who", "who's", "whom", "why", "why's", "with",
                                                                    "won't", "would", "wouldn't", "you", "you'd", "you'll", "you're", "you've",
                                                                    "your", "yourself", "yourselves", "follow"));
                                                                    
                                                                    
   
  static ArrayList<String> suffixes = new ArrayList<String>(Arrays.asList("escence", "ization", "aholic", "ectomy", "iatric", "logist", "ostomy", "phobia", "plegia", "plegic", "scribe", "script", "sophic", "trophy", "acity", "ocity", "algia", "arian", "ariam", "ation", "ative", "cracy", "cycle", "esque", "gonic", "loger", "ology", "otomy", "pathy", "phile", "phone", "phyte", "scopy", "scope", "sophy", "able", "ance", "cide", "crat", "cule", "emia", "ence", "ency", "etic", "ette", "gamy", "hood", "ible", "ical", "ious", "itis", "less", "like", "ling", "ment", "ness", "onym", "opia", "opsy", "osis", "path", "pnea", "sect", "ship", "sion", "some", "tion", "tome", "tomy", "tude", "ular", "uous", "ward", "ware", "wise", "ade", "ade", "ies", "ing", "ant", "ard", "ary", "ate", "dom", "dox", "eer", "ent", "ern", "ese", "ess", "est", "ful", "gam", "gon", "ial", "ian", "ile", "ily", "ine", "ing", "ion", "ish", "ism", "ist", "ite", "ity", "ive", "ize", "let", "log", "oid", "oma", "ory", "ous", "ure", "ac", "al", "ar", "cy", "ed", "ee", "en", "er", "fy", "ic", "ly", "or", "th", "ty", "y"));

  /**
   * @arg word - a string that is an unedited space separated text from the document to be summarized
   *
   * @return - true if word is a stop word, false otherwise
   **/ 
   
   public static Boolean isStopWord(String word) {
   		return stopWords.contains(word);
   }
   
   
   /**
   *
   * @arg sentences - an arraylist of strings that are the sentences of the document
   *
   * @return - an arraylist of arraylists of strings - the same sentences, but with stop words removed, and with 
   * 					non-stop words stemmed
   **/ 
  public static ArrayList<ArrayList<String>> filterSentences(ArrayList<String> sentences) {
     ArrayList<ArrayList<String>> toReturn = new ArrayList<ArrayList<String>>();
  		for (String sentence : sentences) {
  			String[] split = sentence.split(" ");
  			ArrayList<String> filtered = new ArrayList<String>();
  			for (String word : split) {
  				String base = makeBaseWord(word.toLowerCase());
  				if (!base.equals("")) {
  					filtered.add(base);
  				}
  			}
  			System.out.println(filtered.toString());
  			toReturn.add(filtered);
  		}
  		
  		return toReturn;
  	}

	 /**
	  *
	  * @arg word - a string that is an unedited space separated text from the document to be summarized
	  *
	  * @return - the input word trimmed down to its base word, i.e. Cities --> City,
	  * 					with the original punctuation maintained, i.e. Cities. --> City.
	  *           If the word is a stop word, return ""
	  *
	  **/
	  public static String makeBaseWord(String word) {
	  	// Check if the "word" is actually a word 
	  	if (word.matches("\\w+")) {
	      //	word without puncuation
	    	String noPunc = word.replaceAll("\\p{Punct}+$", "");
        if (isStopWord(noPunc)) {
          return "";
        }
	      // Initialize punc in the case where there is no punctuation
	      String punc = "";
	      // if there actually was some punctuation 
	      if (noPunc.length() < word.length()) {
	      			// Get the punctuation 
	      	    punc = word.substring(noPunc.length(), word.length());
	      }
	      for (int i = 0; i < suffixes.size(); i++) {
	      	if (noPunc.endsWith(suffixes.get(i))) {   
	          // replace the suffix with its replacement value and check if there are more 
	          // suffixes to replace
	        	return makeBaseWord(noPunc.replaceAll(suffixes.get(i) + "$", punc));
	        } 
	      }
	      return noPunc + punc;
	    } else {
	    	// Do nothing to the text
	    	return word;
	    } 	
	  }
    
  
	  public static HashMap<ArrayList<String>, Double> rankSentences(ArrayList<ArrayList<String>> cleanedSentences, ArrayList<String> originalSentences, HashMap<String, Integer> wordOccurrences) {
		    //would be more efficient if popularities was a hashmap of words to popularities
		    HashMap<ArrayList<String>, Double> rankedSentences = new HashMap<ArrayList<String>, Double>();
		 
		    for(int i = 0; i < cleanedSentences.size(); i++){
		      int numWords = cleanedSentences.get(i).size();
		      Double score = 1.0;
		    	for(int j = 0; j < cleanedSentences.get(i).size(); j++){
          // Implements the harmonic mean score
		        score *= (wordOccurrences.get(cleanedSentences.get(i).get(j)));
		      }
		      rankedSentences.put(new ArrayList<String>(Arrays.asList(originalSentences.get(i).split(" "))), Math.pow(score, 1.0/numWords));
		    }
		    return rankedSentences;
		  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static ArrayList<ArrayList<String>> bestSentences (HashMap<ArrayList<String>, Double> sentenceScores) {
		ArrayList<Tuple> top10 = new ArrayList<Tuple>(10);
		Tuple zero = new Tuple(new ArrayList<String>(), 0.0);
		for (int i = 0; i < 10; i++) {
			top10.add(zero);
		}
		
		Iterator it = sentenceScores.entrySet().iterator();
		
		while(it.hasNext()) {
			Entry<ArrayList<String>, Double> pair = (Entry<ArrayList<String>, Double>) it.next();
			for (int i = 0; i < 10; i++) {
				if (pair.getValue() > top10.get(i).sentenceScore && pair.getKey().size() >= 6) {
					pair.getKey().add(pair.getValue() + "");
					top10.add(i, new Tuple(pair));
					break;
				}
			}
			it.remove();
		}
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < 10; i++) {
			result.add(top10.get(i).sentence);
		}
		return result;
	}
  
  public static void printRankedSentences(int firstNSentences, HashMap<String[], Integer> ranked){
    List<Map.Entry<String[], Integer>> sentenceList = 
       new LinkedList<Map.Entry<String[], Integer> >(ranked.entrySet()); 
  	for(int i = 0; i < firstNSentences; i++){
    	System.out.println((i + 1) + ". " + sentenceList.get(i).getKey() + "\n");
    }
  }

  public static ArrayList<ArrayList<String>> top10Sorted (ArrayList<ArrayList<String>> top10) {
	  ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
	  ArrayList<Tuple> sorting = new ArrayList<Tuple>();
	  for(ArrayList<String> sentence : top10){
			Tuple zero = new Tuple(sentence, (double) Integer.parseInt(sentence.get(0)));
			sorting.add(zero);
	  }

	  // tuples are now in sorting
	  
	  while(sorting.size() > 0){
		  Tuple2 min_value = new Tuple2(Double.MAX_VALUE, 0);
		  for(int i = 0; i < sorting.size(); i++){
			  if(min_value.score > sorting.get(i).sentenceScore){
				  min_value.score = sorting.get(i).sentenceScore;
				  min_value.position = i;
			  }
		  }
		  result.add(sorting.get(min_value.position).sentence);
		  System.out.println(sorting.get(min_value.position).sentence.toString());
		  sorting.remove(min_value.position);
	  }
	  return result;
  }
  @Override
  public void start(Stage primaryStage) {
	  
	  
      primaryStage.setTitle("SKiM Application");      
      Label title = new Label();
      title.setAlignment(Pos.CENTER);
      title.setText("SKiM");
      title.setAlignment(Pos.BASELINE_CENTER);
      title.setFont(Font.font("American Typewriter", FontWeight.BOLD, 70));
      
      
      TextField filepath = new TextField("Enter your filepath here");
      filepath.setLayoutX(250);
      
      TextArea ta = new TextArea();
      ta.setVisible(false);
      
      VBox vb = new VBox(5);
      
      Button enterText = new Button("Enter");
      enterText.setOnAction(new EventHandler<ActionEvent>() {
    	  
    	  @Override
    	  public void handle(ActionEvent e) {
    		  try {
				runProgram(filepath.getText());
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    		  ta.setText(textFx);
    		  ta.setVisible(true);
    		  vb.getChildren().add(ta);
    	  }
      });
      
      
      vb.setAlignment(Pos.CENTER);
      Scene scene = new Scene(vb, 600, 600);
      scene.setFill(javafx.scene.paint.Paint.valueOf("#00b2ff"));

      ta.setPrefSize(200, 200);
      ta.setWrapText(true);

      VBox.setVgrow(ta, Priority.ALWAYS);
      vb.getChildren().addAll(title, enterText, filepath);

      
   /*   ta.setOnAction(new EventHandler<ActionEvent>() {

          @Override
          public void handle(ActionEvent event) {
              System.out.println("Hello World!");
          }
      }); */
      
      //StackPane root = new StackPane();
      //root.getChildren().add(ta);
      
      primaryStage.setScene(scene);
      primaryStage.show();
  }
  
  public static void runProgram(String s) throws IOException {
		
		
		if (s == null) {
			System.out.println("Error! First arg must be filepath to a text file.");
		}
		else {
			String filePath = s;
			if (s.contains(".pdf")) {
				PdfParser.parse(s);
				filePath = s.substring(0,s.length() - 4) + ".txt";
			}
			
			ArrayList<String> originalSentences = SentenceMaker.processor(filePath);
			ArrayList<String> orderedSentences = new ArrayList<String>();
			for (int i = 0; i < originalSentences.size(); i++){
				orderedSentences.add(Integer.toString(i) + " " + originalSentences.get(i));
			}
			ArrayList<String> sentencesCopy = SentenceMaker.processor(filePath);
			
			ArrayList<ArrayList<String>> trimmedSentences = filterSentences(sentencesCopy);
			//flatten trimmedWords and pass to countWordOccurrences
			//int[] popularities = popularities(trimmedWords, countWordOccurrences(trimmedWords));
			
			int i = 0;
			ArrayList<ArrayList<String>> top10 = bestSentences(rankSentences(trimmedSentences, orderedSentences, countWordOccurrences(trimmedSentences)));
			ArrayList<ArrayList<String>> top10Sorted = top10Sorted(top10);
			for (ArrayList<String> sentence : top10Sorted){
				for (int j = 1; j< sentence.size() - 1; j++) {
					System.out.print(sentence.get(j) + " ");
					textFx += sentence.get(j) + " ";
				}
				textFx += "\n";
				textFx += "\n";
				System.out.println("");
				i++;
			}
		}
  }
  
  /**
  * Main handler of the program
  * args[0] should be an absolute filepath to PDF file to be read
 * @throws IOException 
  */
	public static void main(String[] args) throws IOException {

			launch(args);


	}
}