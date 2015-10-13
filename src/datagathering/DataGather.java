package datagathering;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DataGather {
	
	public Document getDataInXML() {
		Document doc = null; 
		try {
			doc = Jsoup.connect("http://rates.fxcm.com/RatesXML").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	public Map<String,Symbol> ParseSymbol(Document doc) {
		Elements nodeList =  doc.getElementsByTag("Rate");
		Map<String,Symbol> symbols = new HashMap<String,Symbol>();
		
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
