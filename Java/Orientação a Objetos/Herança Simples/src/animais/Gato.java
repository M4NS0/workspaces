package animais;

public class Gato extends Animal{
	
	String fala;
	
	public Gato (String n, String r) {
		super (n,r);
	}
	public String getFala() {
		return fala;
	}
	public void setFala(String fala) {
		this.fala = fala;
	}
	@Override 
	public String toString() {
		return super.toString() + "\n- " + fala;
	}
}
