package app;

import javax.swing.JFrame;

import vista.Bienvenida;

public class App {

	public static void main(String[] args) {
		JFrame marco = new JFrame();
		marco.setVisible(true);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setBounds(0, 0, 600, 400);
		marco.setContentPane(new Bienvenida());
		marco.validate();
	}

}
