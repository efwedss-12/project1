package com.example.demo.files;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.demo.board.Board;

@Entity
@Table(name = "files")
@SequenceGenerator(name = "file_seq_generator",allocationSize = 1, sequenceName = "file_seq" )
public class Files {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_seq_generator")
	private int fno;
	
	@ManyToOne
	@JoinColumn(name = "boardimg", nullable = false)
	private Board boardimg;
	
	private String filename;
	private String fileOriname;
	private String fileurl;
	
	public Files() {}
	
	public Files(Board boardimg, int fno, String filename, String fileOriname, String fileurl) {
		this.boardimg = boardimg;
		this.fno = fno;
		this.filename = filename;
		this.fileOriname = fileOriname;
		this.fileurl = fileurl;
	}
	
	

	public Board getBoardimg() {
		return boardimg;
	}

	public void setBoardimg(Board boardimg) {
		this.boardimg = boardimg;
	}

	public int getFno() {
		return fno;
	}

	public void setFno(int fno) {
		this.fno = fno;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileOriname() {
		return fileOriname;
	}

	public void setFileOriname(String fileOriname) {
		this.fileOriname = fileOriname;
	}

	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	@Override
	public String toString() {
		return "Files [fno=" + fno + ", boardimg=" + boardimg + ", filename=" + filename + ", fileOriname="
				+ fileOriname + ", fileurl=" + fileurl + "]";
	}
	
	
	
	
}
