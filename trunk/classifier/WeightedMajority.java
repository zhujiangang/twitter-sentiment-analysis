package classifier;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


//implementazione dell'algoritmo weighted majority
public class WeightedMajority {
	
	//mappa che associa un id ad ogni classificatore
	private Map<Integer, IClassifier> _id2cl;
	//mappa che associa ad ogni id un peso
	private Map<Integer,Double> _cl2weight;
	private final double _beta = 0.5;
	

	public Map<Integer, Double> get_cl2weight() {
		return _cl2weight;
	}

	//costruttore che per ora prende tre classificatori già addestrati
	//associa ad ognuno peso 1
	public WeightedMajority(List<IClassifier> classifiers) throws Exception {
		_id2cl = new HashMap<Integer, IClassifier>();
		_cl2weight = new HashMap<Integer, Double>();
		int i = 1;
		for (IClassifier classifier : classifiers) {
			_id2cl.put(i, classifier);
			_cl2weight.put(i, 1.);
			i++;
		}
	}
	
	//classifica un documento e costruisce e restituisce un oggetto istanza corrispondente
	public Item weightedClassify(String stringa) throws Exception {
		Item ist = new Item(stringa);
		Map<Integer,String> cl2pol = new HashMap<Integer, String>();
		int i = 1;
		double pos = 0;
		double neg = 0;
		while(i<=_id2cl.size()) {
			String pol = _id2cl.get(i).classify(stringa);
			if(pol.equals("0")) {
				neg = neg + _cl2weight.get(i);
				cl2pol.put(i, "0");
			}
			else {
				pos = pos + _cl2weight.get(i);
				cl2pol.put(i, "4");
			}
			i++;
		}
		ist.setCl2pol(cl2pol);
		if(pos>neg)
			ist.setPolarity("4");
		else if(pos<neg)
			ist.setPolarity("0");
		else {
			double n = Math.random();
			if(n>=0.5)
				ist.setPolarity("0");
			else
				ist.setPolarity("4");
		}
		return ist;
	}
	
	
	//prende un istanza con il target e la polarità già settati
	//controlla le risposte di ogni classificatore e penalizza i classificatori che hanno sbagliato
	public void setTarget(Item istanza) {
		if(istanza.getTarget()!=null) {
			int i = 1;
			while(i<=3) {
				if(!istanza.getCl2pol().get(i).equals(istanza.getTarget())) {
					double oldweight = _cl2weight.get(i);
					_cl2weight.put(i, oldweight*_beta);
				}
				i++;
			}
		}
	}
	
	//calcola la precisione del classificatore usando le istanze di test
	public void calculatePrecision(String path_input) {
		try{
			int i = 1;
			int correct = 0;
			Preprocesser pr = new Preprocesser();
			Item temp;
            FileInputStream fstream = new FileInputStream(path_input);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            String str, pol;
            while ((strLine = br.readLine()) != null) {
        		String[] items = strLine.split(";;");
        		str = items[5].toLowerCase();
        		pol = items[0];
        		temp = weightedClassify(pr.preprocessDocument(str));
        		temp.setTarget(pol);
        		setTarget(temp);
        		if(temp.getPolarity().equals(temp.getTarget()))
        			correct++;
        		System.out.println(correct/i);
        		i++;
            }
            in.close();
        }catch (Exception e){
            System.err.println("Errore: " + e.getMessage());
        }
	}
}
