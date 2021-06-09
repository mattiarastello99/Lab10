package it.polito.tdp.rivers.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {

	Simulator sim = new Simulator();
	
	RiversDAO dao;
	Map<Integer, River> idMapRiver;
	
	public Model() {
		idMapRiver = new HashMap<>();
		dao = new RiversDAO();
		
	}
	
	
	public Collection<River> riverList(){
		
		//riempio la mia idMap 
		dao.getAllRivers(idMapRiver);
		
		return idMapRiver.values();
	
	}
	
	public void calcola(River river) {
		
		//inserisco i valori del mio river(avg, lista dei flows, date min e max
		dao.creaValori(river);
		river.setFlows(dao.creaFlows(idMapRiver, river.getId()));
		
	}
	
	public void calcolaSimulazione(double k, River r) {
		sim.calcolaSimulazioe(k, r);
	}
	
	public double getMediaSim() {
		return sim.getMedia();
	}
	public Integer getNumSim() {
		return sim.getNumGiorniNoIrr();
	}
}
