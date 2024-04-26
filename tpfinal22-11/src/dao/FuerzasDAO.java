package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Fuerzas;

public class FuerzasDAO {
	public ArrayList<Fuerzas> traerTodas() {
		ArrayList<Fuerzas> fuerzas = new ArrayList<Fuerzas>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url(), usuario(), contrasenia());
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM fuerzas");

			while (rs.next()) {
				String nombre = rs.getString(2);
				
				
				Fuerzas f = new Fuerzas();
				f.setNombre(nombre);
				
				fuerzas.add(f);
				
			}
			
			

} catch (SQLException e) {
	e.printStackTrace();
} finally {
	if (conn != null) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

return fuerzas;
}
	
	public int conseguirIDFuerzas(String nombreFuerza) {
		Connection conn = null;
		int id=0;
		try {
			conn = DriverManager.getConnection(url(),usuario(),contrasenia());
			PreparedStatement pStmt = conn.prepareStatement("SELECT idfuerzas FROM fuerzas where nombreFuerza = ?;");
			pStmt.setString(1, nombreFuerza);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return id;
	}
	
	private String url() {
		String url = "jdbc:mysql://localhost:3306/bd_tpfinal";
		return url;
	}
	private String usuario() {
		String usuario = "root";
		return usuario;
	}
	private String contrasenia() {
		String contrasenia = "admin";
		return contrasenia;
	}

}
