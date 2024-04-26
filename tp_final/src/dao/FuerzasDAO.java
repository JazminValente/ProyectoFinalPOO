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
	
	/*public boolean agregarFuerza (Fuerzas fuerza) {
		Connection conn = null;
		int filasAfectadas = 0;
		try {
			conn = DriverManager.getConnection(url(), usuario(), contrasenia());
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO `fuerzas` (`nombreFuerza`) VALUES (?)");
			pStmt.setString(1, fuerza.getNombre());
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
	
	public boolean modificarFuerza(Fuerzas fuerza, String nombreFuerza) {
		Connection conn = null;
		int filasAfectadas=0;
		try {
			conn=DriverManager.getConnection(url(),usuario(),contrasenia());
			PreparedStatement pStmt = conn.prepareStatement("UPDATE `fuerzas` SET `nombre` = ? WHERE `nombre` = ?; ");
			pStmt.setString(1,fuerza.getNombre());
			pStmt.setString(2, nombreFuerza);
			
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
	
	
	public boolean eliminarFuerza (String nombre) {
		Connection conn = null;
		int filasAfectadas = 0;
		try {
			conn = DriverManager.getConnection(url(), usuario(), contrasenia());
			
			PreparedStatement pStmt = conn.prepareStatement("DELETE FROM `fuerzas` WHERE `nombre` = ?;");
			pStmt.setString(1, nombre);

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
	*/
	
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
