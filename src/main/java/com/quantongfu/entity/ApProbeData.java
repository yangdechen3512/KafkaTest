package com.quantongfu.entity;

public class ApProbeData
{
	protected int sourceType;
	protected String apmac;
	protected int srcType;
	protected String usermac;
	protected long reportedTime;
	protected int channel;
	protected int rssi;
	protected int noiseFloor;

	private long timeStampForMinute;

	private long timeStampFor5Minute;

	private long timeStampForHour;

	private long timeStampForDay;

	private long timeStampForWeek;

	public ApProbeData() {

	}

	public int getSourceType() {
		return sourceType;
	}

	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}

	public String getApmac() {
		return apmac;
	}

	public void setApmac(String apmac) {
		this.apmac = apmac;
	}

	public int getSrcType() {
		return srcType;
	}

	public void setSrcType(int srcType) {
		this.srcType = srcType;
	}

	public String getUsermac() {
		return usermac;
	}

	public void setUsermac(String usermac) {
		this.usermac = usermac;
	}

	public long getReportedTime() {
		return reportedTime;
	}

	public void setReportedTime(long reportedTime) {
		this.reportedTime = reportedTime;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getRssi() {
		return rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public int getNoiseFloor() {
		return noiseFloor;
	}

	public void setNoiseFloor(int noiseFloor) {
		this.noiseFloor = noiseFloor;
	}

	public String toString() {
        return "the apmac is"+this.apmac+"the usermac is"+this.usermac; 
	}


	public long getTimeStampForMinute() {
		return timeStampForMinute;
	}

	public void setTimeStampForMinute(long timeStampForMinute) {
		this.timeStampForMinute = timeStampForMinute;
	}

	public long getTimeStampFor5Minute() {
		return timeStampFor5Minute;
	}

	public void setTimeStampFor5Minute(long timeStampFor5Minute) {
		this.timeStampFor5Minute = timeStampFor5Minute;
	}

	public long getTimeStampForHour() {
		return timeStampForHour;
	}

	public void setTimeStampForHour(long timeStampForHour) {
		this.timeStampForHour = timeStampForHour;
	}

	public long getTimeStampForDay() {
		return timeStampForDay;
	}

	public void setTimeStampForDay(long timeStampForDay) {
		this.timeStampForDay = timeStampForDay;
	}

	public long getTimeStampForWeek() {
		return timeStampForWeek;
	}

	public void setTimeStampForWeek(long timeStampForWeek) {
		this.timeStampForWeek = timeStampForWeek;
	}
}
