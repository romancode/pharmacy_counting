package com.insight.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.insight.entities.Drug;
import com.insight.entities.Prescriber;

public class Parser {

	/**
	 * Higher level of function to parse input data and write output to the file
	 * @param fileName
	 */
	public void parseInputData(String fileName){

		List<Drug> drugList = new ArrayList<Drug>();
		String fileLineContent;

		try (BufferedReader fileBufferReader = new BufferedReader(new FileReader(fileName))) {
			
			fileBufferReader.readLine(); // Ignore the first line that contains header information
			while ((fileLineContent = fileBufferReader.readLine()) != null) {
				drugList = this.processInputData(drugList,fileLineContent, CommonUtils.SEPARATOR);
			}
			Collections.sort(drugList, Comparator.comparingDouble(Drug::getDrugTotalCost).reversed());//Sort the collection descending order by Drug cost
			this.writeToOutputFile(drugList); //Write to the output file

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Main function to process each prescription information from the file
	 * @param drugList
	 * @param fileLineContent
	 * @param splitBy
	 * @return list of unique drugs with prescriber information and drug cost
	 */
	public List<Drug> processInputData(List<Drug> drugList, String fileLineContent,String splitBy){

		String[] prescription = fileLineContent.split(splitBy);

		if(prescription!= null && prescription.length > 0){

			int noOfRow = prescription.length;
			Drug drug = null;
			boolean isOldPrescriber =false;
			boolean isNewDrug =false;

			String drugName = prescription[CommonUtils.DRUG_NAME]!= null ? prescription[CommonUtils.DRUG_NAME].trim() : "";
			int drugCost = prescription[noOfRow-1] != null ? Integer.parseInt(prescription[noOfRow-1]) : 0 ;

			if(drugName.isEmpty()){
				return drugList;
			}
			String prescriberId =prescription[CommonUtils.PRESCRIBER_ID]!= null ? prescription[CommonUtils.PRESCRIBER_ID].trim() : "";
			String prescriberFirstName = prescription[CommonUtils.PRESCRIBER_FIRST_NAME]!= null ? prescription[CommonUtils.PRESCRIBER_FIRST_NAME].trim() : "";
			String prescriberLastName = prescription[CommonUtils.PRESCRIBER_LAST_NAME]!= null ? prescription[CommonUtils.PRESCRIBER_LAST_NAME].trim() : "";

			if(drugList.size() > 0){
				drug = this.findDrugByName(drugList, drugName);
			}

			if(drug==null){
				drug = new Drug(drugName);	
				isNewDrug= true;
			}
			Prescriber prescriber = new Prescriber(prescriberId, prescriberFirstName,prescriberLastName,drugCost);
			
			if(drug.getPrescribers()!=null && drug.getPrescribers().size() > 0){
				//Checking duplicate prescriber for same drug
				isOldPrescriber = this.isPrescriberExits(drug, prescriber);
			}
			if(!isOldPrescriber){
				drug.addPrescirber(prescriber);
			}
			if(isNewDrug){
				drugList.add(drug);
			}
		}
		return drugList;
	}
	/**
	 * Checking if same prescriber already exists
	 * @param drug
	 * @param prescriber
	 * @return true/false
	 */
	public boolean isPrescriberExits(Drug drug,Prescriber prescriber){
		
		return drug.getPrescribers().stream().anyMatch(p-> p.getFirstName().equals(prescriber.getFirstName()) 
				&& p.getLastName().equals(prescriber.getLastName()) && p.getId().equals(prescriber.getId()));

	}
	/**
	 * Find the drug by name from the list
	 * @param listDrug
	 * @param drugName
	 * @return Drug
	 */
	public Drug findDrugByName(Collection<Drug> listDrug, String drugName) {
		return listDrug.stream().filter(drug -> drugName.equals(drug.getDrugName())).findFirst().orElse(null);
	}
	/**
	 * Write drug list to output file
	 * @param drugList
	 * @throws IOException
	 */
	public void writeToOutputFile(List<Drug> drugList) throws IOException{

		FileWriter fileWriter = new FileWriter(CommonUtils.OUTPUT_DATA_FILE);
		fileWriter.write(CommonUtils.OUTPUT_FILE_HEADER);

		for(Drug drug: drugList){
			fileWriter.write(drug.getDrugName()+","+drug.getPrescribers().size() +","+drug.getDrugTotalCost() + CommonUtils.NEW_LINE_SEPARATOR );
		}
		fileWriter.close();
	}
}
