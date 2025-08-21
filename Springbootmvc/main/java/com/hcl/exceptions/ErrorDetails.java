package com.hcl.exceptions;

import java.time.LocalDateTime;

public class ErrorDetails {
     private LocalDateTime timeStampDateTime;
     private String message;
     private String pathString;
     private String errorCode;
     
	 public ErrorDetails(LocalDateTime timeStampDateTime, String message, String pathString, String errorCode) {
		super();
		this.timeStampDateTime = timeStampDateTime;
		this.message = message;
		this.pathString = pathString;
		this.errorCode = errorCode;
	 }
	 
	 public ErrorDetails() {
		 
	 }

	 public LocalDateTime getTimeStampDateTime() {
		 return timeStampDateTime;
	 }

	 public void setTimeStampDateTime(LocalDateTime timeStampDateTime) {
		 this.timeStampDateTime = timeStampDateTime;
	 }

	 public String getMessage() {
		 return message;
	 }

	 public void setMessage(String message) {
		 this.message = message;
	 }

	 public String getPathString() {
		 return pathString;
	 }

	 public void setPathString(String pathString) {
		 this.pathString = pathString;
	 }

	 public String getErrorCode() {
		 return errorCode;
	 }

	 public void setErrorCode(String errorCode) {
		 this.errorCode = errorCode;
	 }

	 @Override
	 public String toString() {
		return "ErrorDetails [timeStampDateTime=" + timeStampDateTime + ", message=" + message + ", pathString="
				+ pathString + ", errorCode=" + errorCode + "]";
	 }
 
}
