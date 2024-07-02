package com.code.dream;

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

import com.code.dream.classes.IClassService;
import com.code.dream.dto.AttachFileDto;
import com.code.dream.dto.ClassDto;
import com.code.dream.dto.DocumentDto;
import com.code.dream.dto.MemoDto;
import com.code.dream.dto.RegisterDto;
import com.code.dream.dto.StudentDto;
import com.code.dream.regteacher.IRegteacherService;
import com.code.dream.security.UserSecurityDto;
import com.code.dream.student.IStudentService;

@Controller
public class StudentController {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	IStudentService iStudentService;
	
	@Autowired
	IClassService iClassService;
	
	@Autowired
	IRegteacherService iRegteacherService;
	
	@RequestMapping(value = "/class/studentTest", method = RequestMethod.GET)
	public String studentTest(Authentication authentication, int cl_seq) {
		logger.info("[StudentController] classMain");
		
		// 학생 아이디(로그인한 사람의 아이디)와 강의 번호를 받아 넘겨준다
		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
		RegisterDto rdto = usDto.getDto();
		String student = rdto.getId();
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("student", student);
		map.put("cl_seq", String.valueOf(cl_seq));
		
		boolean isc = iStudentService.dropStudent(map);
		
		return isc?"/board/classList":"redirect:/";
	}
	
