package com.example.demo.member;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.demo.board.Board;

@Entity // 이걸 참고하여 테이블 만들어라
public class BoardMem {
	@Id // primary key로 지정
	private String id;
	private String pwd;
	private String name;
	private String email;
	
//	@Transient
//	private String test;//테이블 생성에서 제외
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="writer", cascade = CascadeType.REMOVE)
	private List<Board> list;
	
	public List<Board> getList() {
		return list;
	}

	public void setList(List<Board> list) {
		this.list = list;
	}

	public BoardMem() {
	}

	public BoardMem(String id, String pwd, String name, String email) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "BoardMem [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + "]";
	}

}
