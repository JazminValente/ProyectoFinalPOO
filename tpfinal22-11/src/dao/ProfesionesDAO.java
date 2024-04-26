package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Profesion;

public class ProfesionesDAO {
	
	public boolean agregarProfesion(Profesion profesiones)
	{
		Connection conn = null;
		int filasAfectadas = 0;
		try {
			conn = DriverManager.getConnection(url(), usuario(), contrasenia());
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO `profesiones` (`nombre`) VALUES (?)");
			pStmt.setString(1, profesiones.getNombre());
			
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
	
	public ArrayList<Profesion> traerTodos() {
		ArrayList<Profesion> profesiones = new ArrayList<Profesion>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url(), usuario(), contrasenia());
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM profesiones");

			while (rs.next()) {
				String nombre = rs.getString(2);
				
				
				
				Profesion p = new Profesion();
				p.setNombre(nombre);	
				
				profesiones.add(p);
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

return profesiones;
}
	
	public Profesion traerProfesionPorID(int idProfesion) {
		Connection conn = null;
	
		Profesion p = new Profesion();
		
		try {
			conn = DriverManager.getConnection(url(),usuario(),contrasenia());
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM profesiones where idProfesiones = ?");
			pStmt.setInt(1, idProfesion);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String nombre = rs.getString(2);
				p.setNombre(nombre);
				System.out.println(pStmt.toString());
				System.out.println("profesion id:" + idProfesion + " nombre: " + nombre);
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
		
		return p;
	}
	
	public int conseguirIDProfesiones(String nombre) {
		Connection conn = null;
		int id=5;
		try {
			conn = DriverManager.getConnection(url(),usuario(),contrasenia());
			PreparedStatement pStmt = conn.prepareStatement("SELECT idProfesiones FROM profesiones where nombre = ?;");
			pStmt.setString(1, nombre);
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
