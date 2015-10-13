package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jsoup.nodes.Document;

import datagathering.DataGather;
import datagathering.Symbol;

public class Main {
	static RowOfData[] rowOfData;
	
	public static void main(String[] args) {
		Main main = new Main();
		DataGather dataGather = new DataGather();
		Document data = dataGather.getDataInXML();
		Map<String,Symbol> symbols = dataGather.ParseSymbol(data);
		rowOfData = main.createRows(symbols);
		
		Thread update = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					DataGather dataGather = new DataGather();
					Document data = dataGather.getDataInXML();
					Map<String,Symbol> symbols = dataGather.ParseSymbol(data);
					
					Symbol[] s = new Symbol[symbols.size()];
					s = symbols.values().toArray(s);
					Update(s);
				}
			}
			
			public void Update(Symbol[] s) {
				int counter = 0;
				for (RowOfData rowOfData : Main.rowOfData) {
					System.out.println(s[counter].getAsk());
					rowOfData.ask.setText(Float.toString(s[counter].getAsk()));
					rowOfData.bid.setText(Float.toString(s[counter].getBid()));
					rowOfData.high.setText(Float.toString(s[counter].getHigh()));
					rowOfData.low.setText(Float.toString(s[counter].getLow()));
					rowOfData.dir.setText(Short.toString(s[counter].getDirectory()));
					
					UpdateColors(s[counter], rowOfData.components);
					++counter;
				}
			}
			
			public void UpdateColors(Symbol s, ArrayList<Component> components) {
				Color color = null;
				int dir = s.getDirectory();
				System.out.println(dir);
				if (dir == 0) {
					color = Color.black;
				} else if (dir == -1) {
					color = Color.red;
				} else {
					color = Color.green;
				}
				for (Component c : components) {
					c.setForeground(color);
				}
			}
		};
		
		update.start();
		
		@SuppressWarnings("unused")
		MainWindow mainWindow = new MainWindow("Stock viewer", rowOfData);
	}
	
	public RowOfData[] createRows(Map<String,Symbol> symbols) {
		RowOfData[] rowOfData = new RowOfData[symbols.size()];
		
		int counter = 0;
		for (Map.Entry<String, Symbol> entry : symbols.entrySet()) {
			Symbol symbol = entry.getValue();
			
			String name = symbol.getName();
			Float ask = symbol.getAsk();
			Float bid = symbol.getBid();
			Float high = symbol.getHigh();
			Float low = symbol.getLow();
			Short dir = symbol.getDirectory();
			
			rowOfData[counter] = new RowOfData(name, ask.toString(), bid.toString(),
					high.toString(), low.toString(), dir.toString());
			++counter;
		}
		return rowOfData; 
	}

}

class RowOfData extends JPanel {
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

class MainWindow extends JFrame {
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
