package com.example.demo.files;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.member.BoardMem;
import com.example.demo.member.BoardMemService;

@Controller
public class MultiFilesController {
	
	@GetMapping("/testupload")
	public String getTestUpload() {
		return "/test/testupload";
	}
	
	@PostMapping("/upload")
	public String getFiles(MultipartHttpServletRequest mrequest) {
		List<MultipartFile> filelist = mrequest.getFiles("file");
		String src = mrequest.getParameter("src");
		System.out.println("src 는" + src);
		
		String path = "c:\\imgs\\";
		
		for(MultipartFile mf : filelist) {
			String originFileName = mf.getOriginalFilename();
			long filesize = mf.getSize();
			
			System.out.println("파일이름 : " +  originFileName);
			System.out.println("크기 : " + filesize);
			
			String saveFile = path + System.currentTimeMillis() + originFileName;
		
			try {
				mf.transferTo(new File(saveFile));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		return "redirect:/test/testupload";
		
	}
	
	

}
