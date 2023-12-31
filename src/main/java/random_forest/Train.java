package random_forest;

import java.util.HashMap;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.RandomForest;
import org.apache.spark.mllib.tree.model.RandomForestModel;
import org.apache.spark.mllib.util.MLUtils;

import scala.Tuple2;

public class Train {
	public static HashMap<Double, Double> LabelMap = new HashMap<>();

	@SuppressWarnings({ "resource", "serial" })
	public static void main(String[] args) {
		// Need to change
		System.setProperty("hadoop.home.dir", "C:\\hadoop");

		SparkConf sparkConf = new SparkConf().setAppName("JavaRandomForestClassificationExample").setMaster("local[*]");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
		// Load and parse the data file.
		String datapath = "data/d2Histo.txt";
		JavaRDD<LabeledPoint> data = MLUtils.loadLibSVMFile(jsc.sc(), datapath).toJavaRDD();
		// Split the data into training and test sets (0% held out for testing)
		JavaRDD<LabeledPoint>[] splits = data.randomSplit(new double[] { 1.0, 0.0 });
		JavaRDD<LabeledPoint> trainingData = splits[0];
		
		// Train a RandomForest model.
		// Empty categoricalFeaturesInfo indicates all features are continuous.
		int numClasses = 3;
		Map<Integer, Integer> categoricalFeaturesInfo = new HashMap<Integer, Integer>();
		int numTrees = 10; // Use more in practice.
		String featureSubsetStrategy = "auto"; // Let the algorithm choose.
		String impurity = "gini";
		int maxDepth = 5;
		int maxBins = 32;
		int seed = 12345;

		RandomForestModel model = RandomForest.trainClassifier(trainingData, numClasses, categoricalFeaturesInfo,
				numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins, seed);

		
		System.out.println("Learned classification forest model:\n" + model.toDebugString());
		
		// Save model
		model.save(jsc.sc(), "target/tmp/d2HistoModel");
	}

}
