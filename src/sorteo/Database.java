package sorteo;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

public abstract class Database {

	private String host;
	private String port;
	private String user;
	private String password;
	
	private final String FICHERO = "default.properties";

	private Connection conexion;

	public Database() {
					
		Properties prop = new Properties();
		prop.setProperty("host", "192.168.1.52");
		prop.setProperty("port", "1521");
		prop.setProperty("user", "alumno1");
		prop.setProperty("password", "1111");
		
		InputStream is = null;
		
		try {
			is = new FileInputStream(FICHERO);
			prop.load(is);
		} catch(IOException e) {
			System.out.println(e.toString());
		}
		
		this.host = prop.getProperty("host");
		this.port = prop.getProperty("port");
		this.user = prop.getProperty("user");
		this.password = prop.getProperty("password");
		
		try {
			prop.store(new FileWriter(FICHERO), "store to properties file");
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	public Connection conectar() {

		conexion = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String BaseDeDatos = "jdbc:oracle:thin:@" + host + ":" + port + ":XE";
			conexion = DriverManager.getConnection(BaseDeDatos, user, password);
			if (conexion != null)
				System.out.println("Conexión realizada con éxito a SORTEO");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error al acceso a la BD:\n"+
												"Host: " + this.host +"\n"+
												"Port: " + this.port +"\n"+
												"User: " + this.user +"\n"+
												"Password: " + this.password +"\n"+
												"Repase los datos de acceso y modique el fichero " + FICHERO, "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		return conexion;
	}

	public void desconectar() {
		try {
			conexion.close();
			System.out.println("Desconexion realizada con exito");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
