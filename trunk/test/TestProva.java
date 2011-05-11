package test;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import classifier.ClassifierBuilder;
import classifier.IClassifier;
import classifier.Item;
import classifier.WeightedMajority;
import classifier.WekaClassifier;

import junit.framework.TestCase;

public class TestProva extends TestCase {
	
	@Test
	public void testProva() throws Exception {
		List<IClassifier> classifiers = new LinkedList<IClassifier>();
		ClassifierBuilder cb = new ClassifierBuilder();
		WekaClassifier wc1 = cb.retrieveClassifier("weka.classifiers.bayes.NaiveBayes");
		WekaClassifier wc2 = cb.retrieveClassifier("weka.classifiers.trees.J48");
		WekaClassifier wc3 = cb.retrieveClassifier("weka.classifiers.functions.VotedPerceptron");
		classifiers.add(wc1);
		classifiers.add(wc2);
		classifiers.add(wc3);
		WeightedMajority wm  = new WeightedMajority(classifiers);
		Item ist = wm.weightedClassify("i am very sad");
		System.out.println(ist.getPolarity());
		Item ist2 = wm.weightedClassify("i am very sad");
		System.out.println(ist2.getPolarity());
		ist.setTarget("4");
		ist2.setTarget("4");
		wm.setTarget(ist);
		wm.setTarget(ist2);
		System.out.println(wm.get_cl2weight().get(1) + " " + wm.get_cl2weight().get(2) + " " + wm.get_cl2weight().get(3));
	}
}
