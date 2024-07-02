package com.code.dream.student;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.dream.dto.DocumentDto;
import com.code.dream.dto.MemoDto;
import com.code.dream.dto.StudentDto;

@Repository
public class StudentDaoImpl implements IStudentDao {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);
	private final String NS = "student.";
	
	@Autowired
	private SqlSessionTemplate session;


	@Override
	public boolean insertStudent(StudentDto dto) {
		logger.info("[StudentDaoImpl] insertStudent 수강신청 {}", dto);
		int n = session.insert(NS+"insertStudent", dto);
		return (n>0)?true:false;
	}


	@Override
	public boolean checkStudent(Map<String, String> map) {
		logger.info("[StudentDaoImpl] checkStudent 수강신청 여부 확인 {}", map);
		List<StudentDto> list = session.selectList(NS+"checkStudent", map);
		int n = list.size();
		return (n>0)?true:false;
	}


	@Override
	public boolean insertVisit(StudentDto dto) {
		logger.info("[StudentDaoImpl] insertVisit 출결 입력 {}", dto);
		int n = session.insert(NS+"insertVisit",dto);
		return (n>0)?true:false;
	}


	@Override
	public StudentDto visitList(StudentDto dto) {
		logger.info("[StudentDaoImpl] visitList 출결 조회 {}", dto);
		return session.selectOne(NS+"visitList",dto); // 널 처리가 반드시 필요함
	}


	@Override
	public List<MemoDto> memoList(int cl_seq) {
		logger.info("[StudentDaoImpl] memoList 필기 전체 조회 {}", cl_seq);
		return session.selectList(NS+"memoList", cl_seq);
	}


	@Override
	public MemoDto memoDetail(int me_seq) {
		logger.info("[StudentDaoImpl] memoDetail 필기 상세 조회 {}", me_seq);
		return session.selectOne(NS+"memoDetail", me_seq);
	}


	@Override
	public boolean insertMemo(MemoDto dto) {
		logger.info("[StudentDaoImpl] insertMemo 필기 입력 {}", dto);
		int n = session.insert(NS+"insertMemo", dto);
		return (n>0)?true:false;
	}


	@Override
	public List<DocumentDto> docList(int cl_seq) {
		logger.info("[StudentDaoImpl] docList 강의 자료 전체 조회 {}");
		return session.selectList(NS+"docList",cl_seq);
	}


	@Override
	public DocumentDto docDetail(int doc_seq) {
		logger.info("[StudentDaoImpl] docDetail 강의 자료 상세 조회 {}", doc_seq);
		return session.selectOne(NS+"docDetail",doc_seq);
	}


	@Override
	public boolean insertDoc(DocumentDto dto) {
		logger.info("[StudentDaoImpl] insertDoc 강의 자료 입력 {}", dto);
		int n = session.insert(NS+"insertDoc",dto);
		return (n>0)?true:false;
	}


	@Override
	public List<StudentDto> studentList(int cl_seq) {
		logger.info("[StudentDaoImpl] studentList 수강생 조회 {}", cl_seq);
		return session.selectList(NS+"studentList",cl_seq);
	}


	@Override
	public List<MemoDto> recentMemoList(int cl_seq) {
		logger.info("[StudentDaoImpl] recentMemoList 최근 필기 조회 {}", cl_seq);
		return session.selectList(NS+"recentMemoList",cl_seq);
	}


	@Override
	public List<DocumentDto> recentDocList(int cl_seq) {
		logger.info("[StudentDaoImpl] recentDocList 최근 강의 자료 조회 {}", cl_seq);
		return session.selectList(NS+"recentDocList",cl_seq);
	}
	
	@Override
	public boolean dropStudent(Map<String, String> map) {
		int n=session.delete(NS+"dropStudent", map);
		return (n>0)?true:false;
	}
}
