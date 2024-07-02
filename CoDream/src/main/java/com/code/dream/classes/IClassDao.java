package com.code.dream.classes;

import java.util.List;
import java.util.Map;

import com.code.dream.dto.ClassDto;
import com.code.dream.dto.RegisterDto;
import com.code.dream.dto.StudentDto;

public interface IClassDao {

	// 강의 개설
	public boolean insertClass(ClassDto dto);
	
	// 신규 해시 등록
	public boolean insertHash(String value);
	
	// 해시태그 존재여부 검색
	public List<Map<Integer, String>> checkHash(String value);
	
	// 해시태그별 강의 조회
	public List<ClassDto> hashedClassList(String value);
	
	// 강의 해시 조회
	public List<String> linkHashList(int cl_seq);
	
	// 강의 해시 등록
	public boolean updateLinkHash(Map<String, Integer> linkhashMap);
	
	// 강의 전체 조회
	public List<ClassDto> classList();
	
	// 강의 상세 조회
	public ClassDto classDetail(int cl_seq);
	
	// 알림서비스 대상 조회
	public List<RegisterDto> emailList();
	
	// 같은 해시태그의 저렴한 강의
	public List<ClassDto> cheapestClass(Map<String, String[]> map);
	
	// 최근 개설 강의 검색
	public List<ClassDto> newestClass();
	
	// 개강 임박 강의 검색
	public List<ClassDto> deadlineClass();

	// 강사 확인
	public boolean checkTeacher(Map<String, String> map);
	
	// 수강 중인 강의 조회
	public List<ClassDto> regClassList(String id);
	
	// 나의 개설 강의 조회
	public List<ClassDto> teachClassList(String id);
	
}
