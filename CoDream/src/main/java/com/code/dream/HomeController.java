package com.code.dream;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.code.dream.classes.IClassService;
import com.code.dream.dto.AttachFileDto;
import com.code.dream.dto.ClassDto;
import com.code.dream.dto.RegisterDto;
import com.code.dream.dto.RegteacherDto;
import com.code.dream.regteacher.IRegteacherService;
import com.code.dream.security.UserSecurityDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	IClassService iClassService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("[HomeController] 첫페이지");
		
		List<ClassDto> ncList = iClassService.newestClass();
		List<ClassDto> dcList = iClassService.deadlineClass();

		model.addAttribute("ncList", ncList);
		model.addAttribute("dcList", dcList);
		
		
		return "home";
	}
	
	
}
