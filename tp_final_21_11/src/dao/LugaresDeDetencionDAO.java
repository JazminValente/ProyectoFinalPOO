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
	
	public boolean agregarLugar(LugarDeDetencion lugar)
	{
		Connection conn = null;
		int filasAfectadas = 0;
		try {
			conn = DriverManager.getConnection(url(), usuario(), contrasenia());
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO `lugaresDeDetencion` (`nombreLugar`) VALUES (?)");
			pStmt.setString(1, lugar.getNombreLugar());
			
			filasAfectadas = pStmt.executeUpdate();
			

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
		return filasAfectadas == 1;
	}
	
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
