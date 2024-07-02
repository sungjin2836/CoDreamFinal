package com.code.dream.dto;

public class PageDto {

	private int count;	// 전체 글 개수
	private int start;	// 불러올 글의 시작 번호
	private int end;	// 불러올 글의 마지막 번호
	private int step;	// 한번에 불러올 글의 개수
	private int page;	// 현재 페이지
	private int pagestep;	// 한번에 표시할 페이지 개수
	private int pagestart;	// 표시할 페이지 시작
	private int pageend;	// 표시할 페이지 끝
	
	public PageDto(int count) {
		this.count = count;
		this.step = 10;
		this.page = 1;
		this.pagestep = 5;
		this.pagestart = (page-1)*pagestep+1;
		this.pageend = page*pagestep;
		this.start = (page-1)*step+1;
		this.end = page*step;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page < 1) {
			page = 1;
		} else if (page > count/step + 1) {
			page = count/step + 1;
		}
		this.page = page;
		this.start = (page-1)*step+1;
		this.end = page*step;
		this.pagestart = ((page-1)/pagestep)*pagestep+1;
		this.pageend = (((page-1)/pagestep)+1)*pagestep;
	}

	public int getPagestep() {
		return pagestep;
	}

	public void setPagestep(int pagestep) {
		this.pagestep = pagestep;
	}

	public int getPagestart() {
		return pagestart;
	}

	public void setPagestart(int pagestart) {
		this.pagestart = pagestart;
	}

	public int getPageend() {
		if (pageend > count/step + 1) {
			this.pageend = count/step + 1;
		}
		return pageend;
	}

	public void setPageend(int pageend) {
		this.pageend = pageend;
	}

	@Override
	public String toString() {
		return "PageDto [count=" + count + ", start=" + start + ", end=" + end + ", step=" + step + ", page=" + page
				+ ", pagestep=" + pagestep + ", pagestart=" + pagestart + ", pageend=" + pageend + "]";
	}
	
}
