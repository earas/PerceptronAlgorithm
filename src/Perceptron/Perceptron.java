package Perceptron;
import java.util.Arrays;

public class Perceptron {

		double[][] trainData;
	    double[][] testData;
	    double learningRate;
	    int iterations;
	    private double[] weights;

	    Perceptron(double learningRate, int iterations, double[][] trainData, double[][] testData) {
	        this.learningRate = learningRate;
	        this.iterations = iterations;
	        this.trainData = trainData;
	        this.testData = testData;
	        weights = new double[trainData[0].length-1];
	        
	        for (int i = 0 ; i < trainData[0].length-1 ; i++ ) {
				this.weights[i] = 0.5 ;
			}
	        
	        System.out.println(Arrays.toString(weights));
	        
	    }
	    
	    public void printWeights() {
	    	System.out.println(Arrays.toString(weights));
	    }
	    
	    
	    public double calculateOutput(double[] input) {
			double sum = 0;
			
			//System.out.println("train data"+Arrays.toString(input));
			
			for (int i = 0 ; i < this.weights.length ; i++) {
			     sum = sum + input[i] * this.weights[i];
			     
			     //System.out.println("process: "+input[i] +" * " +this.weights[i]);
			}
			
			sum = sum + this.learningRate;
			
			return activationFunction(sum,1);
		}
		
		public double activationFunction(double sum, int actFuncType) {
			if (actFuncType == 1) { // Step function
				if (sum > 0) {
					return 1;
				}
				else {
					return 0;
				}
			}
			
			return -1;
		}
				
		
		public void train() {
			
			
			 for (int i = 0; i < iterations; i++) { // each iteration
				 int numberOfMisClassifications = 0;
		            System.out.println("Starting iteration " + (i + 1));
		            for (double[] row: trainData) { // each weight		            	
		            	double label = row[row.length - 1];
		            	double predictedLabel = calculateOutput(row);
		            	if (predictedLabel > label) {
		                    numberOfMisClassifications++;		           
		                    decreaseWeightVector(row);
		                }
		                else if (predictedLabel < label) {
		                    numberOfMisClassifications++;		                    
		                    increaseWeightVector(row);
		                }
		            	
		            }
		            System.out.println("Number of Misclassifications: " + numberOfMisClassifications);
				 
			 }
			
		}
		
		
		private void decreaseWeightVector(double[] dataPoint) {
	        for (int i = 0; i < dataPoint.length - 1; i++) {
	            //System.out.println("############# " + dataPoint[i]);
	            weights[i] -= dataPoint[i] * learningRate;
	        }
	        //System.out.println("-last: "+Arrays.toString(weights));
	    }

	    private void increaseWeightVector(double[] dataPoint) {
	        for (int i = 0; i < dataPoint.length - 1; i++) {
	            //System.out.println("************ " + dataPoint[i]);
	            weights[i] += dataPoint[i] * learningRate;
	        }
	        //System.out.println("+last: "+Arrays.toString(weights));
	    }
		
	    public void predict() {
	        /* Classify the testing data. Remember our algorithm hasn't
	         * seen this data before. Hopefully, it "learned" the difference
	         * between the two flowers */
	    	
	    	String one = "1";
	        String zero = "0";
	        
	    	int numeric = DataSetup.numeric();
	    	
	    	if(numeric == 0) {
	    		one = "Iris-virginica";
	            zero = "Iris-versicolor";
	    	}
	        
	        
	        
	        int numberOfCorrectPredictions = 0;
	        int numberOfWrongPredictions = 0;
	        double TP = 0;
	        double TN = 0;
	        double FP = 0;
	        double FN = 0;

	        for (double[] row : testData) {
	            double label = row[row.length - 1];
	           // System.out.println("label: "+label);
	            double prediction = calculateOutput(row);
	          //  System.out.println("prediction: "+prediction);
	            boolean correct = prediction == label;
	          
	            
	            if (correct && prediction == 1) {
	                numberOfCorrectPredictions++;
	                TP++;
	                System.out.println("+Predicted: " + one + "  | Truth: " + one);
	            }
	            else if (correct && prediction == 0) {
	                numberOfCorrectPredictions++;
	                TN++;
	                System.out.println("-Predicted: " + zero + "  | Truth: " + zero);
	            	            
	            }
	            else if (!correct && prediction == 0) {
	            	System.out.println("!-Predicted: " + zero + "  | Truth: " + one);
	                numberOfWrongPredictions++;
	                FN++;
	            	
	            }
	            else if (!correct && prediction == 1) {
	            	System.out.println("!+Predicted: " + zero + "  | Truth: " + one);
	                numberOfWrongPredictions++;
	                FP++;
	            	
	            }
	            else {
	                System.out.println("???Predicted: " + zero + "  | Truth: " + one);
	                numberOfWrongPredictions++;
	            }
	            
	           // System.out.println("TP: "+TP);
	           // System.out.println("FP: "+FP);
	        }
	        
	      System.out.println("True Positive: "+TP +" - True Negative: "+TN +" - False Positive: "+FP+" - False Negative: "+FN);
	        calculateAccuracy(numberOfCorrectPredictions);	        
	        calculatePrecision(TP,FP);
	        calculateRecall(TP,FN);

	    }
	    
	    public void calculateAccuracy(int correct) {
	        double percentCorrect = ((double) correct / testData.length) * 100;
	        System.out.println("Accuracy " + percentCorrect + "%");
	      

	    }
	    
	    public void calculatePrecision(double TP, double FP) {
	    	double precison = TP/(TP+FP);
	    	 System.out.println("Precision "+precison);
	    }
	    
	    public void calculateRecall(double TP, double FN) {
	    	
	    	double recall = TP/(TP+FN);
	    	 System.out.println("Recall "+recall);
	    	
	    }
}
