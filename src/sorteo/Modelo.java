package sorteo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;

public class Modelo extends Database {

	private TreeMap<Integer, Equipo> equipos;
	private TreeMap<Integer, Grupo> grupos;

	public Modelo() {
		super();
		this.equipos = new TreeMap<Integer, Equipo>();
		this.grupos = new TreeMap<Integer, Grupo>();
	}

	private void cargarEquipos() {

		equipos = new TreeMap<Integer, Equipo>();

		String sql = "SELECT * FROM EQUIPO";

		try (Connection con = conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql);) {

			int id;
			String nombre;
			int bombo;

			while (rs.next()) {

				id = rs.getInt("ID");
				nombre = rs.getString("NOMBRE");
				bombo = rs.getInt("BOMBO");

				equipos.put(id, new Equipo(id, nombre, bombo));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Bombo<Equipo> obtenerBombo(int numeroBombo) {

		if (equipos.isEmpty())
			cargarEquipos();

		Bombo<Equipo> bombo = new Bombo<Equipo>();
		
		for (Equipo e : equipos.values())
			if (e.getBombo() == numeroBombo)
				bombo.introducirBola(e);

		return bombo;
	}

	private void cargarGrupos() {

		grupos = new TreeMap<Integer, Grupo>();

		String sql = "SELECT * FROM GRUPO ORDER BY LETRA";
		Grupo grupo;

		try (Connection con = conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql);) {

			int id;
			String letra;

			while (rs.next()) {

				id = rs.getInt("ID");
				letra = rs.getString("LETRA");
				grupo = new Grupo(id, letra);

				grupos.put(grupo.getId(), grupo);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public Bombo<Grupo> getGrupos() {

		if (grupos.isEmpty())
			cargarGrupos();

		return new Bombo<Grupo>(new ArrayList<Grupo>(grupos.values()));
	}

	public boolean insertarEquipo_has_grupo(Equipo_has_grupo ehg) throws SQLException {

		String sql = "INSERT INTO EQUIPO_HAS_GRUPO VALUES(?,?)";

		try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql);) {

			int pos = 0;

			ps.setInt(++pos, ehg.getEquipo().getId());
			ps.setInt(++pos, ehg.getGrupo().getId());

			ps.execute();

			return true;

		}
	}

	public boolean borrarSorteo() throws SQLException {

		String sql = "DELETE FROM EQUIPO_HAS_GRUPO";

		try (Connection con = conectar(); Statement st = con.createStatement();) {

			st.execute(sql);
			return true;
		}

	}

	public ArrayList<Equipo_has_grupo> getSorteo() {

		if (equipos.isEmpty())
			cargarEquipos();
		if (grupos.isEmpty())
			cargarGrupos();

		ArrayList<Equipo_has_grupo> sorteo = new ArrayList<Equipo_has_grupo>();

		String sql = "SELECT * FROM EQUIPO_HAS_GRUPO";

		try (Connection con = conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql);) {

			int idEquipo, idGrupo;

			while (rs.next()) {

				idEquipo = rs.getInt("IDEQUIPO");
				idGrupo = rs.getInt("IDGRUPO");

				sorteo.add(new Equipo_has_grupo(equipos.get(idEquipo), grupos.get(idGrupo)));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sorteo;

	}

}
