package assignment8;

import java.io.*;
import java.util.Set;

public class MainCLI {

    static public WordHash wordHash;
    public static String inputString;
    public static String[] words;
   
    public static void main(String[] args) throws IOException {
    wordHash = new WordHash(readstr()); 
   
    boolean mainrepeat = true;
    while (mainrepeat) {
        System.out.println("*************************************************************************");
        System.out.println("Hello! Please choose an option (represented by the corresponding number):");
        System.out.println("(1) Print out the string.");
        System.out.println("(2) Search for a word in the string");
        System.out.println("(0) exit the program.");
        mainrepeat = usrIn();
        }
    }

    static public boolean usrIn() throws IOException{
    	String[] printStrings = inputString.split(" ");
        String iputstr;
        Set<Integer> indices = null;
        int chosenvalue = -1;
        chosenvalue = readval(0, 2);
        
        switch (chosenvalue) {
        case 0:
        	System.out.println("Goodbye! Have a nice day! :D");
            return false;
        case 1:
        	System.out.println("*************************************************************************");
            System.out.println("Here is your string:");
            System.out.println(inputString);
            return true;
        case 2:
        	System.out.println("*************************************************************************");
            System.out.println("What word would you like to search for?:");
            iputstr = readstr();
            indices = wordHash.get(iputstr);
            if (indices == null){
                System.out.println("The word is not in the document!");
            } else {
                System.out.println("Here are your values:");
                System.out.println("--------------------------------");
                for (Integer ix : indices) {
                    System.out.println("");
                    System.out.println("****At word " + ix + "*****");
                    printexcript(ix, printStrings);
                }
                System.out.println("--------------------------------");
            }
            return true;
        default:
        	System.out.println("*************ERROR IN USRIN METHOD*************");
        	return true;
        }
    }
   
    static public String readstr(){
        String foundstr = null;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        boolean uix = true;
        while (uix) {
        	try {       
                foundstr = input.readLine();
                uix = false;
                if (foundstr == null) {
                	System.out.println("Please enter a word.");
                	uix = true;
                }
        	} catch (IOException ex) {
        		System.out.println("Please enter a valid word (ex: 'the', 'cat')");
        	}
           
        }
        return foundstr;
    }
   
    static public int readval(int check1, int check2) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        boolean uix = true;
        int status = -1;
        while (uix){
            try{                // Reads the user input. If input is not one of the integers, then it re-prompts the user for input
                status = Integer.parseInt(input.readLine());
                uix = false;
                if (status < check1 || status > check2){
                    uix = true;
                    System.out.println("_Incorrect value: please input an integer (between " + check1 + " and " + check2 + ")");
                    System.out.println("");
                    continue;
                }
            }catch (NumberFormatException nos){
                    System.out.println("_Incorrect value: please input an integer");
                    uix = true;
                    System.out.println("");
                }
               
        }
        if (status == -1){
            throw new Error("##########ERROR IN READVAL1 METHOD, QUITTING PROGRAM##########");
        }
        return status;
    }
   
    static public void printexcript(Integer index, String[] splitstrn){
        boolean lowerval = ((index - 1) < 0);
        boolean lower2val = ((index - 2) < 0);
        boolean upperval = ((index + 1) >= splitstrn.length);
        boolean upper2val = ((index + 2) >= splitstrn.length);
       
        System.out.println( "..." + ((!lower2val)?(splitstrn[index - 2] + " "):(".") ) +
        		((!lowerval)?(splitstrn[index - 1] + " "):(".") )                      +
        		splitstrn[index]                                                       +
        		((!upperval)?(" " + splitstrn[index + 1]):(".") )                      +
        		((!upper2val)?(" " + splitstrn[index + 2]):("."))                      +
        		"..." );
    }
}