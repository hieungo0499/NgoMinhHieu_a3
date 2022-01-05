package com.hanu.jsd.a3.service;

import java.util.List;

import com.hanu.jsd.a3.entity.Setting;
import com.hanu.jsd.a3.model.SettingDTO;

public interface SettingService {
	void updateSetting(SettingDTO settingDTO);
	List<SettingDTO> getAll();
	void createSetting(SettingDTO settingDTO);
	Setting convertToSetting(SettingDTO settingDTO);
	SettingDTO converToSettingDTO(Setting setting);
	
}
