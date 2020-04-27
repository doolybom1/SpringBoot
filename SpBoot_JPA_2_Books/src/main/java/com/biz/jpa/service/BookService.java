package com.biz.jpa.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biz.jpa.domain.BookVO;
import com.biz.jpa.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

	private final BookRepository bookDao;
	
	/*
	 * spring-data 패키지의 Pageable, Page, PageRequest 클래스를 사용하여 pagination을 구현
	 */
	public Page<BookVO> getPageList(Pageable page) {
		
		int pageNumber = 0;
		if(page.getPageNumber() == 0) {
			pageNumber = 0;
		}else {
			pageNumber = page.getPageNumber() - 1;
			page = PageRequest.of(pageNumber, 10);
		}
		return bookDao.findAll(page);
		
	}
	
	
	
	public BookVO save(BookVO bookVO) {

		BookVO vo = bookDao.save(bookVO);
		return vo;
	}

	public List<BookVO> selectAll() {

		return bookDao.findAll();
	}

	public Optional<BookVO> findById(long bookId) {
		
		Optional<BookVO> bookVO = bookDao.findById(bookId);
		return bookVO;
	}

	public void delete(long bookId) {
		
		bookDao.deleteById(bookId);
	}



	
}
