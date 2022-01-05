package com.hanu.jsd.a3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hanu.jsd.a3.dao.FileDao;
import com.hanu.jsd.a3.entity.FileEntity;
import com.hanu.jsd.a3.model.FileDTO;
@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private	FileDao fileDao ;
	
	
	@Override
	public void uploadFile(FileDTO fileDTO) {
		fileDao.save(convertToFile(fileDTO));
		
		
	}

	@Override
	public void deleteFile(long id) {
		
		FileDTO fileDTO = getById(id);
		fileDTO.setStatus(false);
		updateFile(fileDTO);
		
	}

	@Override
	public List<FileDTO> getAllAvailableFile(Pageable pageable) {
		List<FileEntity> files = fileDao.getAvailableFile(pageable).getContent();
		List<FileDTO> fileDTOs = new ArrayList<FileDTO>();
		if(files.size() >0) {
			files.forEach((n)->{fileDTOs.add(convertToFileDTO(n));});
			
			return fileDTOs;
		}
		else {
			return fileDTOs ;
		}
		
	}

	@Override
	public FileDTO convertToFileDTO(FileEntity file) {
		FileDTO fileDTO = new  FileDTO();
		fileDTO.setCreatedDateTime(file.getCreatedDateTime());
		fileDTO.setFileSize(file.getFileSize());
		fileDTO.setId(file.getId());
		fileDTO.setMime(file.getMime());
		fileDTO.setName(file.getName());
		fileDTO.setNumberOfDownload(file.getNumberOfDownload());
		fileDTO.setPath(file.getPath());
		fileDTO.setStatus(file.isStatus());
		fileDTO.setVersion(file.getVersion());
		return fileDTO;
	}

	@Override
	public FileEntity convertToFile(FileDTO fileDTO) {
		FileEntity file = new FileEntity();
		file.setCreatedDateTime(fileDTO.getCreatedDateTime());
		file.setFileSize(fileDTO.getFileSize());
		file.setId(fileDTO.getId());
		file.setMime(fileDTO.getMime());
		file.setName(fileDTO.getName());
		file.setNumberOfDownload(fileDTO.getNumberOfDownload());
		file.setPath(fileDTO.getPath());
		file.setStatus(fileDTO.isStatus());
		file.setVersion(fileDTO.getVersion());
		return file ;
	}

	@Override
	public FileDTO converToFileDTO(MultipartFile file) {
		FileDTO fileDTO = new FileDTO();
		fileDTO.setFileSize(0);
		
		return null ;
	}

	@Override
	public int countNumberOfVersion(String name) {
		int numberOfVersion = fileDao.countNumberOfVersion(name);
		return numberOfVersion;
	}

	@Override
	public FileDTO getById(long id) {
		FileEntity fileEntity = fileDao.getById(id);
		return convertToFileDTO(fileEntity);
	}

	@Override
	public void updateFile(FileDTO fileDTO) {
		fileDao.save(convertToFile(fileDTO));
		
	}

	
	
}
