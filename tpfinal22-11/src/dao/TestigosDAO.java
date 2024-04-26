package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.CCDTyE;
import modelo.Testigo;

public class TestigosDAO {

	public boolean agregarTestigo (Testigo testigo) {
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url(), usuario(), contrasenia());
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO `testigos` (`DNITestigos`, `Nombre_completo`, `Testimonio`) VALUES (?,?,?)");
			pStmt.setInt(1, testigo.getDniTestigo());
			pStmt.setString(2, testigo.getNombreCompleto());
			pStmt.setString(3, testigo.getTestimonio());

			filasAfectas = pStmt.executeUpdate();

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
		return filasAfectas == 1;
	}
	
	public boolean modificarTestigo(Testigo testigo, int dniOriginal) {
		Connection conn = null;
		int filasAfectadas = 0;
		try {
			conn = DriverManager.getConnection(url(), usuario(), contrasenia());
			PreparedStatement pStmt = conn.prepareStatement("UPDATE testigos SET `Nombre_completo` = ?, `Testimonio` = ? WHERE `DNITestigos` = ?;");
			pStmt.setString(1, testigo.getNombreCompleto());
			pStmt.setString(2, testigo.getTestimonio());
			pStmt.setInt(3, dniOriginal);

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
	
	public boolean eliminarTestigo(int dni) {
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url(), usuario(), contrasenia());
			
			PreparedStatement pStmt = conn.prepareStatement("DELETE FROM `testigos` WHERE `DNITestigos` = ?;");
			pStmt.setInt(1, dni);

			filasAfectas = pStmt.executeUpdate();

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
		return filasAfectas == 1;
	}
	
	
	
	public ArrayList<Testigo> traerTodos() {
		ArrayList<Testigo> testigos = new ArrayList<Testigo>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url(), usuario(), contrasenia());
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM testigos");

			while (rs.next()) {
				int dni = rs.getInt(2);
				String nombre = rs.getString(3);
				String testimonio = rs.getString(4);
				Testigo e = new Testigo();
				e.setDniTestigo(dni);
				e.setNombreCompleto(nombre);
				e.setTestimonio(testimonio);
				
				testigos.add(e);
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

		return testigos;
	}
	
	

	public int conseguirIDTestigo(int DNITestigo) {
		Connection conn = null;
		int id=0;
		try {
			conn = DriverManager.getConnection(url(),usuario(),contrasenia());
			PreparedStatement pStmt = conn.prepareStatement("SELECT idTestigos FROM testigos where DNITestigos = ?;");
			pStmt.setInt(1, DNITestigo);
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
	
	public Testigo traerTestigoPorID(int idTestigo) {
		Connection conn = null;
	
		Testigo e = new Testigo();
		
		try {
			conn = DriverManager.getConnection(url(),usuario(),contrasenia());
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM testigos where idTestigos = ?");
			pStmt.setInt(1, idTestigo);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				int dni = rs.getInt(2);
				String nombre = rs.getString(3);
				String testimonio = rs.getString(4);
				e.setDniTestigo(dni);
				e.setNombreCompleto(nombre);
				e.setTestimonio(testimonio);
				System.out.println(pStmt.toString());
				System.out.println("testigo id:" + idTestigo + " dni: " + dni);
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
