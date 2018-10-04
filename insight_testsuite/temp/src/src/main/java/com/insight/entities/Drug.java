package com.insight.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Drug implements Comparator<Drug> {

	private String drugName;
	private List<Prescriber> prescribers;

	public Drug(String drugName){
		this.drugName = drugName;
		this.prescribers = new ArrayList<Prescriber>();
	}

	public String getDrugName() {
		return this.drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public int getDrugTotalCost() {
		int drugTotalCost = 0;

		if(prescribers !=null && prescribers.size() > 0){
			for(Prescriber p:prescribers ){
				drugTotalCost += p.getDrugCost();
			}
		}
		return drugTotalCost;
	}

	public List<Prescriber> getPrescribers() {
		return prescribers;
	}
	public void setPrescribers(List<Prescriber> prescribers) {
		this.prescribers = prescribers;
	}
	public void addPrescirber(Prescriber prescriber){
		this.prescribers.add(prescriber);
	}
	@Override
	public int compare(Drug drug1, Drug drug2) {
	       int totalCost1 = drug1.getDrugTotalCost();
	       int totalCost2 = drug2.getDrugTotalCost();
	       if(totalCost2 > totalCost1)
	          return 1;
	       else if(totalCost2 < totalCost1)
	          return -1;
	       else
	          return 0;    
	} 
}
