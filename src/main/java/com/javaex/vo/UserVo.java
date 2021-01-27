package com.javaex.vo;

public class UserVo {
	
	
	// 필드
	private int no;
	private String id;
	private String password;
	private String name;
	private String gender;
	
	
	// 생성자
	// 디폴트 생성자
	public UserVo() {
	}

	// getUser 생성자
	public UserVo(int no, String name) {
		this.no = no;
		this.name = name;
	}

	
	// modify 생성자
	public UserVo(int no, String password, String name, String gender) {
		super();
		this.no = no;
		this.password = password;
		this.name = name;
		this.gender = gender;
	}

	//	insert 생성자
	public UserVo(String id, String password, String name, String gender) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
	}
	
	//
	public UserVo(int no, String id, String password, String name, String gender) {
		this.no = no;
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
	}
	
	


	// 메소드 g/s
	

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	
	
	// 메소드 일반
	
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", id=" + id + ", password=" + password + ", name=" + name + ", gender=" + gender
				+ "]";
	}
	
	
	
	
	
}
