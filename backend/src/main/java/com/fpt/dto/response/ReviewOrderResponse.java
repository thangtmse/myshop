package com.fpt.dto.response;

public class ReviewOrderResponse {
	private Integer done;
	private Integer processing;
	private Integer delivering;
	private Integer cancel;

	public Integer getDone() {
		return done;
	}

	public void setDone(Integer done) {
		this.done = done;
	}

	public Integer getProcessing() {
		return processing;
	}

	public void setProcessing(Integer processing) {
		this.processing = processing;
	}

	public Integer getDelivering() {
		return delivering;
	}

	public void setDelivering(Integer delivering) {
		this.delivering = delivering;
	}

	public Integer getCancel() {
		return cancel;
	}

	public void setCancel(Integer cancel) {
		this.cancel = cancel;
	}

}
