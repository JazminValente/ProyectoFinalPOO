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
import javax.swing.table.TableModel;

import modelo.Identificado;
import modelo.Testigo;
import dao.IdentificadosDAO;
import dao.TestigosDAO;

public class identificadoModificarTestigo extends JPanel {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel modelTestigosDisponibles;
	private DefaultTableModel modelTestigoQueYaTiene;
	private Identificado identificado = null;
	private ArrayList<Testigo> TestigosDisponibles = new ArrayList<Testigo>();
	private ArrayList<Testigo> TestigoQueYaTiene = new ArrayList<Testigo>();
	private JTable tableTestigosDisponibles;
	private JTable tableTestigoQueYaTiene;

	/**
	 * Create the panel.
	 */
	public identificadoModificarTestigo(Identificado ident) {
		setBackground(new Color(2, 0, 34));
		setBounds(0, 0, 600, 400);
		setLayout(null);
		this.identificado = ident;
		modelTestigosDisponibles = new DefaultTableModel();
		modelTestigosDisponibles.addColumn("DNI Testigo");
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
		datosTestigosDisponibles(ident);
		// tableTestigosDisponibles.setBounds(10, 103, 256, 232);
		// add(tableTestigosDisponibles);

		JScrollPane scrollPaneCentrosEnQueEstuvo = new JScrollPane();
		scrollPaneCentrosEnQueEstuvo.setBounds(334, 103, 256, 232);
		add(scrollPaneCentrosEnQueEstuvo);

		tableTestigoQueYaTiene = new JTable();
		scrollPaneCentrosEnQueEstuvo.setViewportView(tableTestigoQueYaTiene);
		tableTestigoQueYaTiene.setModel(modelTestigoQueYaTiene);
		datosTestigoQueYaTiene(ident);
		// tableTestigoQueYaTiene.setBounds(334, 103, 256, 232);
		// add(tableTestigoQueYaTiene);
		validate();

		JButton btnVincularTestigo = new JButton("VINCULAR TESTIGO CON DETENIDO");
		btnVincularTestigo.setBounds(207, 346, 207, 23);
		btnVincularTestigo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				IdentificadosDAO identificadoDao = new IdentificadosDAO();
				Testigo testigoAVincular = obtenerTestigosDisponible();
				System.out.println("Testigo a vincular: " + testigoAVincular.getDniTestigo());
				boolean seVincula = identificadoDao.agregarTestigoAlIdentificado(identificado,
						testigoAVincular.getDniTestigo());
				System.out.println(seVincula);
				identificado.setTestigoDelDetenido(testigoAVincular);
				datosTestigosDisponibles(ident);
				datosTestigoQueYaTiene(ident);
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

	private void datosTestigosDisponibles(Identificado ident) {
		asignarIdentificado(ident);
		TestigosDAO tDAO = new TestigosDAO();
		TestigosDisponibles = tDAO.traerTodos();
		modelTestigosDisponibles.setRowCount(0);
		if (ident.getTestigoDelDetenido() != null) {
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

	private void datosTestigoQueYaTiene(Identificado ident) {
		asignarIdentificado(ident);
		modelTestigoQueYaTiene.setRowCount(0);

		TestigosDAO testigoDao = new TestigosDAO();
		IdentificadosDAO identificadoDao = new IdentificadosDAO();
		int idTestigo = identificadoDao.conseguirIdDeUnTestigoDeUnDetenido(ident.getDniDetenido());
		Testigo testigo = testigoDao.traerTestigoPorID(idTestigo);
		ident.setTestigoDelDetenido(testigo);

		if (ident.getTestigoDelDetenido() != null) {
			int dni = ident.getTestigoDelDetenido().getDniTestigo();
			String nombre = ident.getTestigoDelDetenido().getNombreCompleto();
			String testimonio = ident.getTestigoDelDetenido().getTestimonio();

			modelTestigoQueYaTiene.addRow(new Object[] { dni, nombre, testimonio });
			System.out.println("Testigo que ya fue agregado: " + dni);
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

	private void asignarIdentificado(Identificado i) {
		identificado = i;
	}
}
