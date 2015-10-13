package datagathering;

public class Symbol {
	private String name;
	private short dir;
	private float bid, ask, high, low;
	
	public Symbol(String name, float bid, float ask, float high, float low, short dir) {
		this.name = name;
		
		this.dir = dir;
		
		this.bid = bid;
		this.ask = ask;
		this.high = high;
		this.low = low;
	}
	
	public short getDirectory() {
		return this.dir;
	}
	
	public String getName() {
		return this.name;
	}

	public float getBid() {
		return bid;
	}

	public float getAsk() {
		return ask;
	}

	public float getHigh() {
		return high;
	}

	public float getLow() {
		return low;
	}

}
