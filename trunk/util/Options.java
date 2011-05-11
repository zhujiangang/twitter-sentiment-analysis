package util;

import java.util.LinkedList;
import java.util.List;

import weka.classifiers.Classifier;
import classifier.IClassifier;
import classifier.WekaClassifier;

public class Options {
	
	private int numFeatures;
	private boolean selectedFeaturesByFrequency;
	private boolean removeEmoticons;
	private String classifierName;
	private WekaClassifier constructedClassifier;
	private List<String> wmClassifiersName;
	
	public List<String> getWmClassifiersName() {
		return wmClassifiersName;
	}

	public void setWmClassifiersName(List<String> wmClassifiersName) {
		this.wmClassifiersName = wmClassifiersName;
	}

	public WekaClassifier getConstructedClassifier() {
		return constructedClassifier;
	}

	public void setConstructedClassifier(WekaClassifier constructedClassifier) {
		this.constructedClassifier = constructedClassifier;
	}

	public Options() {
		this.numFeatures = 0;
		this.selectedFeaturesByFrequency = false;
		this.removeEmoticons = false;
		this.wmClassifiersName = new LinkedList<String>();
	}

	public String getClassifierName() {
		return classifierName;
	}

	public void setClassifierName(String classifierName) {
		this.classifierName = classifierName;
	}



	public int getNumFeatures() {
		return numFeatures;
	}
	public void setNumFeatures(int numFeatures) {
		this.numFeatures = numFeatures;
	}
	public boolean isSelectedFeaturesByFrequency() {
		return selectedFeaturesByFrequency;
	}
	public void setSelectedFeaturesByFrequency(boolean selectedFeaturesByFrequency) {
		this.selectedFeaturesByFrequency = selectedFeaturesByFrequency;
	}
	public boolean isRemoveEmoticons() {
		return removeEmoticons;
	}
	public void setRemoveEmoticons(boolean removeEmoticons) {
		this.removeEmoticons = removeEmoticons;
	}
	
	
	
}
