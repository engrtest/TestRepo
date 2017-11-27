package com.jptest.model;

import org.joda.time.LocalDate;

public class TradeInstructionRank {
	private String entity;
	private LocalDate filterDate;
	private int rank;
	
	public TradeInstructionRank(String entity, LocalDate filterDate, int rank){
		this.setEntity(entity);
		this.setDate(filterDate);
		this.setRank(rank);
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getEntity() {
		return entity;
	}

	public void setDate(LocalDate filterDate) {
		this.filterDate = filterDate;
	}

	public LocalDate getDate() {
		return filterDate;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getRank() {
		return rank;
	}
	
	@Override
	public boolean equals(Object ob){
		if(ob == null || (this.getClass() != ob.getClass())){
			return false;
		}
		TradeInstructionRank inRank = (TradeInstructionRank)ob;
		boolean isEquals = this.getRank() == inRank.getRank() && this.getDate().equals(inRank.getDate()) && this.getEntity().equals(inRank.getEntity());
		return isEquals;
	}
}
