package com.hanu.jsd.a3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanu.jsd.a3.dao.SettingDao;
import com.hanu.jsd.a3.entity.Setting;
import com.hanu.jsd.a3.model.SettingDTO;
@Service
public class SettingServiceImpl implements SettingService {
	
	@Autowired
	private	SettingDao settingDao ;

	@Override
	public void updateSetting(SettingDTO settingDTO) {
		settingDao.save(convertToSetting(settingDTO));
		
	}

	@Override
	public List<SettingDTO> getAll() {
		List<Setting> settings = settingDao.findAll();
		List<SettingDTO> settingDTOs = new ArrayList<SettingDTO>();
		if (settings.size() > 0) {
			settings.forEach((n)->{settingDTOs.add(converToSettingDTO(n));});
			return settingDTOs ;
		}
		
		else {
		return settingDTOs;}
	}

	@Override
	public void createSetting(SettingDTO settingDTO) {
		Setting setting = convertToSetting(settingDTO);
		settingDao.save(setting);
		
		
	}

	@Override
	public Setting convertToSetting(SettingDTO settingDTO) {
		Setting setting = new Setting();
		setting.setId(settingDTO.getId());
		setting.setItemPerPage(settingDTO.getItemPerPage());
		setting.setMaxFileSize(settingDTO.getMaxFileSize());
		setting.setMimeTypeAllowed(settingDTO.getMimeTypeAllowed());
		return setting;
	}

	@Override
	public SettingDTO converToSettingDTO(Setting setting) {
		SettingDTO settingDTO = new SettingDTO();
		settingDTO.setId(setting.getId());
		settingDTO.setItemPerPage(setting.getItemPerPage());
		settingDTO.setMaxFileSize(setting.getMaxFileSize());
		settingDTO.setMimeTypeAllowed(setting.getMimeTypeAllowed());
		return settingDTO;
	}

	

	
}
