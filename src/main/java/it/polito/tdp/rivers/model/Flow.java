package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Flow implements Comparable<Flow>{
	
	private int id;
	private LocalDate day;
	private double flow;
	private River river;

	public Flow(int id, LocalDate day, double flow, River river) {
		this.day = day;
		this.flow = flow;
		this.river = river;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public double getFlow() {
		return flow;
	}

	public void setFlow(double flow) {
		this.flow = flow;
	}

	public int getId() {
		return id;
	}

	public River getRiver() {
		return river;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRiver(River river) {
		this.river = river;
	}

	@Override
	public String toString() {
		return "Flow [day=" + day + ", flow=" + flow + ", river=" + river + "]";
	}

	@Override
	public int compareTo(Flow arg0) {
		
		return this.day.compareTo(arg0.day);
	}

	
}
