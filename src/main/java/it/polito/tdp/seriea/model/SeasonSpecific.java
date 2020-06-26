package it.polito.tdp.seriea.model;



public class SeasonSpecific {

	Season s1;
    int peso;
    
    public SeasonSpecific() {
		
	}
    
    public SeasonSpecific(Season s1, int peso) {
		super();
		this.s1 = s1;
		this.peso = peso;
	}
    
	public Season getS1() {
		return s1;
	}
	public void setS1(Season s1) {
		this.s1 = s1;
	}
	
	public int getPeso() {
		return peso;
	}
	
	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return  s1.getDescription() ;
	}
	
 
    
	
    
    
	
}
