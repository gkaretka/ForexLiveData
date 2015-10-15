package gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = -7815680249266318390L;
	
	public MainWindow(String name, JPanel[] panels) {
		super(name);
		
		JPanel mainPanel = new JPanel();
		JScrollPane scroll = new JScrollPane();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(500, 500);
		mainPanel.setLayout(new GridLayout(panels.length,1));
		mainPanel.setAutoscrolls(true);
		
		scroll.setViewportView(mainPanel);
		this.add(scroll);
		
		for (JPanel panel : panels) {
			mainPanel.add(panel);
		}
		this.setVisible(true);
		
	}
}