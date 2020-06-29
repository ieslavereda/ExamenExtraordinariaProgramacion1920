package sorteo;

public class Grupo implements Comparable<Grupo>{
	
	private int id;
	private String letra;
	
	public Grupo(int id, String letra) {
		super();
		this.id = id;
		this.letra = letra;
	}

	public int getId() {
		return id;
	}

	public String getLetra() {
		return letra;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	@Override
	public String toString() {
		return "Grupo [id=" + id + ", letra=" + letra + "]";
	}

	@Override
	public int compareTo(Grupo g) {
		
		return letra.compareTo(g.getLetra());
	}
	
	



}
