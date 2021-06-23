package com.example.demo.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestFilesService {
	
	@Autowired
	TestFilesRepository reps;
	
	public List<TestFiles> getAll(){
		List<TestFiles> list = reps.findAll();
		return list;
	}
	
	public void saveFiles(TestFiles files) {
		reps.save(files);
	}
	
	public void saveAllFiles(List<TestFiles> testFiles) {
		reps.saveAll(testFiles);
	}
	
	public void delFiles(int tfno) {
		reps.deleteById(tfno);
	}
}
