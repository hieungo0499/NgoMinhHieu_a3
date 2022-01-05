package com.hanu.jsd.a3.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hanu.jsd.a3.commons.Properties;
import com.hanu.jsd.a3.dao.FileDao;
import com.hanu.jsd.a3.model.FileDTO;
import com.hanu.jsd.a3.model.SettingDTO;
import com.hanu.jsd.a3.service.FileServiceImpl;
import com.hanu.jsd.a3.service.SettingServiceImpl;

@Controller
public class FileController {

	@Autowired
	private FileServiceImpl fileServiceImpl;
	@Autowired
	private SettingServiceImpl settingServiceImpl;
	@Autowired
	private FileDao fileDao ;

	@GetMapping("/home")
	public String viewHomePage(HttpServletRequest request) {
		paginatedFileList(request, 1);
		return "view/home";
	}

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {

		SettingDTO settingDTO = settingServiceImpl.getAll().get(0);
		String allowedType = settingDTO.getMimeTypeAllowed().toLowerCase();

		if (file.getContentType().contains(allowedType) || allowedType.equals("all")) {

			if (file.getSize() <= settingDTO.getMaxFileSize() * 1000000) {

				try {
					int version = fileServiceImpl.countNumberOfVersion(file.getOriginalFilename()) + 1;
					System.out.print(file.getName());
					file.transferTo(new File(Properties.filePath+ String.valueOf(version) + file.getOriginalFilename()));
					FileDTO fileDTO = new FileDTO();
					fileDTO.setFileSize(Math.ceil((file.getSize() / 1000000.0) * 100) / 100);
					fileDTO.setCreatedDateTime(LocalDateTime.now());
					fileDTO.setMime(file.getContentType());
					fileDTO.setName(file.getOriginalFilename());
					fileDTO.setNumberOfDownload(0);
					fileDTO.setPath(Properties.filePath+String.valueOf(version) + file.getOriginalFilename());
					fileDTO.setStatus(true);
					fileDTO.setVersion(version);
					fileServiceImpl.uploadFile(fileDTO);
					String message = Properties.uploadCompletedMess;
					request.setAttribute("message", message);

				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				String error = Properties.fileSizeErr;
				request.setAttribute("error", error);
			}
		} else {
			String error = Properties.fileTypeErr;
			request.setAttribute("error", error);
		}

		return viewHomePage(request);

	}
	@GetMapping("/download")
	public void download(HttpServletResponse response, @RequestParam("id") long id,HttpServletRequest request) throws IOException {
	    try {
	  
	      System.out.println("id :"+ id);
	      FileDTO fileDTO = fileServiceImpl.getById(id);
	      File file = ResourceUtils.getFile(fileDTO.getPath());
	      byte[] data = FileUtils.readFileToByteArray(file);
	      response.setContentType("application/octet-stream");
	      response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
	      response.setContentLength(data.length);
	      InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
	      FileCopyUtils.copy(inputStream, response.getOutputStream());
	      fileDTO.setNumberOfDownload(fileDTO.getNumberOfDownload()+1);
	      fileServiceImpl.updateFile(fileDTO);
	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }
	  }
	

	@GetMapping("/deleteFile")
	public String deleteFile(HttpServletRequest request, @RequestParam("id") long id) {
		String msg = Properties.deleteFileMess;
		fileServiceImpl.deleteFile(id);
		request.setAttribute("message", msg);
		return viewHomePage(request);
	}

	@GetMapping("file-list/{pageNo}")
	public String paginatedFileList(HttpServletRequest request, @PathVariable("pageNo") int p) {
		List<SettingDTO> settingDTOs = settingServiceImpl.getAll();
		SettingDTO settingDTO = new SettingDTO();
		// if setting has not been created , a default will be created
		if (settingDTOs.size() > 0) {
			settingDTO = settingDTOs.get(0);
		} else {
			settingDTO.setId(1);
			settingDTO.setItemPerPage(5);
			settingDTO.setMaxFileSize(1);
			settingDTO.setMimeTypeAllowed(Properties.allowedFileType[1]);
			settingServiceImpl.updateSetting(settingDTO);
		}
		Pageable pageable = PageRequest.of(p-1, settingDTO.getItemPerPage(), Sort.by("id").descending());
		List<FileDTO> files = fileServiceImpl.getAllAvailableFile(pageable);
		System.out.println(files.size());
		int currentPage = p;
		int total = fileDao.getAvailableFile(pageable).getTotalPages();
		request.setAttribute("setting", settingDTO);
		request.setAttribute("files", files);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("total", total);
		request.setAttribute("allowedType", Properties.allowedFileType);
		return "view/home";
	}

}
