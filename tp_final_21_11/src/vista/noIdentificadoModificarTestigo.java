package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import dao.IdentificadosDAO;
import dao.TestigosDAO;
import modelo.Identificado;
import modelo.Testigo;
import modelo.NoIdentificados;
import dao.NoIdentificadosDAO;

public class noIdentificadoModificarTestigo extends JPanel {
	private static final long serialVersionUID = 1L;

	private DefaultTableModel modelTestigosDisponibles;
	private DefaultTableModel modelTestigoQueYaTiene;
	private NoIdentificados noIdentificado;
	private ArrayList<Testigo> TestigosDisponibles = new ArrayList<Testigo>();
	private ArrayList<Testigo> TestigoQueYaTiene = new ArrayList<Testigo>();
	private JTable tableTestigosDisponibles;
	private JTable tableTestigoQueYaTiene;

	
	/**
	 * Create the panel.
	 */
	public noIdentificadoModificarTestigo(NoIdentificados noIdent) {
		setBackground(new Color(2, 0, 34));
		setBounds(0, 0, 600, 400);
		setLayout(null);
		
		TestigosDAO tDAO = new TestigosDAO();
		TestigosDisponibles = tDAO.traerTodos();
		
		this.noIdentificado = noIdent;
		modelTestigosDisponibles = new DefaultTableModel();
		modelTestigosDisponibles.addColumn("DNI Testigos");
		modelTestigosDisponibles.addColumn("Nombre");
		modelTestigosDisponibles.addColumn("Testimonio");
		modelTestigoQueYaTiene = new DefaultTableModel();
		modelTestigoQueYaTiene.addColumn("DNI Testigo");
		modelTestigoQueYaTiene.addColumn("Nombre");
		modelTestigoQueYaTiene.addColumn("Testimonio");
		JLabel lblTestigosDisponibles = new JLabel("Testigos disponibles");
		lblTestigosDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTestigosDisponibles.setForeground(new Color(255, 255, 255));
		lblTestigosDisponibles.setBounds(84, 78, 151, 23);
		add(lblTestigosDisponibles);

		JLabel lblTestigoQueYaTiene = new JLabel("Testigo que ya tiene");
		lblTestigoQueYaTiene.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTestigoQueYaTiene.setForeground(new Color(255, 255, 255));
		lblTestigoQueYaTiene.setBounds(372, 78, 194, 23);
		add(lblTestigoQueYaTiene);

		JScrollPane scrollPaneTestigosDisponibles = new JScrollPane();
		scrollPaneTestigosDisponibles.setBounds(10, 103, 256, 232);
		add(scrollPaneTestigosDisponibles);

		tableTestigosDisponibles = new JTable();
		scrollPaneTestigosDisponibles.setViewportView(tableTestigosDisponibles);
		tableTestigosDisponibles.setModel(modelTestigosDisponibles);
		datosTestigosDisponibles(noIdentificado);
		// tableTestigosDisponibles.setBounds(10, 103, 256, 232);
		// add(tableTestigosDisponibles);

		JScrollPane scrollPaneCentrosEnQueEstuvo = new JScrollPane();
		scrollPaneCentrosEnQueEstuvo.setBounds(334, 103, 256, 232);
		add(scrollPaneCentrosEnQueEstuvo);

		tableTestigoQueYaTiene = new JTable();
		scrollPaneCentrosEnQueEstuvo.setViewportView(tableTestigoQueYaTiene);
		tableTestigoQueYaTiene.setModel(modelTestigoQueYaTiene);
		datosTestigoQueYaTiene(noIdentificado);
		// tableTestigoQueYaTiene.setBounds(334, 103, 256, 232);
		// add(tableTestigoQueYaTiene);
		validate();

		JButton btnVincularTestigo = new JButton("VINCULAR TESTIGO CON DETENIDO");
		btnVincularTestigo.setBounds(207, 346, 207, 23);
		btnVincularTestigo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NoIdentificadosDAO noIdentificadoDao = new NoIdentificadosDAO();
				Testigo testigoAVincular = obtenerTestigosDisponible();
				System.out.println("Testigo a vincular: " + testigoAVincular.getDniTestigo());
				boolean seVincula = noIdentificadoDao.agregarTestigoAlNoIdentificado(noIdentificado,
						testigoAVincular.getDniTestigo());
				System.out.println(seVincula);
				noIdentificado.setTestigoDelDetenido(testigoAVincular);
				datosTestigosDisponibles(noIdentificado);
				datosTestigoQueYaTiene(noIdentificado);
			}
		});
		add(btnVincularTestigo);

		JButton bAtras = new JButton("ATRAS");
		bAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new identificadoEliminarConsultar());
				marco.validate();
			}
		});

		bAtras.setBounds(10, 366, 158, 23);
		add(bAtras);
		

	}
	
	private void datosTestigosDisponibles(NoIdentificados noIdent) {
		asignarNoIdentificado(noIdent);
		
		modelTestigosDisponibles.setRowCount(0);
		if (noIdent.getTestigoDelDetenido() != null) {
			for (Testigo t : TestigosDisponibles) {
				
					int dni = t.getDniTestigo();
					String nombre = t.getNombreCompleto();
					String testimonio = t.getTestimonio();
					modelTestigosDisponibles.addRow(new Object[] { dni, nombre, testimonio });
				
			}
		} else {

			for (Testigo t : TestigosDisponibles) {
				int dni = t.getDniTestigo();
				String nombre = t.getNombreCompleto();
				String testimonio = t.getTestimonio();
					modelTestigosDisponibles.addRow(new Object[] { dni, nombre, testimonio });
			}
		}
	}

	private void datosTestigoQueYaTiene(NoIdentificados noIdent) {
		modelTestigoQueYaTiene.setRowCount(0);
		System.out.println("cargar testigo q ya tiene");
		TestigosDAO testigoDao = new TestigosDAO();
		NoIdentificadosDAO noIdentificadoDao = new NoIdentificadosDAO();
		int idTestigo = noIdentificadoDao.conseguirIDTestigoDeUnDetenido(noIdent.getApodo());
		Testigo testigo = testigoDao.traerTestigoPorID(idTestigo);
		noIdent.setTestigoDelDetenido(testigo);

		if (noIdent.getTestigoDelDetenido() != null) {
			int dni =testigo.getDniTestigo();
			String nombre = testigo.getNombreCompleto();
			String testimonio = testigo.getTestimonio();

			modelTestigoQueYaTiene.addRow(new Object[] { dni, nombre, testimonio });
			System.out.println("Testigo que ya fue agregado: " + dni);
		}
		else {
			System.out.println("Testigo nulo");
		}
	}

	public Testigo obtenerTestigosDisponible() {
		int filaSeleccionada = tableTestigosDisponibles.getSelectedRow();
		return TestigosDisponibles.get(filaSeleccionada);
	}

	public Testigo obtenerTestigoVinculado() {
		int filaSeleccionada = tableTestigoQueYaTiene.getSelectedRow();
		return TestigoQueYaTiene.get(filaSeleccionada);
	}

	private void asignarNoIdentificado(NoIdentificados i) {
		this.noIdentificado = i;
	}

}
