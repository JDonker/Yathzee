import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Yathzee {
	private ArrayList<Speler> spelers;
	private Scanner reader;
	private boolean spelActief;
	
	
	public Yathzee() {
		this.spelActief = true;
		this.reader = new Scanner(System.in);
		leesSpelers();
        if ( this.spelers.size()==0) {
        	this.spelers.add(new Speler("Yat Zee"));
        } 
	}
	
	public void run() {
		boolean spelActief = true;
		boolean rondeActief;
		Worp hand;
		while(spelActief) {
			
			for(Speler speler: this.spelers) {
				speler.nieuweScoreKaart();
			}
			rondeActief = true;
			while(rondeActief) {
				for(Speler speler: this.spelers) {
					if (!speler.kaartVol()) {
						hand = new Worp();
						System.out.println(speler.getName()+ " is aan de beurt.");
						werpen(hand);
						speler.addWorp(hand);
						scoren(hand,speler);
					}
				}
				
				// check of kaarten vol zijn
				rondeActief = false;
				for(Speler speler: this.spelers) {
					if (!speler.kaartVol()) {
						rondeActief = true;
					}
				}
				
				
				
			}
			
			// ronde afgelopen scores printen
			for(Speler speler: this.spelers) {
				System.out.println(speler.getName() + " " + speler.getScore() + " punten");
			}
			
			
		}
	}
	
    private void leesSpelers(){
        
        this.spelers = new ArrayList<>();
        int i = 1;
        String naam;  

        while(true) {
            System.out.println("Geef de naam van speler " + i + " en druk op [enter]. Of druk op [enter] om te starten.");
            naam = reader.nextLine();
            
            // maak speler met ingelezen naam of in geval van geen naam start spel 
            if (naam.length()==0) {
                break;
            } 
            this.spelers.add(new Speler(naam));   
            i++;
        }
    }
	
    
    private void werpen(Worp hand) {
		hand.Werpen();
		String input;
		while (hand.isActief()) {
			System.out.println("alle stenen opnieuw [enter] passen [p] of toets welke stenen je wilt vasthouden");
			System.out.println("[1][2][3][4][5]");
			System.out.println(hand.getString());
			input = reader.nextLine();
			switch(input) {
				case "q":
				this.spelActief = false;
				case "p":
					hand.pas();
					break;
			}
			hand.Werpen(input);

		}
		System.out.println("[1][2][3][4][5]");
		System.out.println(hand.getString());
	
    }
    
    private void scoren(Worp hand,Speler speler) {
    	String input;
    	boolean keuzeGemaakt = false;
		Map<String, Integer> worpKaart = hand.getWorpScoreKaart();
		Map<String, Integer> spelerKaart = speler.getScoreKaart();
		System.out.println("vakje\t\tScorekaart \t\tWorpscore");
		char index = 'a';
		for(String code : Speler.codes) {
			if(spelerKaart.get(code)>0) {
				System.out.println("["+ index + "]" + code + "\t  " + spelerKaart.get(code) +"\t\t  " + worpKaart.get(code) );
			} else {
				System.out.println("["+ index + "]" + code + "\t  [leeg] \t  " + worpKaart.get(code) );
			}
			index++;
		}
		System.out.println("Geef aan in welke vakje je de score wil opslaan;");
		
		keuzeGemaakt = true;
		while(keuzeGemaakt) {
			input = reader.nextLine();
			if(input.length()==1) {
				input = input.toLowerCase();
				char keuze = input.charAt(0);
				if(keuze>96&& keuze<110) {
					speler.setScore(Speler.codes[keuze-97], worpKaart.get(Speler.codes[keuze-97]));
					keuzeGemaakt=false;
				}
				
			} else {
				System.out.println("verkeerde invoer");
				
			}

		}
	}
    	
}
	
	

