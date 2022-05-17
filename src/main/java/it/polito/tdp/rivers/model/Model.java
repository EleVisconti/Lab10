package it.polito.tdp.rivers.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	private Map<Integer, River> riversMap;
    private List<River> rivers;
    private Simulatore sim = new Simulatore();

	public Model() {
		RiversDAO riversDao = new RiversDAO();
		rivers = riversDao.getAllRivers();
		for (River river : rivers) {
			river.setFlows(riversDao.getFlows(river));
		}
		for (River river : rivers) {
			riversDao.getFlows(river);
		}
	}

	public List<River> getRivers() {
		return rivers;
	}

	
	public String doSimula(River r, double k) {
		return sim.simula(r, k);
	}
	
	
	
}
