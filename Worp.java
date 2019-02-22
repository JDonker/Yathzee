import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Worp {
	private List<Dobbelsteen> dobbelstenen;
	private int aantalWorpen;
	private int maxWorpen;
	private boolean actief;
	
	public Worp() {
		this.dobbelstenen = new ArrayList<Dobbelsteen>();
		this.aantalWorpen =0;
		this.maxWorpen = 3;
		actief = true;
		for(int i=0;i<5;i++)
			this.dobbelstenen.add(new Dobbelsteen());
	}
	
	public Worp(int maxWorpen) {
		this();
		this.maxWorpen = maxWorpen;
	}
	
	public boolean isActief() {
		return this.actief;
		
	}
	
	
	public void Werpen() {
		if (this.aantalWorpen<this.maxWorpen) {
			for(Dobbelsteen dobbelsteen : this.dobbelstenen) {
				dobbelsteen.werp();
			}
			aantalWorpen++;
			if (aantalWorpen==this.maxWorpen) {
				this.actief=false;
			}
			Collections.sort(this.dobbelstenen);
		}
	}
	
	public void Werpen(String vastHoudString) {
		try {
			for(int i=0;i<vastHoudString.length();i++) {
				if (vastHoudString.charAt(i)>48 && vastHoudString.charAt(i)<54) {
				this.dobbelstenen.get(vastHoudString.charAt(i)-49).vasthouden();
				} else {
					throw new IllegalArgumentException("Verkeerde invoer!");
				}
			} 
			Werpen();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void pas() {
		this.actief=false;
		
	}
	
	
	public boolean Yathzee() {
		if (maxDubbelen()==5)
			return true;
		return false;
	}
	
	
	public boolean fourOfAKind() {
		if (maxDubbelen()>=4 )
			return true;
		return false;
	}
	
	public boolean threeOfAKind() {
		if (maxDubbelen()>= 3)
			return true;
		return false;
	}

	public boolean fullHouse() {
		if (getUniekString().length()==2 && maxDubbelen()==3 )
			return true;
		return false;
	}
	
	public boolean groteStraat() {
		return getUniekString().matches("12345|23456");
	}
	
	public boolean kleineStraat() {
		
		
		return (getUniekString().matches("1234|2345|3456|12346|13456")||groteStraat());
	}
	
	public int scorePerOog(int ogen) {
		int som=0;
		for(Dobbelsteen dobbel : this.dobbelstenen) {
			if (ogen==dobbel.getWaarde())
			som+=dobbel.getWaarde();
		}
		return som;
	}
	
	public int heleScore() {
		int som=0;
		for(Dobbelsteen dobbel : this.dobbelstenen) {
			som+=dobbel.getWaarde();
		}
		return som;
		
	}
	
	
	public String getUniekString() {
		String dString = "";
		int value = 0;
		for(Dobbelsteen dobbel : this.dobbelstenen) {
			if (dobbel.getWaarde()!=value) {
				dString += dobbel.getWaarde();
				value=dobbel.getWaarde();
			}
		}
		return dString;
	}
	
	public String getString() {
		String dString = "";
		for(Dobbelsteen dobbel : this.dobbelstenen) {
				dString += " " + dobbel.getWaarde() + " ";
		}
		return dString;
	}
	
	public int maxDubbelen() {
		int herhaling = 0;
		int maxherhaling = 0;
		int value = 0;
		for(Dobbelsteen dobbel : this.dobbelstenen) {
			if (dobbel.getWaarde()==value) {
				herhaling+=1;
			} else {
				herhaling = 1;
				value = dobbel.getWaarde();
			}
			maxherhaling = Math.max(maxherhaling, herhaling);
		}
		
		return maxherhaling;
	}
	
    public Map<String,Integer> getWorpScoreKaart() {
    	Map<String,Integer> scorekaart=new HashMap<>();
    	// alles op -1 zetten;
    	for (String code : Speler.codes) {
    		scorekaart.put(code, 0);
    	}
    	
    	// voor de dobbelwaarden
    	for(int i=0;i<6;i++) {
    		scorekaart.put(Speler.codes[i], scorePerOog(i+1));
    	}
    	if (this.threeOfAKind())
    		scorekaart.put(Speler.codes[6],heleScore());
    	if (this.fourOfAKind())
    		scorekaart.put(Speler.codes[7],heleScore());	
    	if (this.kleineStraat())
        		scorekaart.put(Speler.codes[8],30);
    	if (this.groteStraat())
    		scorekaart.put(Speler.codes[9],40);
    	if(this.fullHouse())
        		scorekaart.put(Speler.codes[10],25);
    	scorekaart.put(Speler.codes[11],heleScore());
    	if(this.Yathzee())
    		scorekaart.put(Speler.codes[12],50);
    	
    	return scorekaart;
    }

	
	
}
