package com.pcwk.ehr.board.domain;

import com.pcwk.ehr.cmn.DTO;

public class CtrlVO extends DTO{
	
	private int   	oNum    ;//주문번호
	private String	oAddr   ;//주소
	private String	oName   ;//이름
	private int  	oTel    ;//전화번호   
	private String	oStatus ;//주문상태  
	private String	oDt     ;//주문날짜  
	private String	kNum    ;//회원번호  
	
	
	public int getoNum() {
		return oNum;
	}
	public void setoNum(int oNum) {
		this.oNum = oNum;
	}
	public String getoAddr() {
		return oAddr;
	}
	public void setoAddr(String oAddr) {
		this.oAddr = oAddr;
	}
	public String getoName() {
		return oName;
	}
	public void setoName(String oName) {
		this.oName = oName;
	}
	public int getoTel() {
		return oTel;
	}
	public void setoTel(int oTel) {
		this.oTel = oTel;
	}
	public String getoStatus() {
		return oStatus;
	}
	public void setoStatus(String oStatus) {
		this.oStatus = oStatus;
	}
	public String getoDt() {
		return oDt;
	}
	public void setoDt(String oDt) {
		this.oDt = oDt;
	}
	public String getkNum() {
		return kNum;
	}
	public void setkNum(String kNum) {
		this.kNum = kNum;
	}
	@Override
	public String toString() {
		return "CtrlVO [oNum=" + oNum + ", oAddr=" + oAddr + ", oName=" + oName + ", oTel=" + oTel + ", oStatus="
				+ oStatus + ", oDt=" + oDt + ", kNum=" + kNum + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
}
