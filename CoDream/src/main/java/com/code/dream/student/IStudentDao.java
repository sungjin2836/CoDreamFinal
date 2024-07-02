package com.code.dream.student;

import java.util.List;
import java.util.Map;

import com.code.dream.dto.DocumentDto;
import com.code.dream.dto.MemoDto;
import com.code.dream.dto.StudentDto;

public interface IStudentDao {
	// 수강 신청
	public boolean insertStudent(StudentDto dto);
	
	// 수강신청 여부 확인
	public boolean checkStudent(Map<String, String> map);
	
	// 출결 입력
	public boolean insertVisit(StudentDto dto);
	
	// 출결 조회
	public StudentDto visitList(StudentDto dto);
	
	// 필기 전체 조회
	public List<MemoDto> memoList(int cl_seq);
	
	// 최근 필기 조회
	public List<MemoDto> recentMemoList(int cl_seq);
	
	// 필기 상세 조회
	public MemoDto memoDetail(int me_seq);
	
	// 필기 자료 등록
	public boolean insertMemo(MemoDto dto);
	
	// 강의 자료 전체 조회
	public List<DocumentDto> docList(int cl_seq);
	
	// 최근 강의 자료 조회
	public List<DocumentDto> recentDocList(int cl_seq);	
	
	// 강의 자료 상세 조회
	public DocumentDto docDetail(int doc_seq);
	
	// 강의 자료 등록
	public boolean insertDoc(DocumentDto dto);
	
	// 수강생 조회
	public List<StudentDto> studentList(int cl_seq);
	
	// 수강 취소
	public boolean dropStudent(Map<String, String> map);
	
}
