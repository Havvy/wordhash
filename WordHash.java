package assignment8;

import java.util.*;

@SuppressWarnings("serial")
public class WordHash extends HashMap<String, Set<Integer>>
{
    public WordHash(String input)
    {
        input = input.replaceAll("[^a-zA-Z\\- ]", "").toLowerCase();
        String[] words = input.split(" |-");

        for (int ix = 0; ix < words.length; ix++)
        {
            if (words[ix] != null)
            {
                put(words[ix], new Integer(ix));
            }
        }
    }

    public void put(String key, Integer value)
    {
        Set<Integer> indices = super.get(key);
        if (indices == null)
        {
            // Word not yet in map. We add the word..
            TreeSet<Integer> set = new TreeSet<Integer>();
            set.add(value);
            super.put(key, set);
        }
        else
        {
            // Word in map. Just add the new index.
            indices.add(value);
        }
    }
}
