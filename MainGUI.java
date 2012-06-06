package assignment8;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Set;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

@SuppressWarnings("serial")
public class MainGUI extends JFrame {
	private final JTextArea input = new JTextArea();
	private final JLabel labFindWord = new JLabel("Find Word: ");
	private final JTextField findWord = new JTextField();
	private final JTextArea output = new JTextArea();
	private final Listener keyListener = new Listener();
	
	private String priorInput = "";
	private WordMap words;
	
	public MainGUI () {
		super("Word Finder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		Container window = getContentPane();
		window.setMinimumSize(new Dimension(600, 1200));
		
		input.setPreferredSize(new Dimension(600, 300));
		input.addKeyListener(keyListener);
		window.add(input, BorderLayout.NORTH);
		
		window.add(labFindWord, BorderLayout.WEST);
		
		findWord.setPreferredSize(new Dimension(590 - labFindWord.getWidth(), 12));
		findWord.addKeyListener(keyListener);
		window.add(findWord, BorderLayout.EAST);
		
		output.setPreferredSize(new Dimension(600, 300));
		output.setEnabled(false);
		window.add(output, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}
	
	public static void main (String[] argv) {
		new MainGUI();
	}
	
	class Listener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent evt) {
			if (!(input.getText().equals(priorInput))) {
				priorInput = input.getText();				
				words = new WordMap(priorInput);
			}
			
			output.setText("");
			
			String searchTerm = findWord.getText();
			
			for (int ix : words.get(searchTerm)) {
				output.append(wordsNear(words.getWords(), ix));
			}
			
		}

		private String wordsNear (String[] words, int ix) {
			String[] nearby = null;
			if (ix > 2 && ix < words.length - 3) {
				nearby = Arrays.copyOfRange(words, ix - 2, ix + 2);
			} else if (ix < 2) {
				nearby = Arrays.copyOfRange(words, 0, 5);
			} else {
				nearby = Arrays.copyOfRange(words, words.length - 6, words.length - 1);
			}
			
			StringBuffer sb = new StringBuffer();
			for (String s : nearby) {
				sb.append(s + " ");
			}
			return sb.toString();
		}

		@Override
		public void keyTyped(KeyEvent e) {}
		
	}
}
