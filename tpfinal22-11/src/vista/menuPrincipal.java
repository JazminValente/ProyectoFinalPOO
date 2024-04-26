package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Font;

public class menuPrincipal extends JPanel {
	/**
	 * Create the panel.
	 */
	public menuPrincipal() {
		setBackground(new Color(0, 0, 28));
		setForeground(new Color(255, 255, 255));
		setLayout(null);
		setBounds(0,0,600,400);
		
		JLabel menu = new JLabel("SELECCIONE UNA OPCION");
		menu.setFont(new Font("Tahoma", Font.BOLD, 14));
		menu.setHorizontalAlignment(SwingConstants.CENTER);
		menu.setForeground(new Color(255, 255, 255));
		menu.setBounds(0, 82, 600, 36);
		add(menu);
		
		JButton botonCentro = new JButton("CCDTyE");
		botonCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
					marco.setContentPane( new centroOpciones());
					marco.validate();
			}
		});
		botonCentro.setBounds(168, 156, 272, 36);
		add(botonCentro);
		
		JButton botonDesaparecidos = new JButton("DESAPARECIDOS");
		botonDesaparecidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new desaparecidoOpciones());
				marco.validate();
			}
		});
		botonDesaparecidos.setBounds(168, 203, 272, 36);
		add(botonDesaparecidos);
		
		
		
		JButton botonTestigos = new JButton("TESTIGOS\r\n");
		botonTestigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new testigoOpciones());
				marco.validate();
			}
		});
		botonTestigos.setBounds(168, 250, 272, 36);
		add(botonTestigos);
		
	}
	
}
