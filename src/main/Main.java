package main;

import java.util.Map;

import org.jsoup.nodes.Document;

import datagathering.DataGather;
import datagathering.Symbol;

import gui.RowOfData;
import gui.ErrorDialog;
import gui.MainWindow;

public class Main {
	static RowOfData[] rowOfData;
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Main main = new Main();
		DataGather dataGather = new DataGather();
		Document data = dataGather.getDataInXML();
		Map<String,Symbol> symbols = dataGather.ParseSymbol(data);
		
		if (dataGather.error == -1) {
			ErrorDialog er = new ErrorDialog();
			return;
		}
		
		rowOfData = main.createRows(symbols);
		Updater updater = new Updater();
		updater.start();
		
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
