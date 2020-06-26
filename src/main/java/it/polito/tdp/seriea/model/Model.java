package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {

	private SerieADAO dao;
	private Graph<Season,DefaultWeightedEdge> graph;
	private Map<Integer, Season> idMap;
	private List<Season> seasons;
	SeasonSpecific sGold;
	
	public Model() {
		dao=new SerieADAO();
	}
	
	public List<Team> getTeam(){
		return dao.listTeams();
	}
	
	public List<Integer> getPuntiTot(Team t){
		
		List<Integer> punti = new ArrayList<>();
		seasons=dao.listAllSeasons();
		
		int tot=0;
		
		for(Season s: seasons) {
			List<String> esito=dao.getPuntiTot(t, s);
			tot=this.puntiClassifica(esito);
			punti.add(tot);
	}
		
		return punti;
	}
	
	
	public void creaGrafo(Team t) {
		this.graph=new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		idMap = new HashMap<Integer, Season>();
		
		dao.getVertici(t,idMap);
		Graphs.addAllVertices(graph, idMap.values());
		int peso1=0;
		int peso2=0;
		
		for(Season s1: idMap.values()) {
			for(Season s2: idMap.values()) {
				if(graph.getEdge(s1, s2)==null || graph.getEdge(s2, s1)==null ) {	
					
			List<String> esito1=dao.getPuntiTot(t, s1);
			List<String> esito2=dao.getPuntiTot(t, s2);
			
			peso1=this.puntiClassifica(esito1);
			peso2=this.puntiClassifica(esito2);
			
			if(peso1>peso2) {
				Double distanza=(double) (peso1-peso2);
				Graphs.addEdgeWithVertices(graph, s1, s2,distanza);
			}else 
				if(peso2>peso1) {
					Double distanza=(double) (peso2-peso1);
					Graphs.addEdgeWithVertices(graph, s2, s1,distanza);
				}
			 }
		  }
	   }
	}
	
	public int nVertici() {
		return this.graph.vertexSet().size();
	}
	public int nArchi() {
		return this.graph.vertexSet().size();
	}
	
	
	public SeasonSpecific getAnnataDOro() {
		sGold= new SeasonSpecific();
		int peso=0;
		int pNew = 0;
	
		int in;
		int out;
		
		for(Season s: this.graph.vertexSet()) {
			in=0;
			out=0;
			
			for(DefaultWeightedEdge edge : this.graph.incomingEdgesOf(s))
				in += (int) this.graph.getEdgeWeight(edge);
			
			for(DefaultWeightedEdge edge : graph.outgoingEdgesOf(s))
				out += (int) graph.getEdgeWeight(edge);
				
				pNew +=(out-in);
				
			if(pNew>peso) {
				peso=pNew;
				sGold.setPeso(pNew);
			}
		
			sGold.setS1(s);
		}
		return sGold;
	}
	
	
	public int getPesoSGold() {
		return sGold.getPeso();
	}
	
	public int puntiClassifica(List<String> esito) {
		int a=0;
		int h=0;
		int d=0;
		int tot=0;
		
		for(String e:esito) {
			switch(e) {
			case "A":
				a+=3;
				break;
			case "H":
				h+=3;
				break;
			case "D":
				d+=1;
				break;
			}
		}
		tot=a+d+h;
		return tot;
	}
}
