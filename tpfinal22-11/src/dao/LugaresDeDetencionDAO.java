package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.LugarDeDetencion;

public class LugaresDeDetencionDAO {
	
	
	public ArrayList<LugarDeDetencion> traerTodos() {
		ArrayList<LugarDeDetencion> lugares = new ArrayList<LugarDeDetencion>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url(), usuario(), contrasenia());
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM lugaresdedetencion");

			while (rs.next()) {
				String nombre = rs.getString(2);
				
				
				
				LugarDeDetencion l = new LugarDeDetencion();
				l.setNombreLugar(nombre);		
				
				lugares.add(l);
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

return lugares;
}
	
	public LugarDeDetencion traerLugarPorID(int idLugar) {
		Connection conn = null;
	
		LugarDeDetencion e = new LugarDeDetencion();
		
		try {
			conn = DriverManager.getConnection(url(),usuario(),contrasenia());
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM lugaresdedetencion where idLugares = ?");
			pStmt.setInt(1, idLugar);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String nombre = rs.getString(2);
				e.setNombreLugar(nombre);
				System.out.println(pStmt.toString());
				System.out.println("lugar id:" + idLugar + " nombre: " + nombre);
			}
		} catch (SQLException ae) {
			ae.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ae) {
					ae.printStackTrace();
				}
			}
		}
		
		return e;
	}
	
	public int conseguirIDLugaresDeDetencion(String nombreLugar) {
		Connection conn = null;
		int id=0;
		try {
			conn = DriverManager.getConnection(url(),usuario(),contrasenia());
			PreparedStatement pStmt = conn.prepareStatement("SELECT idLugares FROM lugaresDeDetencion where nombreLugar = ?;");
			pStmt.setString(1, nombreLugar);
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
