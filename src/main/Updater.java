package main;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Map;

import org.jsoup.nodes.Document;

import datagathering.DataGather;
import datagathering.Symbol;
import gui.RowOfData;

public class Updater extends Thread {
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
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
}
