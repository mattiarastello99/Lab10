package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public void getAllRivers(Map<Integer, River> idMapRiver) {
		
		final String sql = "SELECT id, name FROM river";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				idMapRiver.put(res.getInt("id") , new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

	}
	
	public void creaValori(River r) {
		
		final String sql = "SELECT f.river, MIN(f.day) as min, MAX(f.day) as max, AVG(f.flow) as avg "
				+ "FROM flow f "
				+ "WHERE f.river = ? "
				+ "GROUP BY f.river";
		

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				
				r.setFlowAvg(res.getFloat("avg"));
				r.setDataMax(res.getDate("max").toLocalDate());
				r.setDataMin(res.getDate("min").toLocalDate());
				
				
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

	}

	public List<Flow> creaFlows(Map<Integer, River> idMapRiver, int river) {
		
		final String sql = "SELECT * "
				+ "FROM flow f WHERE f.river = ? "
				+ "ORDER BY f.river";

		List<Flow> elenco = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				Flow f = new Flow(res.getInt("id"), res.getDate("day").toLocalDate(), res.getFloat("flow"), idMapRiver.get(res.getInt("river")));
				elenco.add(f);
				
			}

			conn.close();
			return elenco;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
	}
}
