package com.self.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.self.domain.User;
import com.self.service.UserService;
/**
* 用户登录模块
* @author Administrator
* @Time 2016-03-27 20:33:01
*
*/
@Controller
@RequestMapping("/")
public class UserController {
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	//日志
	public static Log log = LogFactory.getLog(UserController.class);
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	//登录页面
	@RequestMapping(value="default.do")
	public String login(){
		return ("redirect:/login.jsp");
	}
	
	//jsp页面初始化
	@RequestMapping(value="redirect.do")
	public String redirect(HttpServletRequest request,ModelMap model,String page){
		return "common/"+page;
	}
	
	//登录
	@RequestMapping(value="login.do", method = RequestMethod.POST)
	public String login(HttpServletRequest request,ModelMap model,@Param("username")String username,@Param("password")String password){
		log.info("输入的用户名：" + username + " 密码：" + password);
		HttpSession session = request.getSession();
		
		User user = this.userService.login(username, password);
		if(null != user){
			log.info("登录成功，用户名为" + user.getName());
			session.setAttribute("name", user.getName());
			return "common/index";
		}
		log.error("密码错误。。。。");
		return "redirect:/login.jsp";
	}
}
