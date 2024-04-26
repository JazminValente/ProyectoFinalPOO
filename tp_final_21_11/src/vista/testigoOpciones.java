package vista;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import java.awt.Font;

public class testigoOpciones extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public testigoOpciones() {
		setBackground(new Color(0, 0, 28));
		setLayout(null);
		setBounds(0,0,600,400);
		JLabel lblNewLabel = new JLabel("SELECCIONE UNA OPCION");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(0, 50, 600, 48);
		add(lblNewLabel);
		
		JButton bConsultarEliminarT = new JButton("CONSULTAR");
		bConsultarEliminarT.setFont(new Font("Tahoma", Font.BOLD, 11));
		bConsultarEliminarT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
					marco.setContentPane( new testigoEliminarConsultar());
					marco.validate();
			}
		});
		bConsultarEliminarT.setBounds(165, 157, 264, 40);
		add(bConsultarEliminarT);
		
		JButton bAtras = new JButton("ATRAS");
		bAtras.setFont(new Font("Tahoma", Font.BOLD, 11));
		bAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
					marco.setContentPane( new menuPrincipal());
					marco.validate();
			}
		});
		bAtras.setBounds(165, 230, 264, 40);
		add(bAtras);

	}

}
