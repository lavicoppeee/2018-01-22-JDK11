package it.polito.tdp.seriea.model;

public class Arco {

	Season s1;
	Season s2;
	Double peso;
	
	public Arco(Season s1, Season s2, Double peso) {
		super();
		this.s1 = s1;
		this.s2 = s2;
		this.peso = peso;
	}

	public Season getS1() {
		return s1;
	}

	public void setS1(Season s1) {
		this.s1 = s1;
	}

	public Season getS2() {
		return s2;
	}

	public void setS2(Season s2) {
		this.s2 = s2;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	
}
