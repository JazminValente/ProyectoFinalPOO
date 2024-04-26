package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingConstants;
import java.awt.Font;

public class desaparecidoTipoConsultar extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public desaparecidoTipoConsultar() {
		setBackground(new Color(0, 0, 28));
		setLayout(null);
		setBounds(0,0,600,400);
		JLabel lblNewLabel = new JLabel("EL DETENIDO ES:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(0, 80, 600, 33);
		add(lblNewLabel);
		
		JButton bIdentificado = new JButton("IDENTIFICADO");
		bIdentificado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new identificadoEliminarConsultar());
				marco.validate();
			}
		});
		bIdentificado.setBounds(217, 159, 163, 33);
		add(bIdentificado);
		
		JButton bNoi = new JButton("NO IDENTIFICADO\r\n");
		bNoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new noIdentificadoEliminarConsultar());
				marco.validate();
			}
		});
		bNoi.setBounds(217, 203, 163, 33);
		add(bNoi);
		
		JButton bAtras = new JButton("ATRAS");
		bAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new desaparecidoOpciones());
				marco.validate();
			}
		});

		bAtras.setBounds(217, 247, 163, 33);
		add(bAtras);

	}
}
