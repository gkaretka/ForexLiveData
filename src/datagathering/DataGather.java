package datagathering;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DataGather {
	public int error = 0;
	public Document getDataInXML() {
		Document doc = null; 
		try {
			doc = Jsoup.connect("http://rates.fxcm.com/RatesXML").get();
		} catch (IOException e) {
			doc = null;
		}
		return doc;
	}
	
	public Map<String,Symbol> ParseSymbol(Document doc) {
		Elements nodeList;
		Map<String,Symbol> symbols;
		
		try {
			nodeList =  doc.getElementsByTag("Rate");
			symbols = new HashMap<String,Symbol>();
		} catch (Exception e) {
			this.error = -1;
			return null;
		}
		
		for (int i = 0; i < nodeList.size(); i++) {
			String name = nodeList.get(i).getAllElements().attr("symbol").toString();
			symbols.put(name,new Symbol(name, Float.parseFloat(nodeList.get(i).child(0).text()),
							 				  Float.parseFloat(nodeList.get(i).child(1).text()),
							 				  Float.parseFloat(nodeList.get(i).child(2).text()),
							 				  Float.parseFloat(nodeList.get(i).child(3).text()),
							 				  Short.parseShort(nodeList.get(i).child(4).text())));
		}
		return symbols;
	}
}
