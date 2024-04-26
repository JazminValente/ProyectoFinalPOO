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

public class desaparecidoTipoAgregarModificar extends JPanel {

	/**
	 * Create the panel.
	 */
	public desaparecidoTipoAgregarModificar() {
		setBackground(new Color(0, 0, 28));
		setLayout(null);
		setBounds(0,0,600,400);
		JLabel lblNewLabel = new JLabel("EL DETENIDO ES:");
		lblNewLabel.setFont(new Font("Sitka Heading", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(0, 55, 600, 33);
		add(lblNewLabel);
		
		JButton bIdentificado = new JButton("IDENTIFICADO\r\n");
		bIdentificado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new identificadoAgregarModificar());
				marco.validate();
			}
		});
		bIdentificado.setBounds(167, 165, 269, 50);
		add(bIdentificado);
		
		JButton bNoi = new JButton("NO IDENTIFICADO\r\n");
		bNoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new noIdentificadoAgregarModificar());
				marco.validate();
			}
		});
		bNoi.setBounds(167, 226, 269, 50);
		add(bNoi);
		
		JButton bAtras = new JButton("ATRAS");
		bAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new desaparecidoOpciones());
				marco.validate();
			}
		});

		bAtras.setBounds(166, 287, 270, 50);
		add(bAtras);

	}

}
