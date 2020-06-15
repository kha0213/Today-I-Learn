package com.kaoni.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kaoni.adm.service.MenuService;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * <pre>
 * com.kaoni.login
 *   |_ LoginController.java
 * </pre>
 * 
 * Desc : 로그인/로그아웃 기능을 수행하는 컨트롤러
 * @Company : KAONI
 * @Author  : NC294
 * @Date    : 2020. 3. 24. 오후 2:11:25
 * @Version : 
 */
@Controller
public class LoginController {

	private Logger LOGGER = Logger.getLogger(LoginController.class);
	
	@Resource(name = "menuService")
	private MenuService menuService;
	
	/**
	 * Desc : 로그인 화면으로 이동한다.
	 * @Method Name : loginView
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loginView.do")
	public String loginView(ModelMap model) throws Exception {
		LOGGER.info("== LOGIN PAGE LOAD START!!");
		LOGGER.info("== LOGIN PAGE LOAD END!!");
		return "login/login";
	}
	
	/**
	 * Desc : 로그아웃 화면으로 이동한다.
	 * @Method Name : logoutView
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logoutView.do")
	public String logoutView(ModelMap model) throws Exception {
		LOGGER.info("== LOGOUT PAGE LOAD START!!");
		LOGGER.info("== LOGOUT PAGE LOAD END!!");
		return "login/logout";
	}
	
//	/**
//	 * Desc : 로그인 기능을 수행한다.
//	 * @Method Name : login
//	 * @param model
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/login.do")
//	public String login(ModelMap model) throws Exception {
//		LOGGER.info("== LOGIN START!!");
//		LOGGER.info("== LOGIN END!!");
//		return "redirect:/main.do";
//	}
	
//	/**
//	 * Desc : 로그아웃 기능을 수행한다.
//	 * @Method Name : logout
//	 * @param model
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/logout.do")
//	public String logout(ModelMap model) throws Exception {
//		LOGGER.info("== LOGOUT START!!");
//		LOGGER.info("== LOGOUT END!!");
//		return "redirect:/loginView.do";
//	}
}
