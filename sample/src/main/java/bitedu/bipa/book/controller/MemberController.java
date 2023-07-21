package bitedu.bipa.book.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller("MemberController")
@RequestMapping("/member")
public class MemberController {

	@RequestMapping(value = "/view_loginForm.do", method = RequestMethod.GET)
	public ModelAndView viewLoginForm() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("./member/loginForm");
		
		return mav;
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("id") String id, @RequestParam("pwd") String pwd, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String url = "./member/loginForm";
		if(id.equals("admin") && pwd.equals("1234")) {
			url = "./member/gatehome";
		}
		session.setAttribute("id", id);
		mav.setViewName(url);
		
		return mav;
	}
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) { //Container에서 알아서 현재 Session 줘
		ModelAndView mav = new ModelAndView();
		
		session.invalidate(); //
		
		mav.setViewName("./member/gatehome");
		
		return mav;
	}
}
