package com.javaex.vo;

public class RboardVo {
	
	// 필드
	/**게시글 번호**/
	private int no;
	/**userNo**/
	private int userNo;
	/**게시글 제목**/
	private String title;
	/**게시글 내용**/
	private String content;
	/**작성자**/
	private String name;
	/**조회수**/
	private int hit;
	/**게시글 저장 날짜**/
	private String regDate;
	
	/**계층형 게시판을 위한 추가 필드**/
	
	/**원글 번호**/
	private int groupNo;
	/**원글(답글 포함)에 대한 순서**/
	private int orderNo;
	/**답글 계층**/
	private int depth;

	
	// 생성자
	public RboardVo() {
	}
	
	public RboardVo(int no, int userNo, String title, String content, String name, int hit, String regDate, int groupNo,
			int orderNo, int depth) {
		this.no = no;
		this.userNo = userNo;
		this.title = title;
		this.content = content;
		this.name = name;
		this.hit = hit;
		this.regDate = regDate;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
	}
	
	// 메소드 g/s

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	
	// 메소드 일반
	@Override
	public String toString() {
		return "RboardVo [no=" + no + ", userNo=" + userNo + ", title=" + title + ", content=" + content + ", name="
				+ name + ", hit=" + hit + ", regDate=" + regDate + ", groupNo=" + groupNo + ", orderNo=" + orderNo
				+ ", depth=" + depth + "]";
	}
	
	
}
