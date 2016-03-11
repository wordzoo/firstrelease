package com.germanclock.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.germanclock.graphics.ViennaBlockUmg;
import com.germanclock.time.Pieces;
import com.germanclock.time.Settings;
import com.germanclock.time.ViennaSettings;
import com.germanclock.words.LocalDialect;
import com.germanclock.words.ViennaDialect;

public class Test {

	//assume a 24 hr time input string
	public static void main(String[] args) {
		//String time = args[0];
		ViennaBlockUmg v = new ViennaBlockUmg();
		v.showTime("7:13");
		
	}
	
	
}


