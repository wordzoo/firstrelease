package com.germanclock.time;

public class Pieces {
	Integer fiveMinBucket;
	Integer remainderMinutes;
	Integer hr;
	Integer hr24;
	

	public Pieces(String time) {
		String splitTime[]=time.split(":");
		String hour=splitTime[0];
		String minute=splitTime[1];
				 
		setFiveMinBucket(new Integer(minute));
		setRemainderMinutes(new Integer(minute));
		setHr(new Integer(hour));
		setHr24(new Integer(hour));
	}

	public Integer getFiveMinBucket() {
		return fiveMinBucket;
	}

	public void setFiveMinBucket(Integer minutes) {
		this.fiveMinBucket = Math.round(minutes/5) * 5;
	}

	public Integer getRemainderMinutes() {
		return remainderMinutes;
	}

	public void setRemainderMinutes(Integer minutes) {
		this.remainderMinutes = minutes % 5;
	}

	public Integer getHr() {
		return hr;
	}

	public void setHr(Integer hour) {
		this.hr = hour % 12;
	}

	public Integer getHr24() {
		return hr24;
	}

	public void setHr24(Integer hr24) {
		this.hr24 = hr24;
	}

	
	
	
}
