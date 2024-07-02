package com.code.dream;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.code.dream.dto.AttachFileDto;
import com.code.dream.dto.PageDto;
import com.code.dream.dto.RegisterDto;
import com.code.dream.dto.RegteacherDto;
import com.code.dream.regteacher.IRegteacherService;
import com.code.dream.security.IUserSecurityService;
import com.code.dream.security.UserSecurityDto;


@Controller
@RequestMapping("/admin/")
public class AdminController {
	
	@Autowired
	private IUserSecurityService uService;
	
	@Autowired
	private IRegteacherService rService;
	
//	@RequestMapping(value="/myInfo", method=RequestMethod.GET)
//	public String myInfo(Model model, Authentication authentication) {
//		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
//		RegisterDto dto = usDto.getDto();
//		dto.setPassword(null);
//		model.addAttribute("dto", dto);
//		return "admin/myInfo";
//	}
	
	@RequestMapping(value="memberList", method=RequestMethod.GET)
	public String memberList(Model model, HttpServletRequest request) {
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page")); 
		};
		
		int userCount = uService.userCount();
		PageDto pageDto = new PageDto(userCount);
		pageDto.setPage(page);
		List<UserSecurityDto> list = uService.selectUserList(pageDto);
		
		
		model.addAttribute("page", pageDto);
		model.addAttribute("list", list);
		
		return "admin/memberList";
	}
	
	@RequestMapping(value="deleteUser", method=RequestMethod.POST)
	@ResponseBody
	public String deleteUser(String id) {
		boolean isc = false;
		isc = uService.deleteUser(id);
		return String.valueOf(isc);
	}
	
	@RequestMapping(value="changeRole", method=RequestMethod.POST)
	@ResponseBody
	public String changeRole(String id, String role) {
		int nowLevel = uService.selectRole(id).size();
		String[] roleList = {"ROLE_USER", "ROLE_TEACHER", "ROLE_ADMIN"};
		System.out.println(id + "//" + role);
		int goalLevel = 0;
		for(int i = 0; i<roleList.length; i++) {
			if(role.equals(roleList[i])) {
				goalLevel = i+1;
			}
		}
		boolean isc = false;
		while(nowLevel != goalLevel) {
			if(nowLevel > goalLevel) {
				isc = uService.deleteRole(id, roleList[--nowLevel]);
			} else {
				isc = uService.addRole(id, roleList[nowLevel++]);
			}
			if(!isc) {
				break;
			}
		}
		
		return String.valueOf(isc);
	}
  
	@RequestMapping(value = "regteacherList", method = RequestMethod.GET)
	public String regteacherList(Model model, HttpServletRequest request) {
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page")); 
		};
		int regteacherCount = rService.regteacherCount();
		PageDto pageDto = new PageDto(regteacherCount);
		pageDto.setPage(page);
		
		List<RegteacherDto> list = rService.selectRegteacher(pageDto);
		model.addAttribute("page", pageDto);
		model.addAttribute("list", list);
		return "admin/regteacherList";
	}
	
	@RequestMapping(value = "regteacherDetail", method = RequestMethod.GET)
	public String regteacherDetail(Model model, String te_seq) {
		RegteacherDto dto = rService.detailRegteacher(te_seq);
		List<AttachFileDto> list = rService.selectFiles(te_seq);
		model.addAttribute("dto", dto);
		model.addAttribute("list", list);
		return "admin/regteacherDetail";
	}
	
	@RequestMapping(value = "regteacherModify", method = RequestMethod.GET)
	public String regteacherModify(String te_seq, String te_admit) {
		rService.modifyRegteacher(te_admit, te_seq);
		return "redirect:/admin/regteacherList";
	}
}
