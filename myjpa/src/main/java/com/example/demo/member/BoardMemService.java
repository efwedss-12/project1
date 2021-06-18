package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardMemService {
	
	@Autowired
	private BoardMemRepository rep;
	
	public void join(BoardMem m) {
		rep.save(m);//db에 동일한 id(primary key)가 있으면 수정, 없으면 추가
	}
	
	public BoardMem getMember(String id) {
		return rep.findById(id).orElse(null);
	}
	
	public void editMember(BoardMem m) {
		rep.save(m);
	}
	
	public void delMember(String id) {
		rep.deleteById(id);//id기준으로 삭제
	}
}
