package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import dao.CCDTyEDAO;
import dao.TestigosDAO;
import modelo.CCDTyE;
import modelo.Testigo;

public class testigoEliminarConsultar extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTable tabla;
	private JLabel desaparecidoEliminado;
	private DefaultTableModel mm;
	private ArrayList<Testigo> testigo;
	/**
	 * Create the panel.
	 */
	public testigoEliminarConsultar() {
		setBackground(new Color(0, 0, 28));
		setForeground(new Color(18, 4, 36));
		setLayout(null);
		setBounds(0,0,600,400);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 11, 498, 254);
		add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setRowHeaderView(scrollPane_1);
		
		mm = new DefaultTableModel();

        mm.addColumn("DNI");
        mm.addColumn("Nombre Completo");
        mm.addColumn("Testimonio");

        tabla.setModel(mm);
		
        datos();
        
		JButton bEliminarTestigo = new JButton("ELIMINAR\r\n");
		bEliminarTestigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int a = tabla.getSelectedRow();
				int dni = (int) tabla.getModel().getValueAt(a, 0);
				TestigosDAO tDAO = new TestigosDAO();
				tDAO.eliminarTestigo(dni);
				
				desaparecidoEliminado = new JLabel("testigo eliminado exitosamente");
				desaparecidoEliminado.setForeground(new Color(255, 255, 255));
				desaparecidoEliminado.setBounds(262, 275, 142, 14);
				add(desaparecidoEliminado);
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((JComponent)e.getSource());
				revalidate();
				repaint();
				marco.validate();
			}
		});
		bEliminarTestigo.setBounds(429, 276, 117, 41);
		add(bEliminarTestigo);
		
		JButton bAtras = new JButton("ATRAS");
		bAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new testigoOpciones());
				marco.validate();
			}
		});
		bAtras.setBounds(48, 276, 117, 41);
		add(bAtras);
		
		JButton btnAñadirTestigo = new JButton("AGREGAR\r\n");
		btnAñadirTestigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
				marco.setContentPane(new testigoAgregarModificar());
				marco.validate();
			}
		});
		btnAñadirTestigo.setBounds(175, 276, 117, 41);
		add(btnAñadirTestigo);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
				marco.setContentPane(new testigoAgregarModificar(obtenerTestigos()));
				marco.validate();
			}
		});
		btnModificar.setBounds(302, 276, 117, 41);
		add(btnModificar);
	}
	
	private void datos() {
		TestigosDAO tDAO = new TestigosDAO();
		testigo = tDAO.traerTodos();
		mm.setRowCount(0);
		for(Testigo testigo : testigo)
		{
			Object[] fila = new Object[] {
					testigo.getDniTestigo(),
					testigo.getNombreCompleto(),
					testigo.getTestimonio(),
				};
		mm.addRow(fila);
		}
	}
	
	public Testigo obtenerTestigos()
	{
		int filaSeleccionada = tabla.getSelectedRow();
		return testigo.get(filaSeleccionada);
	}
}
