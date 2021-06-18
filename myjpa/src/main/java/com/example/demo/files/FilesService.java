package com.example.demo.files;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.board.Board;

@Service
public class FilesService {

	@Autowired
	FilesRepository rep;
	
	public void save(Files files) {
//		Files f = new Files();
//		f.setFilename(files.getFilename());
//		f.setFileOriname(files.getFileOriname());
//		f.setFileurl(files.getFileurl());
//		f.setBoardimg(files.getBoardimg());
//		rep.save(f);
		
		rep.save(files);
	}
	
	public Files getViewByFno(int fno) {
		return rep.findById(fno).orElse(null);
	}
	
	public Files getViewByNum(int num) {
		System.out.println(num+" in service");
		return rep.findByboardimg(num);
	} 
	
	//테스트
	public List<Files> getFileList(){
		List<Files> list = rep.findAll();
		return list;
	}
	
	//테스트
	public void delFile(int fno) {
		rep.deleteById(fno);
	}

}
