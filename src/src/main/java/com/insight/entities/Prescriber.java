package com.insight.entities;

public class Prescriber {


	private String id;
	private String firstName;
	private String lastName;
	
	private int drugCost;
	
	public Prescriber(){
		
	}
	public Prescriber(String id,String firstName,String lastName){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Prescriber(String id,String firstName,String lastName, int drugCost){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.drugCost = drugCost;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getDrugCost() {
		return drugCost;
	}
	public void setDrugCost(int drugCost) {
		this.drugCost = drugCost;
	}
}
