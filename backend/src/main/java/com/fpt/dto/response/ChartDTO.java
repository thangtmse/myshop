package com.fpt.dto.response;

import java.util.List;

public class ChartDTO {
	
	private List<List> datasets;
	private List<String> labels;
	private int orderTotal;
	private float incomeTotal;
	
	public List<List> getDatasets() {
		return datasets;
	}
	public void setDatasets(List<List> datasets) {
		this.datasets = datasets;
	}
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	public int getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}
	public float getIncomeTotal() {
		return incomeTotal;
	}
	public void setIncomeTotal(float incomeTotal) {
		this.incomeTotal = incomeTotal;
	}
	
}
