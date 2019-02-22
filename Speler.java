/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jasper
 */
public class Speler {
	static String[] codes = {"1en\t\t","2en\t\t","3en\t\t","4en\t\t","5en\t\t","6en\t\t","Three of a kind","Four of a kind","kleine straat","grote straat\t","Full House\t","Kans\t\t","Yathzee\t"};
    private String naam;
    private ArrayList<Worp> worpen;
    private Map<String,Integer> scorekaart;
    private boolean plays;
    
    

    Speler(String naam){
        this.naam= naam;
        this.plays=true;
        this.worpen = new ArrayList<Worp>();
        nieuweScoreKaart();
    }
    

    final void nieuweScoreKaart() {
    	this.scorekaart=new HashMap<>();
    	for (String code : Speler.codes) {
    		this.scorekaart.put(code, -1);
    	}
    }

    
    public ArrayList<Worp> getHanden(){
        return new ArrayList<>(this.worpen);
    }
    
    public void printWorpen() {
		System.out.println("[1][2][3][4][5]");
		for(Worp worp : this.worpen)
			System.out.println(worp.getString());
    }
  
    public String getName(){
        return this.naam;
    }
    
    public int getScore() {
    	int som = 0;
    	for(String code:this.scorekaart.keySet()) {
    		if (this.scorekaart.get(code)>=0)
    		som+=this.scorekaart.get(code);
    	}
    	return som;
    }
    
    public Map<String,Integer> getScoreKaart() {	
    	return new HashMap<String,Integer>(this.scorekaart);
    }
    
    public void setScore(String code, int score) {
    	this.scorekaart.put(code, score);
    }
        
    public boolean speeltMee(){
        return this.plays;
    }
        
    public void stop(){
        this.plays=false;
    }
    
    public void addWorp(Worp worp) {
    	this.worpen.add(worp);
    	
    }
    
    public boolean kaartVol() {
    	for(String code:this.scorekaart.keySet()) {
    		if (this.scorekaart.get(code)<0)
    		 return false;
    	}
    	return true;
    }
    
}