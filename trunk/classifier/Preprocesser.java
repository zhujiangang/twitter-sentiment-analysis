package classifier;

import java.util.StringTokenizer;

import util.Options;

//classe per il preprocessamento dei documenti
public class Preprocesser {
	private Options opt;
	
	public Preprocesser(Options opt) {
		this.opt = opt;
	}

	public Preprocesser() {
		this.opt = new Options();
	}

	//sostituisce i termini che iniziano con "@" con "USERNAME"
	public String removeUsername(String item) {
		String result = "";
		if(item.startsWith("@"))
			result =  "USERNAME";
		else
			result = item;
		return result;
	}
	
	//rimuove gli url sostituendoli con "URL"
	public String removeUrl(String item) {
		String result = "";
		if(item.startsWith("http://") || item.startsWith("www."))
			result = "URL";
		else
			result = item;
		return result;
	}
	
	//individua le emoticon e le sostituisce con due termini predefiniti
	public String replaceEmoticons(String item) {
		String result = item;
		result = result.replaceAll(":[)]", "_SMILEHAPPY_");
		result = result.replaceAll(";[)]", "_SMILEHAPPY_");
		result = result.replaceAll(":-[)]", "_SMILEHAPPY_");
		result = result.replaceAll(";-[)]", "_SMILEHAPPY_");
		result = result.replaceAll(":d", "_SMILEHAPPY_");
		result = result.replaceAll(";d", "_SMILEHAPPY_");
		result = result.replaceAll("=[)]", "_SMILEHAPPY_");
		result = result.replaceAll("\\^_\\^", "_SMILEHAPPY_");
		result = result.replaceAll(":[(]", "_SMILESAD_");
		result = result.replaceAll(":-[(]", "_SMILESAD_");
		return result;
	}
	
	//rimuove le emoticon
	public String removeEmoticons(String item) {
		String result = item;
		result = result.replaceAll(":[)]", "");
		result = result.replaceAll(";[)]", "");
		result = result.replaceAll(":-[)]", "");
		result = result.replaceAll(";-[)]", "");
		result = result.replaceAll(":d", "");
		result = result.replaceAll(";d", "");
		result = result.replaceAll("=[)]", "");
		result = result.replaceAll("\\^_\\^", "");
		result = result.replaceAll(":[(]", "");
		result = result.replaceAll(":-[(]", "");
		return result;
	}
	
	//individua i termini con più di due ripetizioni di caratteri
	//sostituisce le ripetizioni con soli due caratteri ripetuti
	public String removeRepeatedCharacters(String item) {
		String result = item;
		result = item.replaceAll("(.)\\1{2,100}", "$1$1");
		return result;
	}
	
	//individua simboli presenti nel dataset
	public String removeSymbols(String item) {
		String result = item;
		result = result.replaceAll("&quot;", "\"");
		result = result.replaceAll("&amp;", "&");
		result = result.replaceAll("&lt;", "<");
		result = result.replaceAll("&gt;", ">");
		return result;
	}
	
	//individua i termini contenenti risate
	public String recognizeLaugh(String item) {
		String result = item;
		if(result.contains("haha"))
			result = "STRLAUGH";
		else if(result.contains("ahah")) 
			result = "STRLAUGH";
		return result;
	}
	
	//preprocessa un intero documento tramite tokenizzatori e i metodi precedenti
	public String preprocessDocument(String item) {
		String result_fin = "";
		String result = item;
		StringTokenizer st1 = new StringTokenizer(result, " ,?![]");
		while (st1.hasMoreTokens()) {
			String str = st1.nextToken();
			result = removeUrl(str);
			if(!opt.isRemoveEmoticons())
				result = replaceEmoticons(result);
			else
				result = removeEmoticons(result);
			StringTokenizer st2 = new StringTokenizer(result, ".:#)(_");
			String tmp = "";
			String tmp2 = "";
			while (st2.hasMoreTokens()) {
				tmp = st2.nextToken();
				tmp = recognizeLaugh(tmp);
				tmp2 = tmp2 + " " + removeUsername(tmp); 
			}
			result = tmp2;
			result = result.replaceAll("lu+v+", "love");
			result = removeRepeatedCharacters(result);
			//result = removeSymbols(result);
			result_fin = result_fin + result;
		}
		return result_fin;
	}
}
