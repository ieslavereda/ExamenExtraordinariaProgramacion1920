package sorteo;

public class Equipo implements Comparable<Equipo>{
	
	private int id;
	private String nombre;
	private int bombo;
	
	public Equipo(int id, String nombre, int bombo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.bombo = bombo;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getBombo() {
		return bombo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setBombo(int bombo) {
		this.bombo = bombo;
	}

	@Override
	public String toString() {
		return "Equipo [id=" + id + ", nombre=" + nombre + ", bombo=" + bombo + "]";
	}

	@Override
	public int compareTo(Equipo e) {		
		return nombre.compareTo(e.getNombre());
	}
	
}
