package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class ErrorDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	public ErrorDialog() {
		JButton button = new JButton("Testbutton!");
		JLabel error  = new JLabel("Cannot connect to internet");
		this.setSize(200, 100);
		this.setResizable(false);
		this.setLayout(null);
		button.setVisible(true);
		
		this.add(error);
		this.add(button);
		
		error.setBounds(0, 0, 200, 25);
		button.setBounds(0, 25, 200, 25);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(-1);
			}
		});
		this.setVisible(true);
	}
}
