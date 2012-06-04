package assignment8;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

@SuppressWarnings("serial")
public class Frame extends JFrame implements KeyListener {
	private final JTextArea input = new JTextArea();
	private final JLabel labFindWord = new JLabel("Find Word: ");
	private final JTextField findWord = new JTextField();
	private final JTextArea output = new JTextArea();
	
	private String priorInput = "";
	private WordHash words;
	
	public Frame () {
		super("Word Finder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		Container window = getContentPane();
		window.setMinimumSize(new Dimension(600, 1200));
		
		input.setPreferredSize(new Dimension(600, 300));
		window.add(input, BorderLayout.NORTH);
		
		window.add(labFindWord, BorderLayout.WEST);
		
		findWord.setPreferredSize(new Dimension(590 - labFindWord.getWidth(), 12));
		window.add(findWord, BorderLayout.EAST);
		
		output.setPreferredSize(new Dimension(600, 300));
		output.setEnabled(false);
		window.add(output, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// Unused.
	}

	@Override
	public void keyReleased(KeyEvent evt) {
		Document in = input.getDocument();
		try {
			if (in.getText(0, in.getLength()).equals(priorInput)) {
				priorInput = in.getText(0, in.getLength());
				output.setText(priorInput);
				
				words = new WordHash(priorInput);
			}
		} catch (BadLocationException e) {
			System.exit(1);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Unused.
	}

}
