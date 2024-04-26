package vista;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import javax.swing.SwingConstants;

public class centroOpciones extends JPanel {

	/**
	 * Create the panel.
	 */
	public centroOpciones() {
		setForeground(new Color(0, 0, 0));
		setBackground(new Color(0, 0, 28));
		setLayout(null);
		setBounds(0,0,600,400);
		JLabel textoMCentro = new JLabel("SELECCIONE UNA OPCION");
		textoMCentro.setHorizontalAlignment(SwingConstants.CENTER);
		textoMCentro.setForeground(new Color(255, 255, 255));
		textoMCentro.setFont(new Font("Sitka Heading", Font.BOLD, 20));
		textoMCentro.setBounds(-10, 65, 600, 23);
		add(textoMCentro);
		
		JButton botonConsultarC = new JButton("CONSULTAR");
		botonConsultarC.setBackground(new Color(255, 255, 255));
				botonConsultarC.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
							marco.setContentPane( new centroEliminarConsultar());
							marco.validate();
			}
		});
		botonConsultarC.setFont(new Font("Sitka Heading", Font.BOLD, 14));
		botonConsultarC.setBounds(185, 147, 211, 23);
		add(botonConsultarC);
		
		JButton bAtras = new JButton("ATRAS");
		bAtras.setFont(new Font("Sitka Heading", Font.BOLD, 14));
		bAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new menuPrincipal());
				marco.validate();
			}
		});
		bAtras.setBounds(185, 207, 211, 23);
		add(bAtras);

	}
}
