package Perceptron;


import java.util.Scanner;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	
	
	public static void calculate(Perceptron perceptron) {
		
		System.out.println("Enter training dataset file name:");
		String trainfilename = sc.nextLine();
		//String trainfilename = "train2.txt";
		
		System.out.println("Enter test dataset file name:");
		String testfilename = sc.nextLine();
		//String testfilename = "test1.txt";
		
		
		System.out.println("Enter learning rate:");
		String learningRate = sc.nextLine();
		Double rate = Double.parseDouble(learningRate);
		
		System.out.println("Enter iteration number:");
		String iteration = sc.nextLine();
		int iterate = Integer.parseInt(iteration);
		
		
		//Double rate = 0.1;
				    		
		 
	    double[][] trainingData = DataSetup.loadData(trainfilename);
	    //DataSetup.printData(trainingData);


	    System.out.println("___________________________________________________");        
	    double[][] testingData = DataSetup.loadData(testfilename);
	    //DataSetup.printData(testingData);

	    
	    

	    System.out.println("======================================================");
	    perceptron = new Perceptron(rate, iterate, trainingData, testingData);
	    perceptron.train();
	    perceptron.printWeights();
	    perceptron.predict();
	    perceptron = null;
	    
		
	}
	
	public static void addInput(Perceptron perceptron) {
		
		System.out.println("Enter new features:");
		String line = sc.nextLine();
		String[] split = line.split(",");
		double[] feature = new double[split.length+1];
		for (int i = 0; i < split.length; i++) {
			feature[i] = Double.parseDouble(split[i]);
		}
		
		
		System.out.println("Enter new label:");
		String label = sc.nextLine();
		double[][] testingData = new double[1][feature.length];
		testingData[0]= feature;
		
		if (label.equalsIgnoreCase("Iris-virginica")) {
           label = "1";
           testingData[0][4] = Double.parseDouble(label);
        }
        else if (label.equalsIgnoreCase("Iris-versicolor")) {
            label = "0";
            testingData[0][4] = Double.parseDouble(label);
        }
		
		testingData[0][2] = Double.parseDouble(label);
				
				
		System.out.println("Enter training dataset file name:");
		String trainfilename = sc.nextLine();
		double[][] trainingData = DataSetup.loadData(trainfilename);
		
		System.out.println("Enter learning rate:");
		String learningRate = sc.nextLine();
		Double rate = Double.parseDouble(learningRate);
		
		System.out.println("Enter iteration number:");
		String iteration = sc.nextLine();
		int iterate = Integer.parseInt(iteration);
		
		System.out.println("======================================================");
	    perceptron = new Perceptron(rate, iterate, trainingData, testingData);
	    perceptron.train();
	    perceptron.printWeights();
	    perceptron.predict();
	    perceptron = null;
		
		
		
		
	}
	
	 public static void main(String[] args) {
		 
		 Perceptron perceptron = null;
	
		 	System.out.println("[0] testing by a new input");
	    	System.out.println("[1] testing by a test file");
	    	System.out.println("[-1] exit");
	    	String input = sc.nextLine();
	    	int answer = Integer.parseInt(input);
	    	if(answer == 0) {    		
	    			addInput(perceptron);	    		
	    	}    	
	    	else if(answer == 1) {
	    		calculate(perceptron);	    		
	    	}
	    	else if(answer == -1) {
	    		System.exit(0);	    		
	    	}
	    	else {
	    		System.out.println("invalid value!");
	    	}
	    	}    	
    
    
	 }


