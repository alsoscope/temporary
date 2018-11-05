package org.p.project.member.model;

import java.sql.Date;

//getter, setter, toString 메서드 세팅
public class MemberVO {
	private String userId;
	private String userPw;
	private String userName;
	private Date userRegdate;//java.sql.Date
	private Date userUpdatedate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getUserRegdate() {
		return userRegdate;
	}
	public void setUserRegdate(Date userRegdate) {
		this.userRegdate = userRegdate;
	}
	public Date getUserUpdatedate() {
		return userUpdatedate;
	}
	public void setUserUpdatedate(Date userUpdatedate) {
		this.userUpdatedate = userUpdatedate;
	}
	
	@Override
	public String toString() {
		return "MemberVO [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", userRegdate="
				+ userRegdate + ", userUpdatedate=" + userUpdatedate + "]";
	}
}
