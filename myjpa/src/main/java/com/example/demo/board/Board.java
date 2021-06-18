package com.example.demo.board;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;

import com.example.demo.files.Files;
import com.example.demo.files.MultiFiles;
import com.example.demo.member.BoardMem;

@Entity
public class Board {
	@Id // primary key 지정
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_sequence") // 자동값생성자사용. 종류:시퀀스,
	// 생성자이름:board_sequence
	@SequenceGenerator(name = "board_sequence", sequenceName = "seq_board", allocationSize=1)
	private int num;//현재 글의 번호

	@ManyToOne
	@JoinColumn(name = "writer", nullable = false)
	private BoardMem writer;
	
	@OneToMany(mappedBy = "boardimg", cascade = CascadeType.REMOVE)
	private List<Files> list;
	
	
	//다중 업로드
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	private List<MultiFiles> multifiles = new ArrayList<>();
	
	
	public List<MultiFiles> getMultifiles() {
		return multifiles;
	}



	public void setMultifiles(List<MultiFiles> multifiles) {
		this.multifiles = multifiles;
	}

	@Column(updatable=false)
	private Date w_date;
	private String title;
	private String content;
//	@Column(columnDefinition="number default 0")//컬럼 기본값 설정
	private int parent;//부모 글의 번호. 원글은 0으로 초기화

	@PrePersist // db 저장 전 호출
	public void makeDate() {
		w_date = new Date();
	}

	

	public List<Files> getList() {
		return list;
	}



	public void setList(List<Files> list) {
		this.list = list;
	}



	public int getParent() {
		return parent;
	}



	public void setParent(int parent) {
		this.parent = parent;
	}



	public Board() {
	}

	public Board(int num, BoardMem writer, Date w_date, String title, String content) {
		this.num = num;
		this.writer = writer;
		this.w_date = w_date;
		this.title = title;
		this.content = content;
	}

	public BoardMem getWriter() {
		return writer;
	}

	public void setWriter(BoardMem writer) {
		this.writer = writer;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Date getW_date() {
		return w_date;
	}

	public void setW_date(Date w_date) {
		this.w_date = w_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Board [num=" + num + ", writer=" + writer + ", w_date=" + w_date + ", title=" + title + ", content="
				+ content + "]";
	}

}
