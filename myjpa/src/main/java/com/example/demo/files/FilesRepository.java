package com.example.demo.files;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.board.Board;


public interface FilesRepository extends JpaRepository<Files, Integer> {
	
    //	Files findByFno(int fno);
	@Query(value = "select * from files where boardimg = ?1", nativeQuery = true)
	Files findByboardimg(int num); 

}
