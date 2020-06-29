package sorteo;

import java.util.Comparator;

public class Equipo_has_grupo implements Comparable {

	private Equipo equipo;
	private Grupo grupo;

	public static final Comparator<Equipo_has_grupo> COMPARTOR_BY_NAME = new Comparator<Equipo_has_grupo>() {

		@Override
		public int compare(Equipo_has_grupo ehg1, Equipo_has_grupo ehg2) {
			return ehg1.getEquipo().compareTo(ehg2.getEquipo());
		}

	};

	public Equipo_has_grupo(Equipo equipo, Grupo grupo) {
		super();
		this.equipo = equipo;
		this.grupo = grupo;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		return "Equipo_has_grupo [equipo=" + equipo.getNombre() + ", grupo=" + grupo.getLetra() + "]";
	}

	@Override
	public int compareTo(Object o) {

		if (o instanceof Equipo_has_grupo) {

			Equipo_has_grupo ehg = (Equipo_has_grupo)o;
			
			if (grupo.getLetra().compareTo(ehg.getGrupo().getLetra()) == 0)
				return equipo.compareTo(ehg.equipo);
			else
				return grupo.getLetra().compareTo(ehg.getGrupo().getLetra());
		} else

			return 0;
	}

}
