package com.example.demo.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestFilesService service;
	
	@GetMapping("/testupload")
	public String getTestUpload() {
		return "/test/testupload";
	}	
	
	@ResponseBody //ajax success
	@PostMapping("/upload")
	public String getArrFiles(
		@RequestParam(value = "article_file", required = false)List<MultipartFile> multipartFile
		, HttpServletRequest request) {
		
		//경로
		String path = "C:/Users/slinfo/Desktop/myspring/workspace1/myjpa/src/main/resources/static/testimgs/";
		//String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		
			if(!(multipartFile == null)) {
				for(MultipartFile files:multipartFile) {
					TestFiles tfiles = new TestFiles();
					String originFileName = files.getOriginalFilename(); //원본 파일명
					String extension = originFileName.substring(originFileName.lastIndexOf(".")); //확장자
					//저장할 파일명 
					String saveFileName = UUID.randomUUID() + extension;
				
					File targetFile = new File(path + saveFileName);
					
					System.out.println(files.getOriginalFilename()+"입니다.");
					//DB로
					tfiles.setFilepath(path);
					tfiles.setOriginFname(originFileName);
					tfiles.setFname(saveFileName);
					service.saveFiles(tfiles);
					
					try {
						InputStream stream = files.getInputStream();
						FileUtils.copyInputStreamToFile(stream, targetFile);
					} catch (Exception e) {
						FileUtils.deleteQuietly(targetFile);
						e.printStackTrace();
						break;
					}
				}
				
			}else {
				System.out.println("파일없음");
			}	
		return null;
		
	}
	
	@GetMapping("/testview")
	public String getTestView(Map map) {
		
		List<TestFiles> list = service.getAll();
		map.put("li", list);
		
		System.out.println("view로 이동");
		return "/test/testview";
	}
	
	@GetMapping("/del/{num}")
	public String delView(@PathVariable("num")int tfno) {
		System.out.println(tfno + "삭제");
		service.delFiles(tfno);
		return "redirect:/test/testview";
	}
	
	
	
}
