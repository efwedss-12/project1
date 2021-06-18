package com.example.demo.files;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FilesController {
	
	@Autowired
	FilesService service;
	
	@RequestMapping("/insert")
	public String getFiles(Map map) {
		List<Files> list = service.getFileList();
		map.put("list", list);
		return "/file/insert";
	}
	
	@RequestMapping("/fileinsert")
	public String fileinsert(HttpServletRequest request, @RequestPart MultipartFile files) throws Exception{
		Files file = new Files();
		
		String sourceFileName = files.getOriginalFilename(); 
        		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); 
        		File destinationFile; 
        		String destinationFileName;
        		String fileUrl = "C:/Users/slinfo/Desktop/myspring/workspace1/myjpa/src/main/resources/static/img/";
		// mung-1은 자기 프로젝트이름으로 체인지!!
        		//저장된 파일 이름 랜덤으로 생성
        		do { 
        			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension; 
            			destinationFile = new File(fileUrl + destinationFileName); 
        		} while (destinationFile.exists()); 
        		
        		destinationFile.getParentFile().mkdirs(); 
        		files.transferTo(destinationFile);
        
        		file.setFilename(destinationFileName);
        		file.setFileOriname(sourceFileName);
        		file.setFileurl(fileUrl);
        		service.save(file);
			return "redirect:/file/insert";
	}

	
	@RequestMapping("/view/{num}")
	public String viewImg(@PathVariable("num")int num, Map map) {
		Files f = service.getViewByFno(num);
		map.put("file", f);
		return "file/view";
	}
	
	@RequestMapping("/delete/{num}")
	public String imgDelete(@PathVariable("num")int num) {
		service.delFile(num);
		return "redirect:/file/insert";
	}
}
