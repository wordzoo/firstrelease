package com.germanclock.graphics;

import java.util.ArrayList;

public class Line {
	public Line () {
		ArrayList<Word> w = new ArrayList<Word>();
		setWords(w);
	}
	private ArrayList<Word> words;

	public ArrayList<Word> getWords() {
		return words;
	}

	public void setWords(ArrayList<Word> words) {
		this.words = words;
	}

	

}
