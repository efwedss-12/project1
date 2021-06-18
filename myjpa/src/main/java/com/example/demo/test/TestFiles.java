package com.example.demo.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "tseq",sequenceName = "test1_seq",allocationSize = 1)
public class TestFiles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tseq")
	private int tfno;
	private String fname;
	private String originFname;
	private String filepath;
	
	
	public TestFiles() {}
	public TestFiles(int tfno, String fname, String originFname, String filepath) {	
		this.tfno = tfno;
		this.fname = fname;
		this.originFname = originFname;
		this.filepath = filepath;
	}

	public int getTfno() {
	return tfno;
	}
	public void setTfno(int tfno) {
		this.tfno = tfno;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getOriginFname() {
		return originFname;
	}
	public void setOriginFname(String originFname) {
		this.originFname = originFname;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
}
