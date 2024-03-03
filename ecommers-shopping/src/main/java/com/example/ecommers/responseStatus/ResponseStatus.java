package com.example.ecommers.responseStatus;

import lombok.Data;

@Data
public class ResponseStatus<T> {
	private int statusCode;
	private String status;
	private String message;
	private T data;

}
