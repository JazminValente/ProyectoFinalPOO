package vista;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import dao.CCDTyEDAO;
import dao.IdentificadosDAO;
import modelo.CCDTyE;
import modelo.Identificado;
import modelo.Identificado;

public class IdentificadosModificarCentros extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableCentrosDisponibles;
	private JTable tableCentrosEnLosQueEstuvo;
	private DefaultTableModel modelCentrosDisponibles;
	private DefaultTableModel modelCentrosEnLosQueYaEstuvo;
	private Identificado identificado = null;
	private ArrayList<CCDTyE> centroDisponibles = new ArrayList<CCDTyE>();
	private ArrayList<CCDTyE> centrosEnLosQueYaEstuvo = new ArrayList<CCDTyE>();
	/**
	 * Create the panel.
	 */
	public IdentificadosModificarCentros(Identificado identificado) {
		this.identificado = identificado;
		modelCentrosDisponibles = new DefaultTableModel();
		modelCentrosDisponibles.addColumn("Ubicacion de Centro");
		modelCentrosEnLosQueYaEstuvo = new DefaultTableModel();
		modelCentrosEnLosQueYaEstuvo.addColumn("Ubicacion de Centro");
		CCDTyEDAO centroDao = new CCDTyEDAO();
		centroDisponibles = centroDao.traerTodos();
		centrosEnLosQueYaEstuvo = identificado.getCentrosEnLosQueEstuvo();
		setBackground(new Color(0, 0, 28));
		setLayout(null);
		
		JLabel lblCentrosDisponibles = new JLabel("Centros disponibles");
		lblCentrosDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCentrosDisponibles.setForeground(new Color(255, 255, 255));
		lblCentrosDisponibles.setBounds(84, 57, 151, 14);
		add(lblCentrosDisponibles);
		
		JLabel lblCentrosEnLosQueEstuvo = new JLabel("Centros en los que estuvo");
		lblCentrosEnLosQueEstuvo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCentrosEnLosQueEstuvo.setForeground(new Color(255, 255, 255));
		lblCentrosEnLosQueEstuvo.setBounds(372, 57, 194, 14);
		add(lblCentrosEnLosQueEstuvo);
		
		JButton btnVincularCentro = new JButton("Vincular Centro con detenido");
		btnVincularCentro.setBounds(52, 325, 183, 23);
		btnVincularCentro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IdentificadosDAO identificadoDao = new IdentificadosDAO();
				CCDTyE centroAVincular = obtenerCentroDisponible();
				System.out.println("centro a vincular: " + centroAVincular.getUbicacion());
				boolean seVincula = identificadoDao.agregarCentroAlIdentificado(identificado, centroAVincular);
				System.out.println(seVincula);
				identificado.añadirCentro(centroAVincular);
				datosCentrosDisponibles();
				datosCentrosEnLosQueYaEstuvo();
			}
		});
		add(btnVincularCentro);
		
		JButton btnDesvincularCentro = new JButton("Desvincular centro");
		btnDesvincularCentro.setBounds(372, 325, 194, 23);
		btnDesvincularCentro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				IdentificadosDAO identificadoDao = new IdentificadosDAO();
				CCDTyE centroADesvincular = obtenerCentroVinculado();
				System.out.println("centro a desvincular: " + centroADesvincular.getUbicacion());
				boolean seDesvincula = identificadoDao.desvincularCentroDelIdentificado(identificado, centroADesvincular);
				System.out.println("desvinculado: " + seDesvincula);
				identificado.desvincularCentro(centroADesvincular);
				centrosEnLosQueYaEstuvo = identificado.getCentrosEnLosQueEstuvo();
				centroDisponibles = centroDao.traerTodos();
				datosCentrosDisponibles();
				datosCentrosEnLosQueYaEstuvo();
			}
		});
		add(btnDesvincularCentro);
		
		
		JScrollPane scrollPaneCentrosDisponibles = new JScrollPane();
		scrollPaneCentrosDisponibles.setBounds(10, 82, 256, 232);
		add(scrollPaneCentrosDisponibles);
		
		tableCentrosDisponibles = new JTable();
		scrollPaneCentrosDisponibles.setViewportView(tableCentrosDisponibles);
		tableCentrosDisponibles.setModel(modelCentrosDisponibles);
		datosCentrosDisponibles();
		//tableCentrosDisponibles.setBounds(10, 103, 256, 232);
		//add(tableCentrosDisponibles);
		
		JScrollPane scrollPaneCentrosEnQueEstuvo = new JScrollPane();
		scrollPaneCentrosEnQueEstuvo.setBounds(334, 82, 256, 232);
		add(scrollPaneCentrosEnQueEstuvo);
		
		tableCentrosEnLosQueEstuvo = new JTable();
		scrollPaneCentrosEnQueEstuvo.setViewportView(tableCentrosEnLosQueEstuvo);
		tableCentrosEnLosQueEstuvo.setModel(modelCentrosEnLosQueYaEstuvo);
		datosCentrosEnLosQueYaEstuvo();
		//tableCentrosEnLosQueEstuvo.setBounds(334, 103, 256, 232);
		//add(tableCentrosEnLosQueEstuvo);
		
		validate();

	}
	private void datosCentrosDisponibles() {
        modelCentrosDisponibles.setRowCount(0);
        if (centrosEnLosQueYaEstuvo.size() == 0) {
            for (CCDTyE c : centroDisponibles) {
                modelCentrosDisponibles.addRow(new Object[] { c.getUbicacion() });
            }
        } else {

            for (CCDTyE c : centroDisponibles) {
                for (CCDTyE centro : identificado.getCentrosEnLosQueEstuvo()) {
                    if (!c.getUbicacion().equals(centro.getUbicacion())) {
                        modelCentrosDisponibles.addRow(new Object[] { c.getUbicacion() });
                    }
                }

            }
        }
        
        JButton bAtras = new JButton("ATRAS");
		bAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new identificadoEliminarConsultar());
				marco.validate();
				}
			});

			bAtras.setBounds(225, 359, 158, 23);
			add(bAtras);
		}
	
	private void datosCentrosEnLosQueYaEstuvo() {
		modelCentrosEnLosQueYaEstuvo.setRowCount(0);
		for(CCDTyE c : centrosEnLosQueYaEstuvo) {
			String ubicacion = c.getUbicacion();
			modelCentrosEnLosQueYaEstuvo.addRow(new Object[] {
					ubicacion
				});
			System.out.println("centro en el que ya estuvo agregado: " +c.getUbicacion());
			}
		}
	
	public CCDTyE obtenerCentroDisponible()
	{
		int filaSeleccionada = tableCentrosDisponibles.getSelectedRow();
		return centroDisponibles.get(filaSeleccionada);
	}
	public CCDTyE obtenerCentroVinculado()
	{
		int filaSeleccionada = tableCentrosEnLosQueEstuvo.getSelectedRow();
		return centrosEnLosQueYaEstuvo.get(filaSeleccionada);
	}
}