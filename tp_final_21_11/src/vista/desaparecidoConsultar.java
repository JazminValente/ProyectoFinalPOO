package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class desaparecidoConsultar extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable tabla;
	private JLabel desaparecidoEliminado;
	/**
	 * Create the panel.
	 */
	public desaparecidoConsultar() {
		setBackground(new Color(0, 0, 28));
		setForeground(new Color(18, 4, 36));
		setLayout(null);
		setBounds(0,0,600,400);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(115, 23, 367, 245);
		add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setRowHeaderView(scrollPane_1);
		
		JButton bEliminardesaparecido = new JButton("ELIMINAR\r\n");
		bEliminardesaparecido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desaparecidoEliminado = new JLabel("Desaparecido eliminado exitosamente");
				desaparecidoEliminado.setForeground(new Color(255, 255, 255));
				desaparecidoEliminado.setBounds(262, 275, 142, 14);
				add(desaparecidoEliminado);
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((JComponent)e.getSource());
				revalidate();
				repaint();
				marco.validate();
			}
		});
		bEliminardesaparecido.setBounds(343, 279, 139, 37);
		add(bEliminardesaparecido);
		
		JButton bAtras = new JButton("ATRAS\r\n");
		bAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new desaparecidoOpciones());
				marco.validate();
			}
		});
		bAtras.setBounds(115, 279, 139, 37);
		add(bAtras);
	}

}
