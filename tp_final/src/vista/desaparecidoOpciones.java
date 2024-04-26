package vista;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import java.awt.Font;

public class desaparecidoOpciones extends JPanel {

	/**
	 * Create the panel.
	 */
	public desaparecidoOpciones() {
		setBackground(new Color(0, 0, 28));
		setLayout(null);
		setBounds(0,0,600,400);
		JLabel lblNewLabel = new JLabel("SELECCIONE UNA OPCION");
		lblNewLabel.setFont(new Font("Sitka Heading", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(0, 80, 600, 26);
		add(lblNewLabel);
		
		JButton bAgregarModificar = new JButton("AGREGAR\r\n");
		bAgregarModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		bAgregarModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new desaparecidoTipoAgregarModificar());
				marco.validate();
			}
		});
		bAgregarModificar.setBounds(194, 163, 207, 33);
		add(bAgregarModificar);
		
		JButton bAcceder = new JButton("CONSULTAR");
		bAcceder.setFont(new Font("Tahoma", Font.BOLD, 11));
		bAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new desaparecidoTipoConsultar());
				marco.validate();
			}
		});
		bAcceder.setBounds(194, 207, 207, 33);
		add(bAcceder);
		
		JButton bAtras = new JButton("ATRAS");
		bAtras.setFont(new Font("Tahoma", Font.BOLD, 11));
		bAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new menuPrincipal());
				marco.validate();
			}
		});
		bAtras.setBounds(194, 251, 207, 33);
		add(bAtras);

	}
}
