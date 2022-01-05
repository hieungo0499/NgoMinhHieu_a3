package com.hanu.jsd.a3.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.hanu.jsd.a3.entity.FileEntity;
import com.hanu.jsd.a3.model.FileDTO;

public interface FileService {
	void uploadFile(FileDTO file);
	void deleteFile(long id);
	List<FileDTO> getAllAvailableFile(Pageable pageable);
	FileDTO convertToFileDTO(FileEntity file);
	FileEntity convertToFile(FileDTO fileDTO);
	FileDTO converToFileDTO(MultipartFile  file);
	int countNumberOfVersion(String name);
	FileDTO getById(long id);
	void updateFile(FileDTO fileDTO);

}
