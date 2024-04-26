package vista;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Bienvenida extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Bienvenida() {
		setBackground(new Color(0, 0, 28));
		setForeground(new Color(255, 255, 255));
		setLayout(null);
		setBounds(0,0,600,400);
		
		JLabel MVJ = new JLabel(new ImageIcon("MVJ_logo.png"));
		MVJ.setBounds(83, -11, 430, 291);
		add(MVJ);
		
		JButton btnNewButton = new JButton("INGRESAR\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new menuPrincipal());
				marco.validate();
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Sitka Heading", Font.BOLD, 14));
		btnNewButton.setBounds(207, 265, 188, 42);
		add(btnNewButton);

	}
}
