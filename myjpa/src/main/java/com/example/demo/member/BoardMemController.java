package com.example.demo.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class BoardMemController {
	
	@Autowired
	private BoardMemService service;
	
	@GetMapping("/join") // join폼으로 이동
	public void joinForm() {
		// 반환값이 void이면 url과 동일한 뷰페이지로 이동. /member/join.jsp
	}

	@PostMapping("/join")
	public String join(BoardMem m) {
		service.join(m);
		return "redirect:/member/login";
	}

	@PostMapping("/id-check/{id}")
	public String idCheck(@PathVariable("id") String id, Map map) {
		BoardMem m = service.getMember(id);
		boolean flag = false;
		if (m == null) {
			flag = true;
		}
		map.put("flag", flag);
		return "member/idCheck";
	}
	
	@GetMapping("/login")
	public void loginForm() {}
	
	@PostMapping("/login")
	public String login(String id, String pwd, HttpServletRequest req, Map map) {
		String path = "member/login";
		BoardMem m = service.getMember(id);
		
		map.put("info", m); //my
		
		if(m!=null && pwd.equals(m.getPwd())) {
			HttpSession session = req.getSession();
			session.setAttribute("id", id);
			path = "member/result";
		}
		return path;
	}
	
//	@GetMapping("/info")
//	public void editInfo(HttpServletRequest req, Map map) {
//		HttpSession session = req.getSession(false);
//		String id = (String) session.getAttribute("id");
//		BoardMem m = service.getMember(id);
//		map.put("m", m);
//	}
	
	@GetMapping("/result")
	public String result(HttpServletRequest req) {
		String path="member/login";
		HttpSession session = req.getSession(false);
		String id = (String) session.getAttribute("id");
		if(id!=null && !id.equals("")) {
			path = "member/result";
		}
		return path;
	}
	
	@GetMapping("/edit")
	public String editForm(HttpServletRequest req, Map map) {
		HttpSession session = req.getSession(false);
		String id = (String) session.getAttribute("id");
		BoardMem m = service.getMember(id);
		map.put("editInfo", m);
		return "member/edit";
	}
	
	@PostMapping("/edit")
	public String edit(BoardMem m,Map map) {
		service.editMember(m);
		m = service.getMember(m.getId());
		
		map.put("info",m);
		
		return "member/result";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		session.removeAttribute("id");
		session.invalidate();
		return "member/login";
	}
	
	@GetMapping("/out")
	public String out(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		String id = (String) session.getAttribute("id");
		service.delMember(id);
		session.removeAttribute("id");
		session.invalidate();
		return "member/login";
	}
	
	@PostMapping("/s-writer")
	public String searchByWriter(String writer, Map map) {
		BoardMem m = service.getMember(writer);
		map.put("list", m.getList());
		return "board/list";
	}
}






