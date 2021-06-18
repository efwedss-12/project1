package com.example.demo.board;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	@Autowired
	private BoardRepository rep;
	
	public void addBoard(Board b) {//글추가
		rep.save(b);
	}
	
	public ArrayList<Board> getByParentNum(int parent_num){//원글 전체 검색
		return rep.findByParent(parent_num);
	}
	
	public Board getByNum(int num) {//글 번호로 검색
		return rep.findById(num).orElse(null);
	}
	
	public ArrayList<Board> getByTitle(String title){
		return rep.findByTitleLike(title);
	}
	
	public void editBoard(Board b) {
		rep.save(b);
	}
	
	public void delBoard(int num) {
		rep.deleteById(num);
	}
}







