package com.germanclock.graphics;

import java.util.ArrayList;

public class WordBlock {
	ArrayList<Line> lines;
	Integer remainderMins;
	
	
	
	public WordBlock() {
		ArrayList<Line> lines = new ArrayList<Line>();
		setLines(lines);
	}
	public void setWordBlock(String[][][] lines) {
		Line l;
		Word w;
		Letter lt;
		for(int x=0;x<lines.length;x++) { //lines
			l = new Line();
			for(int y=0;y<lines[x].length;y++) { //words
				w = new Word();
				w.setB(Boolean.FALSE);
				for(int z=0;z<lines[x][y].length;z++) { //letters
					lt = new Letter(lines[x][y][z]);
					 w.getLetters().add(lt);
				}
				l.getWords().add(w);
			}
			getLines().add(l);
		 }
	}

	//TODO on android go back to letter highlight for partial word highlight
	//as in only "viertel" of "dreiviertel"
	public void updateClock(String words, Integer start, Integer end) {
		if(words == null || words == "")
			return;
		//build words from the input
		String b[]=words.split(" ");
		Word w;
		Letter lt;
		for(int x = 0; x < b.length; x++) {
			w = new Word();
			for (int i = 0; i < b[x].length(); i++){
			    char c = b[x].charAt(i);        
			    lt = new Letter(c+"");
			    w.getLetters().add(lt);
			}
			//highlight each word from words arg
			//start/end search on lines given 
			for(Line line: getLines()) {
				if(getLines().indexOf(line) >= start
						&& getLines().indexOf(line) <= end) {
					for(Word wd: line.getWords()) {
						if(w.equals(wd)) 
							wd.setB(Boolean.TRUE);
					}
				}
			}
		}
		
	}
	public void drawClock() {
		StringBuffer st = new StringBuffer();
		for(Line l: getLines()) {
			for(Word w: l.getWords()) {
				if(w.getB()) 
					st.append("*");
				for(Letter lt: w.getLetters()) {
					st.append(lt.getC());
				}
				if(w.getB()) 
					st.append("*");
			}
			st.append(System.getProperty("line.separator"));
		}
		for(int i = 1; i <=getRemainderMins();i++)
			st.append(".");
		
		System.out.println(st.toString());
		
	}
	
	
	public ArrayList<Line> getLines() {
		return lines;
	}
	public void setLines(ArrayList<Line> lines) {
		this.lines = lines;
	}
	public Integer getRemainderMins() {
		return remainderMins;
	}
	public void setRemainderMins(Integer remainderMins) {
		this.remainderMins = remainderMins;
	}
	
	

}
