package com.example.demo.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestFilesService {
	
	@Autowired
	TestFilesRepository reps;
	
	public void saveFiles(TestFiles files) {
		reps.save(files);
	}
	public void saveAllFiles(List<TestFiles> testFiles) {
		reps.saveAll(testFiles);
	}
}
