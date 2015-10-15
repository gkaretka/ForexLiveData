package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RowOfData extends JPanel {
	private static final long serialVersionUID = -6898973668217988298L;
	public JLabel name, ask, bid, high, low, dir;
	public GridLayout layout = new GridLayout(1,6);
	public ArrayList<Component> components;
	
	public RowOfData(String name, String ask, String bid, String high, String low, String dir) {	
		this.setBackground(Color.darkGray);
		
		this.name = new JLabel(name);
		this.ask = new JLabel(ask);
		this.bid = new JLabel(bid);
		this.high = new JLabel(high);
		this.low = new JLabel(low);
		this.dir = new JLabel(dir);
		
		this.components = new ArrayList<Component>();
		
		this.components.add(this.name);
		this.components.add(this.ask);
		this.components.add(this.bid);
		this.components.add(this.high);
		this.components.add(this.low);
		
		Color color;
		Integer dirInt = Integer.parseInt(dir);
		if (dirInt == 0) {
			color = Color.black;
		} else if (dirInt == -1) {
			color = Color.red;
		} else {
			color = Color.green;
		}
		
		this.dir.setForeground(Color.BLACK);
		
		for (Component c : components) {
			c.setForeground(color);
		}
		
		this.setLayout(layout);
		
		this.add(this.name);
		this.add(this.ask);
		this.add(this.bid);
		this.add(this.high);
		this.add(this.low);
	}
}

