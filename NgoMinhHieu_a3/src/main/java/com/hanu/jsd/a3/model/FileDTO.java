package com.hanu.jsd.a3.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
	private long id ;
	private String name ;
	private String path ;
	private double fileSize ;
	private String mime ;
	private int numberOfDownload ;
	private int version ;
	private boolean  status ;
	private LocalDateTime createdDateTime ;
	
	
}
