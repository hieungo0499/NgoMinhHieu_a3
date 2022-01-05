package com.hanu.jsd.a3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "setting")
@Setter
@Getter
public class Setting {
	@Id
	@Column(length = 10)
	private long id;
	@Column(length = 10)
	private long maxFileSize;
	@Column(length = 10)
	private int itemPerPage;
	@Column(length = 20)
	private String mimeTypeAllowed;

}
