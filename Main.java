package assignment8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    static class Position implements Comparable<Position>
    {
        final String source;
        final int line;
        final int word;

        public Position(String source, int line, int word)
        {
            this.source = source;
            this.line = line;
            this.word = word;
        }

        @Override
        public int compareTo(Position o)
        {
            // return source.compareTo(o.source) ?: line - o.line ?: column - o.column;
            int rv;
            rv = source.compareTo(o.source);
            if (rv != 0)
                return rv;
            rv = line - o.line;
            if (rv != 0)
                return rv;
            return word - o.word;
        }
        
        @Override
        public String toString()
        {
            return String.format("file %s, line %d, word %d", source, line, word);
        }
    }

    
    private final MultiMap<String, Position> impl = new MultiMap<String, Position>();

    /**
     * @param args array containing: filename
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException
    {
        if (args.length == 0)
        {
            System.err.println("Usage: assignment8.Main files-to-search");
            System.exit(1);
        }
        Main main = new Main();
        for (String filename : args)
            main.feed(filename, new BufferedReader(new FileReader(filename)));
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (prompt() && (line = in.readLine()) != null)
        {
            main.search(line);
        }
    }

    private void search(String line)
    {
        System.out.format("\"%s\" occurs at positions:", line);
        System.out.println();
        for (Position i : impl.get(line.toLowerCase()))
        {
            System.out.format(" %s", i);
            System.out.println();
        }
        System.out.println();
    }

    private static boolean prompt()
    {
        System.out.print("Enter a word to search for: ");
        return !System.out.checkError();
    }

    private void feed(String name, BufferedReader in) throws IOException
    {
        String line;
        int l = 1;
        while ((line = in.readLine()) != null)
        {
            int w = 1;
            for (String word : line.split("\\s"))
            {
                impl.add(word.toLowerCase(), new Position(name, l, w));
                w++;
            }
            l++;
        }
    }
}
