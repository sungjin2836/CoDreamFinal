package com.code.dream.student;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dream.dto.DocumentDto;
import com.code.dream.dto.MemoDto;
import com.code.dream.dto.StudentDto;

@Service
public class StudentServiceImpl implements IStudentService {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Autowired
	IStudentDao dao;
	
	@Override
	public boolean insertStudent(StudentDto dto) {
		logger.info("[StudentServiceImpl] insertStudent 수강신청 {}", dto);
		return dao.insertStudent(dto);
	}


	@Override
	public boolean checkStudent(Map<String, String> map) {
		logger.info("[StudentServiceImpl] checkStudent 수강신청 여부 확인 {}", map);
		return dao.checkStudent(map);
	}


	@Override
	public boolean insertVisit(StudentDto dto) {
		logger.info("[StudentServiceImpl] insertVisit 출결 입력 {}", dto);
		return dao.insertVisit(dto);
	}


	// 널 처리가 반드시 필요함
	@Override
	public StudentDto visitList(StudentDto dto) {
		logger.info("[StudentServiceImpl] visitList 출결 조회 {}", dto);
		return dao.visitList(dto);
	}


	@Override
	public List<MemoDto> memoList(int cl_seq) {
		logger.info("[StudentServiceImpl] memoList 필기 전체 조회 {}", cl_seq);
		return dao.memoList(cl_seq);
	}


	@Override
	public MemoDto memoDetail(int me_seq) {
		logger.info("[StudentServiceImpl] memoDetail 필기 상세 조회 {}", me_seq);
		return dao.memoDetail(me_seq);
	}


	@Override
	public boolean insertMemo(MemoDto dto) {
		logger.info("[StudentServiceImpl] insertMemo 필기 입력 {}", dto);
		return dao.insertMemo(dto);
	}

	@Override
	public List<DocumentDto> docList(int cl_seq) {
		logger.info("[StudentServiceImpl] docList 강의 자료 전체 조회 {}");
		return dao.docList(cl_seq);
	}


	@Override
	public DocumentDto docDetail(int doc_seq) {
		logger.info("[StudentServiceImpl] docDetail 강의 자료 상세 조회 {}", doc_seq);
		return dao.docDetail(doc_seq);
	}


	@Override
	public boolean insertDoc(DocumentDto dto) {
		logger.info("[StudentServiceImpl] insertDoc 강의 자료 입력 {}", dto);
		return dao.insertDoc(dto);
	}


	@Override
	public List<StudentDto> studentList(int cl_seq) {
		logger.info("[StudentServiceImpl] studentList 수강생 조회 {}", cl_seq);
		return dao.studentList(cl_seq);
	}


	@Override
	public List<MemoDto> recentMemoList(int cl_seq) {
		logger.info("[StudentServiceImpl] recentMemoList 최근 필기 조회 {}", cl_seq);
		return dao.recentMemoList(cl_seq);
	}


	@Override
	public List<DocumentDto> recentDocList(int cl_seq) {
		logger.info("[StudentServiceImpl] recentDocList 최근 강의 자료 조회 {}", cl_seq);
		return dao.recentDocList(cl_seq);
	}
	
	@Override
	public boolean dropStudent(Map<String, String> map) {
		logger.info("[StudentServiceImpl] dropStudent 수강취소 {}", map);
		return dao.dropStudent(map);
	}

}
