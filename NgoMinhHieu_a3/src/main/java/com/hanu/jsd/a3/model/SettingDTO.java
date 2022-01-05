package com.hanu.jsd.a3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SettingDTO {
	private long id;
	private long maxFileSize;
	private int itemPerPage;
	private String mimeTypeAllowed;

	

}
