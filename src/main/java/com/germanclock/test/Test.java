package com.germanclock.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.time.ViennaSettings;
import com.germanclock.words.LocalDialect;
import com.germanclock.words.ViennaDialect;

public class Test {

	//assume a 24 hr time input string
	public static void main(String[] args) {
		//String time = args[0];
		String time="7:13";
		
		ViennaSettings s = new ViennaSettings();
		s.setUmgangssprachlich(10);
		s.setRangeForViertel(10);
		s.setRangeForHalb(10);
		s.setRangeForDreiViertel(5);
		
		Pieces p = new Pieces(time);
		
		LocalDialect v = new ViennaDialect();
		
		System.out.println(v.getVerbalTime(p, s));
		
		/*SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });*/
		
	}
	
	 private static void createAndShowGUI() {
        JFrame f = new JFrame("German Clock");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
    }
	
	
}


