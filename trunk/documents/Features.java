package documents;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//rappresenta le features del dataset
public class Features {
	
	//associa l'id ad ogni feature
	private Map<Integer,String> _id2feat;
	//associa ogni feature al suo id
	private Map<String,Integer> _feat2id;
	//associa ad ogni feature il numero di documenti in cui appare
	private Map<Integer,Integer> f2freq;
	//oggetto che rappresenta il dataset
	private DocumentsSet ds;
	//lista di stopwords
	private List<String> stopwords;
	
	
	public Features() {
		this.f2freq = new HashMap<Integer, Integer>();
	}
	
	public List<String> getStopwords() {
		return stopwords;
	}

	public void setStopwords(List<String> stopwords) {
		this.stopwords = stopwords;
	}

	public void setI2f(Map<Integer, String> i2f) {
		this._id2feat = i2f;
	}


	public void setF2i(Map<String, Integer> f2i) {
		this._feat2id = f2i;
	}


	public void setF2freq(Map<Integer, Integer> f2freq) {
		this.f2freq = f2freq;
	}


	public DocumentsSet getDs() {
		return ds;
	}


	public void setDs(DocumentsSet ds) {
		this.ds = ds;
	}


	public Map<Integer, Integer> getF2freq() {
		return f2freq;
	}


	public Map<Integer, String> getI2f() {
		return _id2feat;
	}

	public Map<String, Integer> getF2i() {
		return _feat2id;
	}
	
	//crea la lista di stopwords da file
	public List<String> createListStopwords() throws IOException {
		List<String> stop = new LinkedList<String>();
		FileInputStream fstream = new FileInputStream("D:/progetto/stopwords.txt");
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        while ((strLine = br.readLine()) != null) {
        	stop.add(strLine);
        }
        this.stopwords = stop;
        return stop;
	}

	// crea la mappa delle frequenze
	public void createFeat2Freq() {
		int app;
		Map<Integer,Integer> f2freq =  new HashMap<Integer, Integer>();
		for (Integer doc : ds.getD2f_train().keySet()) {
			for (Integer feat : ds.getD2f_train().get(doc)) {
				if(!f2freq.containsKey(feat))
					f2freq.put(feat, 1);
				else {
					app = f2freq.get(feat);
					app++;
					f2freq.put(feat, app);
				}
			}
		}
		this.f2freq = f2freq;
	}
	
	//seleziona features che appaiono in più di due documenti
	public void selectFeaturesByFrequency(int number) {
		createFeat2Freq();
		Map<Integer,List<Integer>> index_new = new HashMap<Integer, List<Integer>>();
		Map<Integer,List<String>> index_new_str = new HashMap<Integer, List<String>>();
		Map<Integer,String> i2f_new = new HashMap<Integer, String>();
		Map<String,Integer> f2i_new = new HashMap<String, Integer>();
		int i = 0;
		int j = 0;
		while(i<_id2feat.size()) {
			if(this.f2freq.get(i)>=number) {
				i2f_new.put(j, _id2feat.get(i));
				f2i_new.put(_id2feat.get(i), j);
				j++;
			}
			i++;
		}
		this._feat2id = f2i_new;
		this._id2feat = i2f_new;
		for (Integer doc : ds.getD2f_train_str().keySet()) {
			index_new.put(doc, new LinkedList<Integer>());
			index_new_str.put(doc, new LinkedList<String>());
			for (String item : ds.getD2f_train_str().get(doc)) {
				if(_feat2id.get(item)!=null) {
					index_new_str.get(doc).add(item);
					index_new.get(doc).add(_feat2id.get(item));
				}
			}
			Collections.sort(index_new.get(doc));
		}
		this.ds.setD2f_train_str(index_new_str);
		this.ds.setD2f2(index_new);
		System.out.println("num features " + _id2feat.size());
		System.out.println("num features " + f2freq.size());
		System.out.println("num selected features  " + _feat2id.size());
	}
}