	@RequestMapping(value = "/class/classMain", method = RequestMethod.GET)
	public String classMain(Authentication authentication, int cl_seq, Model model) {
		logger.info("[StudentController] classMain");
		
		if(checkAccess(authentication, cl_seq)) { // 만약, 해당 강의에 등록한 학생이라면
			// 강의에 대한 데이터를 받아와서 화면에 뿌려줌	
			ClassDto cDto = iClassService.classDetail(cl_seq); // 강의 정보를 가져옴		
			model.addAttribute("cDto", cDto);
			
			// 강의자료와 필기자료 조회
			model.addAttribute("dList",iStudentService.recentDocList(cl_seq));
			model.addAttribute("mList",iStudentService.recentMemoList(cl_seq));
			
			return "/class/classMain";
		} else { // 아니라면, 잘못된 접근으로 처리함
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/class/memoList", method = RequestMethod.GET)
	public String memoList(Authentication authentication, Model model, int cl_seq) {
		logger.info("[StudentController] memoList");		
		
		if(checkAccess(authentication, cl_seq)) { // 만약, 해당 강의에 등록한 학생이라면
			// 강의에 대한 데이터를 받아와서 화면에 뿌려줌	
			ClassDto cDto = iClassService.classDetail(cl_seq); // 강의 정보를 가져옴		
			List<MemoDto> list = iStudentService.memoList(cl_seq); // 필기 리스트
			
			model.addAttribute("cDto", cDto);
			model.addAttribute("list", list);
			model.addAttribute("isTeacher", isTeacher(authentication, cl_seq));
			
			return "/class/memoList";
		}else { // 아니라면, 잘못된 접근으로 처리함
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "/class/docList", method = RequestMethod.GET)
	public String docList(Authentication authentication, Model model, int cl_seq) {
		logger.info("[StudentController] docList");			
		
		if(checkAccess(authentication, cl_seq)) { // 만약, 해당 강의에 등록한 학생이라면
			// 강의에 대한 데이터를 받아와서 화면에 뿌려줌	
			ClassDto cDto = iClassService.classDetail(cl_seq); // 강의 정보를 가져옴		
			List<DocumentDto> list = iStudentService.docList(cl_seq); // 강의자료
			
			model.addAttribute("cDto", cDto);
			model.addAttribute("list", list);
			model.addAttribute("isTeacher", isTeacher(authentication, cl_seq));
			
			return "/class/docList";
		}else { // 아니라면, 잘못된 접근으로 처리함
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/class/memoDetail", method = RequestMethod.GET)
	public String memoDetail(Authentication authentication, Model model, int cl_seq, int me_seq) {
		logger.info("[StudentController] memoDetail {}", me_seq);			
		
		if(checkAccess(authentication, cl_seq)) { // 만약, 해당 강의에 등록한 학생이라면
			// 강의에 대한 데이터를 받아와서 화면에 뿌려줌	
			ClassDto cDto = iClassService.classDetail(cl_seq); // 강의 정보를 가져옴		
			MemoDto mDto = iStudentService.memoDetail(me_seq); // 강의자료 상세보기

			// 파일 받아오기
			List<AttachFileDto> list = iRegteacherService.selectFiles(String.valueOf(mDto.getFile_gid()));
			
			model.addAttribute("list",list);
			
			model.addAttribute("cDto", cDto);
			model.addAttribute("mDto", mDto);
			
			return "/class/memoDetail";
		}else { // 아니라면, 잘못된 접근으로 처리함
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/class/docDetail", method = RequestMethod.GET)
	public String docDetail(Authentication authentication, Model model, int cl_seq, int doc_seq) {
		logger.info("[StudentController] docDetail {}", doc_seq);			
		
		if(checkAccess(authentication, cl_seq)) { // 만약, 해당 강의에 등록한 학생이라면
			// 강의에 대한 데이터를 받아와서 화면에 뿌려줌	
			ClassDto cDto = iClassService.classDetail(cl_seq); // 강의 정보를 가져옴		
			DocumentDto dDto = iStudentService.docDetail(doc_seq); // 강의자료 상세보기

			// 파일 받아오기
			List<AttachFileDto> list = iRegteacherService.selectFiles(String.valueOf(dDto.getFile_gid()));

			model.addAttribute("list",list);
			
			model.addAttribute("cDto", cDto);
			model.addAttribute("dDto", dDto);
			//model.addAttribute("list",list);
			
			return "/class/docDetail";
		}else { // 아니라면, 잘못된 접근으로 처리함
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/class/insertDoc", method = RequestMethod.GET)
	public String docForm(Authentication authentication, int cl_seq, Model model) {
		logger.info("[StudentController] insertDoc");
		
		// 접근권한 확인
		boolean isc = checkAccess(authentication, cl_seq);
		boolean isTeacher = isTeacher(authentication, cl_seq);
		
		if(isc&&isTeacher) {
			UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
			RegisterDto rDto = usDto.getDto();
			
			// 강의에 대한 데이터를 받아와서 화면에 뿌려줌	
			ClassDto cDto = iClassService.classDetail(cl_seq); // 강의 정보를 가져옴
			model.addAttribute("rDto", rDto);
			model.addAttribute("cDto", cDto);
			return "/class/docInput";
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "/class/insertMemo", method = RequestMethod.GET)
	public String memoForm(Authentication authentication, int cl_seq, Model model) {
		logger.info("[StudentController] insertMemo");
		
		// 접근권한 확인
		boolean isc = checkAccess(authentication, cl_seq);
		boolean isTeacher = isTeacher(authentication, cl_seq);
		
		if(isc&&isTeacher) {
			UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
			RegisterDto rDto = usDto.getDto();
			
			// 강의에 대한 데이터를 받아와서 화면에 뿌려줌	
			ClassDto cDto = iClassService.classDetail(cl_seq); // 강의 정보를 가져옴
			model.addAttribute("rDto", rDto);
			model.addAttribute("cDto", cDto);
			return "/class/memoInput";
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "/class/insertDoc", method = RequestMethod.POST)
	public String insertDoc(Authentication authentication, int cl_seq, DocumentDto dto, Model model) {
		logger.info("[StudentController] insertDoc 자료 작성 실행");
		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
		RegisterDto rdto = usDto.getDto();
		String id = rdto.getId();
		
		dto.setCl_seq(cl_seq);
		dto.setAuthor(id);
		
		iStudentService.insertDoc(dto);
		
		return "redirect:/class/docList?cl_seq="+dto.getCl_seq();
	}
	
	@RequestMapping(value = "/class/insertMemo", method = RequestMethod.POST)
	public String insertMemo(Authentication authentication, int cl_seq, MemoDto dto, Model model) {
		logger.info("[StudentController] insertMemo 필기 작성 실행");
		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
		RegisterDto rdto = usDto.getDto();
		String id = rdto.getId();
		
		dto.setCl_seq(cl_seq);
		dto.setAuthor(id);
		
		iStudentService.insertMemo(dto);
		
		return "redirect:/class/memoList?cl_seq="+dto.getCl_seq();
	}
	
	@RequestMapping(value = "/class/visit", method = RequestMethod.GET)
	public String visit(Authentication authentication, int cl_seq, Model model) {
		logger.info("[StudentController] visit 출결 조회");
		
		// 접근권한 확인
		boolean isc = checkAccess(authentication, cl_seq);
		boolean isTeacher = isTeacher(authentication, cl_seq);
		
		// 학생인지, 강사인지에 따라 화면 분기
		if(isc) {
			ClassDto cDto = iClassService.classDetail(cl_seq); // 강의 정보를 가져옴
			model.addAttribute("cDto", cDto);
			if(isTeacher) {
				// 모든 학생의 정보를 불러온다
				List<StudentDto> list = iStudentService.studentList(cl_seq);
				model.addAttribute("list", list);
				return "/class/visitList";
			}else {
				// 한 학생의 정보를 불러온다
				UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
				RegisterDto rdto = usDto.getDto();
				String id = rdto.getId();
				
				StudentDto sDto = new StudentDto();
				sDto.setCl_seq(cl_seq);
				sDto.setStudent(id);
				return "redirect:/class/visitDetail?cl_seq="+cl_seq+"&student="+id;
			}
		} else {
			return "redirect:/";			
		}
	}
	
	@RequestMapping(value = "/class/visitDetail", method = RequestMethod.GET)
	public String visitDetail(Authentication authentication, int cl_seq, String student, Model model) {
		logger.info("[StudentController] visitDetail 출결 조회");
		
		// 접근권한 확인
		boolean isc = checkAccess(authentication, cl_seq);
		
		if(isc) {
			ClassDto cDto = iClassService.classDetail(cl_seq); // 강의 정보를 가져옴
			model.addAttribute("cDto", cDto);
			
			StudentDto sDto = new StudentDto();
			sDto.setCl_seq(cl_seq);
			sDto.setStudent(student);
			
			sDto = iStudentService.visitList(sDto);
			model.addAttribute("sDto", sDto);
			return "/class/visitDetail";
		} else {
			return "redirect:/";			
		}
		
	}
	
	
	// 강의의 관련자 혹은 관리자만 강의에 접근할 수 있도록 한다
	private boolean checkAccess(Authentication authentication, int cl_seq) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		// 학생 아이디(로그인한 사람의 아이디)와 강의 번호를 받아서
		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
		RegisterDto rdto = usDto.getDto();
		String id = rdto.getId();
		
		map.put("id", id);
		map.put("cl_seq", String.valueOf(cl_seq));
		
		// 강의에 실제로 등록한 사람인가? 혹은, 해당 강의의 강사인가?
		boolean isc = iStudentService.checkStudent(map)||iClassService.checkTeacher(map);
		
		return isc;
	}
	
	// 강사 여부 확인
	private boolean isTeacher(Authentication authentication, int cl_seq) {
		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
		RegisterDto rdto = usDto.getDto();
		String id = rdto.getId();
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("cl_seq", String.valueOf(cl_seq));
		map.put("id", id);
		
		return iClassService.checkTeacher(map); // 아이디와 강의 강사 일치여부 체크
	}
	
	
}
