package com.travel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travel.bean.User;
import com.travel.service.UserService;
import com.travel.util.StringUtil;

@Controller
@RequestMapping(value = "login", method = { RequestMethod.GET, RequestMethod.POST })
public class LoginController {

	@Autowired
	UserService userService;
	
	@RequestMapping("login")
	public String login(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			HttpSession session = request.getSession();
			String userName=request.getParameter("userName");
			String password=request.getParameter("password");
			String imageCode=request.getParameter("imageCode");
			request.setAttribute("userName", userName);
			request.setAttribute("password", password);
			request.setAttribute("imageCode", imageCode);
			
			System.out.println("----" + userName + "----" + password);
			
			session.setAttribute("password", password);
			
			if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)){
				request.setAttribute("error", "账户或密码为空");
				return "login";
			}
			if(StringUtil.isEmpty(imageCode)){
				request.setAttribute("error", "验证码为空");
				return "login";
			}
			if(!imageCode.toUpperCase().equals(session.getAttribute("sRand").toString().toUpperCase())){
				request.setAttribute("error", "验证码错误");
				return "login";
			}
			
			User user = userService.getUserByName(userName);
			
			System.out.println("----" + user);
			
			if(user==null){
				request.setAttribute("error", "用户名不存在");
				return "mama";
			}else if(user.getPassword().equals(password)){
				if(user.getStatus().equals("1")){
					session.setAttribute("user", user);
					return "guide";
				}else{
					session.setAttribute("user", user);
					return "visitor_index";
				}
			}else{
				System.out.println("aaaaa");
				request.setAttribute("error", "密码错误");
				return "mama";
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
