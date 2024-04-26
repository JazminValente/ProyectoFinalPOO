package vista;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import dao.CCDTyEDAO;
import modelo.CCDTyE;
import modelo.Fuerzas;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class centroEliminarConsultar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tabla;
	private JLabel centroEliminado;
	private DefaultTableModel mm ;
	private ArrayList<CCDTyE> centros;
	/**
	 * Create the panel.
	 */
	public centroEliminarConsultar() {
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
		
		mm.addColumn("Nombre");
		mm.addColumn("Ubicacion");
		mm.addColumn("Fecha Inicio");
		mm.addColumn("Fecha Fin");
		mm.addColumn("Fuerza/s");
		tabla.setModel(mm);
		
		JButton btnNewButton_1 = new JButton("CARGAR DATOS");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		datos();
        	}
        });
        btnNewButton_1.setBounds(248, 328, 117, 41);
        add(btnNewButton_1);
		
		JButton bEliminarCentro = new JButton("ELIMINAR");
		bEliminarCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int a = tabla.getSelectedRow();
				String ubi = (String) tabla.getModel().getValueAt(a, 1);
				CCDTyEDAO cDAO = new CCDTyEDAO();
				cDAO.eliminarCentro(ubi);
				
				centroEliminado = new JLabel("Centro eliminado exitosamente");
				centroEliminado.setForeground(new Color(255, 255, 255));
				centroEliminado.setBounds(262, 275, 142, 14);
				add(centroEliminado);
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((JComponent)e.getSource());
				revalidate();
				repaint();
				marco.validate();
			}
		});
		bEliminarCentro.setBounds(427, 276, 109, 41);
		add(bEliminarCentro);
		
		JButton bAtras = new JButton("ATRAS");
		bAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new centroOpciones());
				marco.validate();
			}
		});
		bAtras.setBounds(48, 276, 117, 41);
		add(bAtras);
		
		JButton bModificarCentro = new JButton("MODIFICAR");
		bModificarCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
						marco.setContentPane(new centroAgregarModificar(obtenerCentro()));
						marco.validate();
			}
		});
		bModificarCentro.setBounds(308, 276, 109, 41);
		add(bModificarCentro);
		
		JButton bCrear = new JButton("CREAR");
		bCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
						marco.setContentPane(new centroAgregarModificar());
						marco.validate();
			}
		});
		bCrear.setBounds(181, 276, 117, 41);
		add(bCrear);
	}
	
	private void datos() {
		CCDTyEDAO c = new CCDTyEDAO();
		centros= c.traerTodos();
		mm.setRowCount(0);
		for(CCDTyE centro : centros)
		{
			ArrayList<String> fuerzasDelCentro = centro.getFuerzasEnControl();
			String fuerzas= "";
			for(String f: fuerzasDelCentro) {
				fuerzas = fuerzas + "-" + f; 
			}
			
			Object[] fila = new Object[] {
					centro.getNombre_centro(),
					centro.getUbicacion(),
					centro.getFecha_inicio(),
					centro.getFecha_fin(),
					fuerzas
				};
		mm.addRow(fila);
		}
		
	}
	public CCDTyE obtenerCentro()
	{
		int filaSeleccionada = tabla.getSelectedRow();
		return centros.get(filaSeleccionada);
	}
}
