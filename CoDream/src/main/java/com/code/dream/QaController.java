package com.code.dream;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.code.dream.dto.QaDto;
import com.code.dream.dto.RegisterDto;
import com.code.dream.qa.IQaService;
import com.code.dream.security.UserSecurityDto;

@Controller
public class QaController {
	
	@Autowired
	private IQaService service;

	@RequestMapping(value = "/qa/insertpage", method = { RequestMethod.GET, RequestMethod.POST })
	public String insertQa(Model model, Authentication authentication) {
		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
		RegisterDto dto = usDto.getDto();
		model.addAttribute("dto", dto);
		return "/mypage/insertQa";
	}
	
	@RequestMapping(value = "/qa/qalist", method = { RequestMethod.GET, RequestMethod.POST })
	public String detailQa(Model model) {
		List<QaDto> lists = service.qalist();
		model.addAttribute("lists", lists);
		return "/mypage/qaList";
	}
}
