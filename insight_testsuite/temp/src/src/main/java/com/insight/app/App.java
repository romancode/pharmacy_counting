package com.insight.app;

import com.insight.core.CommonUtils;
import com.insight.core.Parser;

public class App 
{
    public static void main( String[] args )
    {
    	String fileName = CommonUtils.INPUT_DATA_FILE; 
		Parser parser = new Parser();
		parser.parseInputData(fileName);
    }
}
