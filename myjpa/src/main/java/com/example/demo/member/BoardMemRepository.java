package com.example.demo.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardMemRepository extends JpaRepository<BoardMem, String> {
//primary key 기준의 추가, 검색, 전체검색, 수정, 삭제는 이미 구현.
//이 외의 db작업이 필요하면 이름 명명 규칙에 맞게 메서드 이 리파짓토리 인터페이스에 선언하면 자동으로 구현해준다
}
