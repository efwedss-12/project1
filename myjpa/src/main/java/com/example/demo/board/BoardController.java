package com.example.demo.board;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.files.Files;
import com.example.demo.files.FilesService;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	//글작성:get으로 작성폼. 작성폼에서 내용작성후 post submit db에 저장하고 글 목록으로 자동 이동
	//get write / post write
	
	//글목록
	//전체 검색해서 검색결과를 list.jsp출력. 글번호 제목(상세페이지링크) 작성자 
	//상세페이지 글 전체 정보 출력. 
	@Autowired
	private BoardService service;
	
	@Autowired 
	private FilesService fservice;
	
	@GetMapping("/write")
	public String writeForm(int parent, Map map) {
		
		map.put("parent", parent);
		return "/board/testwrite";
	}
	
	@PostMapping("/write")
	public String write(Board b, MultipartHttpServletRequest request, @RequestPart MultipartFile files) throws Exception{
		String res = "redirect:/board/read/"+b.getParent();//댓글 작성시 이동할 경로
		service.addBoard(b);
		
		
		System.out.println("겟네임" + files.getName());
		System.out.println("겟사이즈" + files.getSize());
		System.out.println("오리지널네임" + files.getOriginalFilename());
		byte[] data = files.getBytes();
		System.out.println(data);
		
		//파일 업로드
		if(!files.getOriginalFilename().equals("")) {
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
        file.setBoardimg(b);  
        System.out.println("업로드 됨!");
        fservice.save(file);
        }
		if(b.getParent()==0) {
			res = "redirect:/board/list";//원글 작성시 이동할 경로
		}
		
		return res;
	}
	//다중 업로드
	@ResponseBody
	@PostMapping("/testwrite")
	public String testWrite(Board b,
			@RequestParam(value = "article_file",required = false)List<MultipartFile> multipartFiles,
			HttpServletRequest request){
		String res = "redirect:/board/read/"+b.getParent();//댓글 작성시 이동할 경로
		service.addBoard(b);
		//경로
		String path = "C:/Users/slinfo/Desktop/myspring/workspace1/myjpa/src/main/resources/static/testimgs/";
		
		//파일 업로드
		if(!(multipartFiles == null)) {
			System.out.println(multipartFiles.get(0).getOriginalFilename()+"임");
			for(MultipartFile files:multipartFiles) {
				Files f = new Files();
				String originFileName = files.getOriginalFilename();
				String extension = originFileName.substring(originFileName.lastIndexOf("."));//확장자
				//저장파일명(랜덤)
				String saveFileName = UUID.randomUUID() + extension;
				
				File saveLocation = new File(path + saveFileName);
				
				System.out.println(files.getOriginalFilename()+"입니다");
				
				f.setFileurl(path);
				f.setFileOriname(originFileName);
				f.setFilename(saveFileName);
				f.setBoardimg(b);
				fservice.save(f);
				
				try {
					InputStream stream = files.getInputStream();
					FileUtils.copyInputStreamToFile(stream, saveLocation);
				} catch (Exception e) {
					FileUtils.deleteQuietly(saveLocation);
					e.printStackTrace();
					break;
				}
			}
		}else {
			System.out.println("업로드 파일 없음");
		} 
		if(b.getParent()==0) {
			res = "redirect:/board/list";//원글 작성시 이동할 경로
		}

		return res;
		
	}
	
	
	
	@RequestMapping("/list")
	public void list(Map map) {
		ArrayList<Board> list = service.getByParentNum(0);
		map.put("list", list);
	}
	
	@GetMapping("/read/{num}")
	public String read(@PathVariable("num")int num, Map map) {
		Board b = service.getByNum(num);
		ArrayList<Board> reps = service.getByParentNum(num);
		map.put("b", b);
		map.put("reps", reps);
		
		System.out.println(b.getNum()+"입니다.");
		//이미지
		Files f = fservice.getViewByNum(num);
		map.put("f", f);
		return "/board/detail";
	}
	
	@GetMapping("/edit/{num}")
	public String editForm(@PathVariable("num")int num, Map map) {
		
		Board b = service.getByNum(num);
		ArrayList<Board> reps = service.getByParentNum(num);
		map.put("editb", b);
		map.put("reps", reps);
		
		Files f = fservice.getViewByNum(num);
		map.put("editf", f);
		
		return "/board/edit";
	}
	
	@PostMapping("/edit")
	public String edit(Board b) {
		service.editBoard(b);
		return "redirect:/board/read/" + b.getNum();
	}
	
	@GetMapping("/del/{num}")
	public String del(@PathVariable("num")int num) {
		service.delBoard(num);
		return "redirect:/board/list";
	}
	
	@PostMapping("/s-title")
	public String searchByTitleLike(String title, Map map) {
		ArrayList<Board> list = service.getByTitle("%"+title+"%");
		map.put("list", list);
		return "board/list";
	}
}
