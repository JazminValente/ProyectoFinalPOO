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

import dao.CCDTyEDAO;
import dao.IdentificadosDAO;
import dao.NoIdentificadosDAO;
import modelo.CCDTyE;
import modelo.NoIdentificados;

public class noIdentificadoModificarCentros extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableCentrosDisponibles;
	private JTable tableCentrosEnLosQueEstuvo;
	private DefaultTableModel modelCentrosDisponibles;
	private DefaultTableModel modelCentrosEnLosQueYaEstuvo;
	private NoIdentificados noIdentificado = null;
	private ArrayList<CCDTyE> centroDisponibles = new ArrayList<CCDTyE>();
	private ArrayList<CCDTyE> centrosEnLosQueYaEstuvo = new ArrayList<CCDTyE>();

	/**
	 * Create the panel.
	 */
	public noIdentificadoModificarCentros(NoIdentificados noIdent) {
		this.noIdentificado = noIdent;
		modelCentrosDisponibles = new DefaultTableModel();
		modelCentrosDisponibles.addColumn("Ubicacion de Centro");
		modelCentrosEnLosQueYaEstuvo = new DefaultTableModel();
		modelCentrosEnLosQueYaEstuvo.addColumn("Ubicacion de Centro");
		CCDTyEDAO centroDao = new CCDTyEDAO();
		centroDisponibles = centroDao.traerTodos();
		centrosEnLosQueYaEstuvo = noIdentificado.getCentrosEnLosQueEstuvo();
		setBackground(new Color(0, 0, 28));
		setLayout(null);
		
		JLabel lblCentrosDisponibles = new JLabel("Centros disponibles");
		lblCentrosDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCentrosDisponibles.setForeground(new Color(255, 255, 255));
		lblCentrosDisponibles.setBounds(84, 45, 151, 14);
		add(lblCentrosDisponibles);
		
		JLabel lblCentrosEnLosQueEstuvo = new JLabel("Centros en los que estuvo");
		lblCentrosEnLosQueEstuvo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCentrosEnLosQueEstuvo.setForeground(new Color(255, 255, 255));
		lblCentrosEnLosQueEstuvo.setBounds(372, 45, 194, 14);
		add(lblCentrosEnLosQueEstuvo);
		
		JButton btnVincularCentro = new JButton("Vincular Centro con detenido");
		btnVincularCentro.setBounds(52, 313, 183, 23);
		btnVincularCentro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NoIdentificadosDAO noIdentificadoDao = new NoIdentificadosDAO();
				CCDTyE centroAVincular = obtenerCentroDisponible();
				System.out.println("centro a vincular: " + centroAVincular.getUbicacion());
				boolean seVincula = noIdentificadoDao.agregarCentroAlNoIdentificado(noIdentificado, centroAVincular);
				System.out.println(seVincula);
				//identificado.añadirCentro(centroAVincular);
				datosCentrosDisponibles();
				datosCentrosEnLosQueYaEstuvo();
			}
		});
		add(btnVincularCentro);
		
		JButton btnDesvincularCentro = new JButton("Desvincular centro");
		btnDesvincularCentro.setBounds(372, 313, 194, 23);
		btnDesvincularCentro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NoIdentificadosDAO noIdentificadoDao = new NoIdentificadosDAO();
				CCDTyE centroADesvincular = obtenerCentroVinculado();
				System.out.println("centro a desvincular: " + centroADesvincular.getUbicacion());
				boolean seDesvincula = noIdentificadoDao.desvincularCentroDelNoIdentificado(noIdentificado, centroADesvincular);
				centrosEnLosQueYaEstuvo.remove(centroADesvincular);
				System.out.println("desvinculado: " + seDesvincula);
				//identificado.desvincularCentro(centroADesvincular);
				datosCentrosDisponibles();
				datosCentrosEnLosQueYaEstuvo();
			}
		});
		add(btnDesvincularCentro);
		
		JButton bAtras = new JButton("ATRAS");
		bAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new noIdentificadoEliminarConsultar());
				marco.validate();
			}
		});

		bAtras.setBounds(10, 366, 158, 23);
		add(bAtras);
		
		JScrollPane scrollPaneCentrosDisponibles = new JScrollPane();
		scrollPaneCentrosDisponibles.setBounds(10, 70, 256, 232);
		add(scrollPaneCentrosDisponibles);
		
		tableCentrosDisponibles = new JTable();
		scrollPaneCentrosDisponibles.setViewportView(tableCentrosDisponibles);
		tableCentrosDisponibles.setModel(modelCentrosDisponibles);
		datosCentrosDisponibles();
		//tableCentrosDisponibles.setBounds(10, 103, 256, 232);
		//add(tableCentrosDisponibles);
		
		JScrollPane scrollPaneCentrosEnQueEstuvo = new JScrollPane();
		scrollPaneCentrosEnQueEstuvo.setBounds(334, 70, 256, 232);
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
        if (this.centrosEnLosQueYaEstuvo.size() == 0) {
            for (CCDTyE c : centroDisponibles) {
            	System.out.println("1");
                modelCentrosDisponibles.addRow(new Object[] { c.getUbicacion() });
            }
        } else {
    		for (CCDTyE c : centroDisponibles) {
    			if(!centrosEnLosQueYaEstuvo.contains(c)) {
    				modelCentrosDisponibles.addRow(new Object[] { c.getUbicacion() });
    			}
    		}
        }
	}
	private void datosCentrosEnLosQueYaEstuvo() {
		System.out.println("cantidad de centros en los que ya estuvo: " + centrosEnLosQueYaEstuvo.size());
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
