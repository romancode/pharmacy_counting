package com.insight.core;

public class CommonUtils {
	
	public static final String INPUT_DATA_FILE = "../input/itcont.txt";
	
	public static final String OUTPUT_DATA_FILE = "../output/top_cost_drug.txt";
	public static final String NEW_LINE_SEPARATOR = System.getProperty("line.separator");
	public static final String OUTPUT_FILE_HEADER = "drug_name,num_prescriber,total_cost" + NEW_LINE_SEPARATOR ;
	
	public static final int PRESCRIBER_ID = 0;
	public static final int PRESCRIBER_FIRST_NAME = 1;
	public static final int PRESCRIBER_LAST_NAME = 2;
	public static final int DRUG_NAME = 3;
	public static final int DRUG_COST = 4;
	
	public static final String SEPARATOR = ",";
	
}
