package com.hanu.jsd.a3.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hanu.jsd.a3.commons.Properties;
import com.hanu.jsd.a3.model.SettingDTO;
import com.hanu.jsd.a3.service.SettingServiceImpl;

@Controller
public class SettingController {
	
	@Autowired
	SettingServiceImpl settingServiceImpl ;
	@Autowired 
	FileController fileController ;
	@PostMapping("updateSetting")
	public String updateSetting(@ModelAttribute() SettingDTO settingDTO, HttpServletRequest request) {
		settingServiceImpl.updateSetting(settingDTO);
		request.setAttribute("message", Properties.updateSettingMess);
		return fileController.viewHomePage(request);
	}

}
