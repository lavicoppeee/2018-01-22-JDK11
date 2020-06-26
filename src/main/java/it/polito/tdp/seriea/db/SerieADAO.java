package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.SeasonSpecific;
import it.polito.tdp.seriea.model.Team;

public class SerieADAO {

	public List<Season> listAllSeasons() {
		String sql = "SELECT season, description FROM seasons";
		List<Season> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Season(res.getInt("season"), res.getString("description")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Team> listTeams() {
		String sql = "SELECT team FROM teams";
		List<Team> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Team(res.getString("team")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
	public List<String> getPuntiTot(Team t, Season s) {
		String sql="SELECT m.FTR as r " + 
				"FROM matches as m " + 
				"WHERE m.Season=? and (m.AwayTeam=? or m.HomeTeam=?) ";
		
		List<String> result=new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getSeason());
			st.setString(2, t.getTeam());
			st.setString(3, t.getTeam());
			
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("r"));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	public void getVertici(Team t, Map<Integer, Season> idMap) {
		String sql="SELECT s.season as s, s.description as d " + 
				"FROM matches as m, seasons as s " + 
				"WHERE s.season=m.season and (m.AwayTeam=? or m.HomeTeam=?) " + 
				"GROUP BY s.season, s.description ";
			
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getTeam());
			st.setString(2, t.getTeam());
			
			ResultSet res = st.executeQuery();

			while (res.next()) {
				if(!idMap.containsKey(res.getInt("s"))) {
					Season season=new Season(res.getInt("s"),res.getString("d"));
					idMap.put(season.getSeason(), season);
					
				}
			}

			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}

//	public List<SeasonSpecific> getArchi(Map<Integer,Season> idMap){
//		
//		List<SeasonSpecific> result=new ArrayList<>();
//	}

}
