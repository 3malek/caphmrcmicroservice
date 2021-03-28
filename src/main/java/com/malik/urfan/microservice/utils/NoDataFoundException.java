package com.malik.urfan.microservice.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No data found")  // 404
public class NoDataFoundException extends RuntimeException {}
