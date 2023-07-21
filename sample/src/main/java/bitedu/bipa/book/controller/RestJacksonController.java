package bitedu.bipa.book.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bitedu.bipa.book.vo.BookCopy;

/*
 * spring 4버전 부터 RestController 사용
 * */
@RestController("RestJacksonController")
@RequestMapping("/rest")
public class RestJacksonController {
	
	@RequestMapping(value = "/{num}", method = RequestMethod.GET)
	public BookCopy viewDetail(@PathVariable("num") int bookSeq ) {
		return new BookCopy();
	}
	
	@RequestMapping(value = "/{num}", method = RequestMethod.POST)
	public BookCopy viewDetail(@RequestBody BookCopy book) {//VO랑 동일하게 ajax data 보내라
		return new BookCopy();
	}
}
