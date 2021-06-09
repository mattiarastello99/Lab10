package it.polito.tdp.rivers.model;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Simulator {

	private PriorityQueue<Flow> codaEventi;
	
	//variabili del sistema
	private double Q;
	private double C;
	private River fiume;
	private int numGiorni;
	
	List<Double> listaC;
	
	//variabili di output
	private int ggNoIrrigazione;
	
	public void calcolaSimulazioe(double k, River river) {
		
		codaEventi = new PriorityQueue<Flow>();
		listaC = new LinkedList<>();
		
		this.fiume = river;
		
		this.ggNoIrrigazione = 0;
		
		this.numGiorni = 0;
		
		this.Q = k * river.getFlowAvg() * 30;
		
		this.C = this.Q/2;
		
		for(Flow f : river.getFlows()) {
			codaEventi.add(f);
		}
		
		while(!codaEventi.isEmpty()) {
			Flow f = codaEventi.poll();
			processEvent(f);
		}
		
	}

	private void processEvent(Flow f) {
		
		double f_in = f.getFlow();
		
		double prob = Math.random()*100;
		double f_out;
		double f_outMin=0.8*this.fiume.getFlowAvg();
		
		if(prob<=5) {
			f_out = 10*f_outMin;
		}else {
			f_out = f_outMin;
		}
		
		this.C = this.C + f_in - f_out;
		if(this.C < 0) {
			this.C = 0;
			this.ggNoIrrigazione++;
			listaC.add(C);
		}
		else {
			if(this.C > this.Q) {
				this.C = Q;
				listaC.add(this.C);
			}else {
				listaC.add(this.C);
			}
		}
		
		
		this.numGiorni++;
	}

	public double getMedia() {
		
		double sum = 0.0;
		for(Double d : listaC) {
			sum+=d;
		}
		
		return sum/numGiorni;
	}

	public int getNumGiorniNoIrr() {
		return this.ggNoIrrigazione;
	}

}
