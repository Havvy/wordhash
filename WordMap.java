package assignment8;

import java.util.*;

@SuppressWarnings("serial")
public class WordMap extends HashMap<String, Set<Integer>> {
	private String[] words;
	
	public WordMap (String input) {
		words = input.split(" |-|\n");
		
		input = input.replaceAll("[^a-zA-Z\\- ]", "").toLowerCase();
		String[] words = input.split(" |-|\n");
		
        for(int ix = 0; ix < words.length; ix++){
            if (words[ix] != null) {
            	put(words[ix], new Integer(ix));
            }
        }
	}
	
	public void put (String word, Integer indice) {
		Set<Integer> indices = super.get(word);
		if (indices == null) {
			// Word not yet in map. We add the word..
			TreeSet<Integer> set = new TreeSet<Integer>();
			set.add(indice);
			super.put(word, set);
		} else {
			// Word in map. Just add the new index.
			indices.add(indice); 
		}
	}
	
	public Set<Integer> get (String word) {
		Set<Integer> set = super.get(word);
		
		if (set == null) {
			return Collections.emptySet();
		} else {
			return Collections.unmodifiableSet(set);
		}
	}
	
	public String[] getWords () {
		return words;
	}
}