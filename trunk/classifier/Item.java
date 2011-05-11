package classifier;

import java.util.HashMap;
import java.util.Map;

//rappresenta un documento nell'algoritmo weighted majority
public class Item {
	
	private String _text;
	private String _polarity;
	private String _target;
	private Map<Integer,String> _cl2pol;
	
	public Item(String text) {
		this._text = text;
		_cl2pol = new HashMap<Integer, String>();
	}

	public String getText() {
		return _text;
	}

	public void setText(String text) {
		this._text = text;
	}

	public String getPolarity() {
		return _polarity;
	}

	public void setPolarity(String polarity) {
		this._polarity = polarity;
	}

	public Map<Integer, String> getCl2pol() {
		return _cl2pol;
	}

	public void setCl2pol(Map<Integer, String> cl2pol) {
		this._cl2pol = cl2pol;
	}

	public String getTarget() {
		return _target;
	}

	public void setTarget(String target) {
		this._target = target;
	}
	
	
}
