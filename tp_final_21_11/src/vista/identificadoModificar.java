package vista;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JFrame;

public class identificadoModificar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public identificadoModificar() {
		setBackground(new Color(0, 0, 28));
		setLayout(null);
		setBounds(0,0,600,400);
		JLabel lblNewLabel = new JLabel("Nombre completo");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 39, 83, 14);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(103, 36, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Agregar biografía");
		lblNewLabel_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 107, 94, 14);
		add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(103, 104, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("¿Cuando fue visto por ult. vez?");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(199, 39, 155, 14);
		add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(354, 36, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Adjuntar material audiovisual.");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(199, 107, 155, 14);
		add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(354, 104, 86, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		JButton bAtras = new JButton("<--- Atras");
		bAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new desaparecidoTipoAgregarModificar());
				marco.validate();
			}
		});
		bAtras.setBounds(37, 208, 89, 23);
		add(bAtras);
		
		JButton btnNewButton = new JButton("Modificar desaparecido");
		btnNewButton.setBounds(238, 208, 155, 23);
		add(btnNewButton);

	}
}
