package com.pcwk.ctrl.cmn;

public class rdVO extends DTO {
	private long rNum; // 댓글번호
	private String rdCon; // 내용
	private String rdReg; // 작성일
	private String rdName; // 작성자
	private long kNum; // 회원번호
	
	public rdVO() {}

	public rdVO(long rNum, String rdCon, String rdReg, String rdName, long kNum) {
		super();
		this.rNum = rNum;
		this.rdCon = rdCon;
		this.rdReg = rdReg;
		this.rdName = rdName;
		this.kNum = kNum;
	}

	public long getrNum() {
		return rNum;
	}

	public void setrNum(long rNum) {
		this.rNum = rNum;
	}

	public String getRdCon() {
		return rdCon;
	}

	public void setRdCon(String rdCon) {
		this.rdCon = rdCon;
	}

	public String getRdReg() {
		return rdReg;
	}

	public void setRdReg(String rdReg) {
		this.rdReg = rdReg;
	}

	public String getRdName() {
		return rdName;
	}

	public void setRdName(String rdName) {
		this.rdName = rdName;
	}

	public long getkNum() {
		return kNum;
	}

	public void setkNum(long kNum) {
		this.kNum = kNum;
	}

	@Override
	public String toString() {
		return "rdVO [rNum=" + rNum + ", rdCon=" + rdCon + ", rdReg=" + rdReg + ", rdName=" + rdName + ", kNum=" + kNum
				+ ", toString()=" + super.toString() + "]";
	}
}
