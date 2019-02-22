import java.util.Random;

public class Dobbelsteen implements Comparable<Dobbelsteen> {
	private Random random;
	private int value;
	private boolean vasthouden;
	
	public Dobbelsteen() {
		this.vasthouden=false;
		this.random = new Random();
		this.vasthouden = false;
	}
	
	public void werp() {
		if (!this.vasthouden) this.value = this.random.nextInt(6)+1;
		loslaten();
	}
	
	public int getWaarde() {
		return this.value;
	}
	
	public boolean getVasthouden() {
		return this.vasthouden;
	}
	
	public void loslaten() {
		this.vasthouden=false;
	}
	
	public void vasthouden() {
		this.vasthouden=true;
	}

	@Override
	public int compareTo(Dobbelsteen steen2) {
		if(this.getWaarde()==steen2.getWaarde()) {
			return 0;
		} else if (this.getWaarde()>steen2.getWaarde()) {
			return 1;
			
		}
		return -1;
	}
}
