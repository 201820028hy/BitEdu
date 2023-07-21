package bitedu.bipa.tiles.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.tiles.vo.User;

@Controller("memberController")
@RequestMapping("/member")
public class MemberController {

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("data", "Member List페이지 입니다.");
		mav.setViewName("/member/list");
		
		return mav;
	}
	
	@RequestMapping(value = "/viewLogin.do", method = RequestMethod.GET)
	public ModelAndView viewLogin() {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("data", "Login Form");
		mav.setViewName("/member/viewLogin");
		
		return mav;
	}
	
	@RequestMapping(value = "/viewRegist.do", method = RequestMethod.GET)
	public ModelAndView viewRegist() {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("data", "Regist Form");
		mav.setViewName("/member/viewRegist");
		
		return mav;
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("id") String id, @RequestParam("pwd") String pwd, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		String url = "/member/loginForm";
		if(id.equals("admin") && pwd.equals("1234")) {
			session.setAttribute("user", new User(id, pwd));
			url = "main";
		}
		
		mav.setViewName(url);
		
		return mav;
	}
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		session.invalidate();
		
		mav.setViewName("main");
		
		return mav;
	}
}
