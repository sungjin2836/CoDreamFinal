package com.code.dream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.code.dream.classes.IClassService;
import com.code.dream.dto.ClassDto;
import com.code.dream.dto.RegisterDto;
import com.code.dream.security.UserSecurityDto;

@Controller
public class ClassController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClassController.class);
	
	@Autowired
	IClassService iClassService;
	
	@RequestMapping(value = "/board/classList", method = RequestMethod.GET)
	public String classList(Model model, String hash) {
		logger.info("[ClassController] classList 강의 전체 조회 페이지 {}", hash);
		
		// Hash 리스트를 받아서 Model로 넘겨줌
		List<Map<Integer, String>> hList = iClassService.checkHash(null);
		model.addAttribute("hList", hList);
		
		List<ClassDto> cList = null;
		
		if(hash == null) { // 아무 값도 검색하지 않았을 경우, 전체 조회
			// ClassDto에 조회 결과값을 받아 Model에 넘겨줌 
			cList = iClassService.classList();
		} else {
			cList = iClassService.hashedClassList(hash);
		}
		
		model.addAttribute("cList",cList);
		
		return "/board/classList";
	}
	
	@RequestMapping(value = "/board/classInput", method = RequestMethod.GET)
	public String classInputPage(Model model) {
		logger.info("[ClassController] classInput 강의 개설 페이지");
		
		// 권한이 맞지 않는다면 "잘못된 접근입니다." 표시 후 되돌려보냄
		// 권한이 맞다면 화면으로 넘겨줌
		
		return "/board/classInput";
	}
	
	@RequestMapping(value = "/board/jusoPopup", method = RequestMethod.GET)
	public String jusoPopup(Model model) {
		logger.info("[ClassController] jusoPopup 도로명주소 검색 팝업");
		model.addAttribute("inputYn", "");
		model.addAttribute("roadFullAddr", "");
		return "/board/jusoPopup";
	}
	
	@RequestMapping(value = "/board/jusoPopup", method = RequestMethod.POST)
	public String jusoPopupSuccess(String inputYn, String roadFullAddr, String addrDetail, Model model) {
		logger.info("[ClassController] jusoPopup 도로명주소 검색 완료");
		System.out.println(roadFullAddr);
		model.addAttribute("inputYn", "Y");
		model.addAttribute("roadFullAddr", roadFullAddr);
		return "/board/jusoPopup";
	}
	
	@RequestMapping(value = "/board/classInput", method = RequestMethod.POST)
	public String classInput(Authentication authentication, ClassDto cdto, @RequestParam String hash, @RequestParam String sday, @RequestParam String eday) {
		logger.info("[ClassController] classInput 강의 개설 실행");
		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
		RegisterDto rdto = usDto.getDto();
		String teacher = rdto.getId();
		cdto.setTeacher(teacher);
		
		// 날짜 값을 연결해줌
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			cdto.setStartday(sdf.parse(sday));
			cdto.setEndday(sdf.parse(eday));
			logger.info("[ClassController] classInput 개설 강의 정보 {}", cdto);
			
			// 문제가 없을 경우, 데이터를 삽입합니다
			boolean isc = iClassService.insertClass(cdto, hash);
			if(isc) { // 성공 시
				return "redirect:/board/classDetail?cl_seq="+cdto.getCl_seq();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "redirect:/board/classList";
	}
	
	@RequestMapping(value = "/board/classDetail", method = RequestMethod.GET)
	public String classDetail(Model model, int cl_seq) {
		logger.info("[ClassController] classDetail 강의 상세조회");
		ClassDto cdto = iClassService.classDetail(cl_seq);
		if(cdto != null) {
			model.addAttribute("cDto", cdto);
			List<Map<Integer, String>> hashMaps = cdto.getHashList(); // 해시

			if (hashMaps.size() != 0) { // hashMaps의 사이즈가 0이 아닐 경우
				String[] hashList = new String[cdto.getHashList().size()]; // 해시 내용만 받을 배열

				// List<Map<Integer, String>> 형식의 HashList를 풀어서 Value만 넣어야 함
				for (int i = 0; i < hashMaps.size(); i++) {
					hashList[i] = (hashMaps.get(i)).get("value"); // value만 뽑는다
				}

				logger.info("[ClassController] classDetail 해시리스트 : {}", hashList);

				Map<String, String[]> hmap = new HashMap<String, String[]>(); // dao로 보낼 해시맵
				hmap.put("hashList", hashList);

				// service 실행
				List<ClassDto> pList = iClassService.cheapestClass(hmap);
				logger.info("[ClassController] classDetail 가격비교 리스트 : {}", pList);
				model.addAttribute("pList", pList); // 가격 비교 강의 리스트 전송(cl_seq, cl_ttile, price)
			}
			
			return "/board/classDetail";
		} else {
			return "redirect:/board/classList";
		}
		
	}
	
	@RequestMapping(value = "/mypage/regClassList", method = RequestMethod.GET)
	public String regClassList(Authentication authentication, Model model) {
		logger.info("[ClassController] regClassList 수강 중인 강의 조회");
		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
		RegisterDto rdto = usDto.getDto();
		String id = rdto.getId();
		
		List<ClassDto> list = iClassService.regClassList(id);
		model.addAttribute("clist", list);
		
		return "/mypage/regClassList";
	}
	
	@RequestMapping(value = "/mypage/teachClassList", method = RequestMethod.GET)
	public String teachClassList(Authentication authentication, Model model) {
		logger.info("[ClassController] teachClassList 나의 개설 강의 조회");
		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
		RegisterDto rdto = usDto.getDto();
		String id = rdto.getId();
		
		List<ClassDto> list = iClassService.teachClassList(id);
		model.addAttribute("clist", list);
		
		return "/mypage/teachClassList";
	}
}
