package bitedu.bipa.tiles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("bookController")
@RequestMapping("/book")
public class BookController {

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("data", "도서 목록입니다.");
		mav.setViewName("/book/list");
		
		return mav;
	}
}
