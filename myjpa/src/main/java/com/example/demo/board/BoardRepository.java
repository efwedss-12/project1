package com.example.demo.board;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
//	save:추가, 수정
//	findById()
//	findAll
//	deleteById()
//	제목으로 검색: 작성필요
//	작성자로 검색
	ArrayList<Board> findByTitleLike(String title);
	//select * from board where title like '%title%'
	
	ArrayList<Board> findByParent(int parent_num);
}
