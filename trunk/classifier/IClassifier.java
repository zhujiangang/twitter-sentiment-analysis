package classifier;

import java.io.FileNotFoundException;
import java.io.IOException;

//interfaccia per un classificatore generico
public interface IClassifier {
	//addestra il classificatore
	public void train() throws Exception;
	//testa il classificatore
	public void evaluate() throws Exception;
	//classifica un generico documento restituendo la classe
	public String classify(String stringa) throws FileNotFoundException, IOException, Exception;
}
