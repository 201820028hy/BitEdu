package bitedu.bipa.tiles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("boardController")
@RequestMapping("/guestbook")
public class BoardController {

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("data", "GuestBook");
		mav.setViewName("/board/list");
		
		return mav;
	}
}
