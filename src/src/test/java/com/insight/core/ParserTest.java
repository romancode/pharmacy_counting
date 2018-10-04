package com.insight.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.insight.entities.Drug;
import com.insight.entities.Prescriber;

import junit.framework.TestCase;

public class ParserTest extends TestCase {
	Parser parser = new Parser();
	
	 List<Drug> listDrug=new ArrayList<Drug>();
     
    public ParserTest(){
    	this.LoadDrugData();
    }
    public void LoadDrugData(){ 
         listDrug.add(new Drug("FLUOXETINE HCL"));
         listDrug.add(new Drug("GABAPENTIN"));
         listDrug.add(new Drug("BUPROPION HCL SR"));
         listDrug.add(new Drug("BUPROPION XL"));
         listDrug.add(new Drug("BUPROPION HCL"));
    }
    
    @Test
    public void testprocessInputData(){
    	
    	String fileLineContent = "1770628661,AAKALU,GEETHA,BUPROPION HCL XX,500";
    	
    	List<Drug> resultDrugList = parser.processInputData(listDrug, fileLineContent, CommonUtils.SEPARATOR);
    	
    	Drug drug = resultDrugList.get(listDrug.size() - 1);
    	
    	
    	assertEquals(drug.getDrugName(), "BUPROPION HCL XX");
    	
    }
	@Test
    public void testfindDrugByName() {  
        Drug resultNull = parser.findDrugByName(listDrug, "BUPROPION");
        Drug resultDrug = parser.findDrugByName(listDrug, "BUPROPION HCL");
        
        assertEquals(null, resultNull);
        assertEquals(parser.findDrugByName(listDrug, "BUPROPION HCL"), resultDrug);
    }
	@Test
	public void testisPrescriberExits(){
		 Drug drug = new Drug("FLUOXETINE HC");
		 Prescriber prescriber = new Prescriber("1013964634","AAGESEN","CARL",250); 
		 drug.addPrescirber(prescriber);
		 
		 Prescriber prescriberNew = new Prescriber("1013964635","HARRY","KANE",250); 
		 
		 boolean result = parser.isPrescriberExits(drug, prescriber);
		 boolean resultNew = parser.isPrescriberExits(drug, prescriberNew);
		 assertTrue(result);
		 assertFalse(resultNew);
	}

}
