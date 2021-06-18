package com.example.demo.files;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.example.demo.board.Board;

@Entity
@SequenceGenerator(name = "multifile_generator", allocationSize = 1, sequenceName = "multifile_seq")
public class MultiFiles {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "multifile_generator")
	private int fno;
	
	@ManyToOne
	@JoinColumn(name = "boardfkno")
	private Board board;
	
	private String origFileName;
	
	private String filePath;
	
	private Long fileSize;

	
	public MultiFiles() {}
	
	public MultiFiles(int fno, Board board, String origFileName, String filePath, Long fileSize) {
		this.fno = fno;
		this.board = board;
		this.origFileName = origFileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}

	public int getFno() {
		return fno;
	}

	public void setFno(int fno) {
		this.fno = fno;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public String getOrigFileName() {
		return origFileName;
	}

	public void setOrigFileName(String origFileName) {
		this.origFileName = origFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
	

	
	
}
