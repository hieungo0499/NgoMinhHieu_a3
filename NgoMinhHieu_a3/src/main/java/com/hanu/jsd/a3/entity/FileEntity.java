package com.hanu.jsd.a3.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "file")
@Setter
@Getter
public class FileEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 38)
	private long id ;
	@Column(length = 255)
	private String name ;
	@Column(length = 255)
	private String path ;
	@Column(length = 10)
	private double fileSize ;
	@Column(length = 255)
	private String mime ;
	@Column(length = 10)
	private int numberOfDownload ;
	@Column(length = 10)
	private int version ;
	@Column(length = 2)
	private boolean  status ;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime createdDateTime ;

}
