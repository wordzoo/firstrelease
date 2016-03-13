package com.germanclock.graphics;

import java.util.ArrayList;

public class Word {
	private Boolean b;
	
	public Word() {
		ArrayList<Letter> l = new ArrayList<Letter>();
		setLetters(l);
	}
	
	public Boolean getB() {
		return b;
	}
	public void setB(Boolean b) {
		this.b = b;
	}
	
	private ArrayList<Letter> letters;

	public ArrayList<Letter> getLetters() {
		return letters;
	}

	public void setLetters(ArrayList<Letter> letters) {
		this.letters = letters;
	}
	
	public boolean equals(Object o){
		Word w2 = (Word)o;
		if(w2.getLetters().size() != getLetters().size())
			return false;
		int x = 0;
		for(Letter l: getLetters()) {
			if(!l.getC().equalsIgnoreCase(w2.getLetters().get(x).getC())){
				return false;
			}
			x++;
		}
		return true;
	}
}
