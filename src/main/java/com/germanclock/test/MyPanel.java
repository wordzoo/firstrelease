package com.germanclock.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


class MyPanel extends JPanel {

    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Draw Text
        drawString(g, "E S K I S T A F Ü N F" + System.lineSeparator()
		+ "Z E H N Z W A N S I G\n"
		+ "D R E I V I E R T E L\n"
		+ "V O R F U N K N A C H\n"
		+ "H A L B A E L F Ü N F\n"
		+ "E I N S X A M Z W E I\n"
		+ "D R E I P M J V I E R\n"
		+ "S E C H S N L A C H T\n"
		+ "S I E B E N Z W Ö L F\n"
		+ "Z E H N E U N K U H R\n",20,20);
    }
    
    void drawString(Graphics g, String text, int x, int y) {
    	g.setFont(new Font( "Courier", Font.PLAIN, 12 ));
    	for (String line : text.split("\n")) 
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }
    
 }