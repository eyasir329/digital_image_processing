package random_forest;

import java.util.HashMap;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.model.RandomForestModel;
import org.apache.spark.mllib.util.MLUtils;

import scala.Tuple2;

public class Test {
	public static HashMap<Double, Double> LabelMap = new HashMap<>();

	@SuppressWarnings({ "resource", "serial" })
	public static void main(String[] args) {
		// Need to change
		System.setProperty("hadoop.home.dir", "/usr/local/hadoop/");

		SparkConf sparkConf = new SparkConf().setAppName("JavaRandomForestClassificationExample").setMaster("local[*]");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
		// Load and parse the data file.
		String datapath = "data/testHisto.txt";
		JavaRDD<LabeledPoint> data = MLUtils.loadLibSVMFile(jsc.sc(), datapath).toJavaRDD();
		// Split the data into training and test sets (100% held out for testing)
		JavaRDD<LabeledPoint>[] splits = data.randomSplit(new double[] { 0.0, 1.0 });
		JavaRDD<LabeledPoint> testData = splits[1];

		// load model
		RandomForestModel sameModel = RandomForestModel.load(jsc.sc(),
				"target/tmp/myRandomForestClassificationModelTrain3");

		// Evaluate model on test instances and compute test error
		JavaPairRDD<Double, Double> predictionAndLabel = testData
				.mapToPair(p -> new Tuple2<Double, Double>(sameModel.predict(p.features()), p.label()));

		Double testErr = (double) (predictionAndLabel.filter(
				new Function<Tuple2<Double, Double>, Boolean>() {
					int t1 = 0, t2 = 0, t3 = 0, t = 1;

					@Override
					public Boolean call(Tuple2<Double, Double> pl) {
						//System.out.println(pl._1() + " " + pl._2());
						t++;
						if (pl._1() == 0) {
							t1++;
						} else if (pl._1() == 1) {
							t2++;
						} else if (pl._1() == 2) {
							t3++;
						}
						System.out.println(t);
						if(t==4){
							if(t1>t2&&t1>t3){
								System.out.println("Zia");
							}else if(t2>t3&&t2>t3){
								System.out.println("Imtius");
							}else if(t3>t2&&t3>t1){
								System.out.println("Sochcha");
							} else {
							System.out.println("Not Found in our database");
						}
						}
						return !pl._1().equals(pl._2());
					}
				}).count() / testData.count());

		// print

	}// main end

}
