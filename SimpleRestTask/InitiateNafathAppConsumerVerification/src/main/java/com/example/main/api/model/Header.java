package com.example.main.api.model;

public class Header {
	    private String requestId;
	    private String statusDescription;
	    private String statusDetails;
	    private String transId;
		public Header() {
			super();
		}
		public String getRequestId() {
			return requestId;
		}
		public void setRequestId(String requestId) {
			this.requestId = requestId;
		}
		public String getStatusDescription() {
			return statusDescription;
		}
		public void setStatusDescription(String statusDescription) {
			this.statusDescription = statusDescription;
		}
		public String getStatusDetails() {
			return statusDetails;
		}
		public void setStatusDetails(String statusDetails) {
			this.statusDetails = statusDetails;
		}
		public String getTransId() {
			return transId;
		}
		public void setTransId(String transId) {
			this.transId = transId;
		}
}
