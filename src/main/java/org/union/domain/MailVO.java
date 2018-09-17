package org.union.domain;

import java.util.Date;

public class MailVO {
	
	private Integer n_idx;
	private Integer MSGID;
	private String M_id;
	private String M_subject;
	private Integer SEQ;
	private String M_body;
	private Integer M_sender_idx;
	private String M_sender;
	private Integer M_recipi_idx;
	private String M_recipi;
	private Integer M_group_idx;
	private String M_group;
	private Integer M_seq_number;
	private Integer M_invitation;
	private Integer FINALRESULT;
	private Integer M_type;
	private String M_mail_type;
	private Integer M_keyword_idx;
	private String M_keyword;
	private Integer successNum;
	private Integer failNum;
	private Integer sendCountNum;
	private String PROCESSYN;
	private String SENDRESULT;
	private String RSLTMSG;
	private String sendCount;
	private String success;
	private String fail;
	private Date M_send;
	private Date M_regdate;
	private Integer total;
	public Integer getN_idx() {
		return n_idx;
	}
	public void setN_idx(Integer n_idx) {
		this.n_idx = n_idx;
	}
	public Integer getMSGID() {
		return MSGID;
	}
	public void setMSGID(Integer mSGID) {
		MSGID = mSGID;
	}
	public String getM_id() {
		return M_id;
	}
	public void setM_id(String m_id) {
		M_id = m_id;
	}
	public String getM_subject() {
		return M_subject;
	}
	public void setM_subject(String m_subject) {
		M_subject = m_subject;
	}
	public Integer getSEQ() {
		return SEQ;
	}
	public void setSEQ(Integer sEQ) {
		SEQ = sEQ;
	}
	public String getM_body() {
		return M_body;
	}
	public void setM_body(String m_body) {
		M_body = m_body;
	}
	public Integer getM_sender_idx() {
		return M_sender_idx;
	}
	public void setM_sender_idx(Integer m_sender_idx) {
		M_sender_idx = m_sender_idx;
	}
	public String getM_sender() {
		return M_sender;
	}
	public void setM_sender(String m_sender) {
		M_sender = m_sender;
	}
	public Integer getM_recipi_idx() {
		return M_recipi_idx;
	}
	public void setM_recipi_idx(Integer m_recipi_idx) {
		M_recipi_idx = m_recipi_idx;
	}
	public String getM_recipi() {
		return M_recipi;
	}
	public void setM_recipi(String m_recipi) {
		M_recipi = m_recipi;
	}
	public Integer getM_group_idx() {
		return M_group_idx;
	}
	public void setM_group_idx(Integer m_group_idx) {
		M_group_idx = m_group_idx;
	}
	public String getM_group() {
		return M_group;
	}
	public void setM_group(String m_group) {
		M_group = m_group;
	}
	public Integer getM_seq_number() {
		return M_seq_number;
	}
	public void setM_seq_number(Integer m_seq_number) {
		M_seq_number = m_seq_number;
	}
	public Integer getM_invitation() {
		return M_invitation;
	}
	public void setM_invitation(Integer m_invitation) {
		M_invitation = m_invitation;
	}
	public Integer getFINALRESULT() {
		return FINALRESULT;
	}
	public void setFINALRESULT(Integer fINALRESULT) {
		FINALRESULT = fINALRESULT;
	}
	public Integer getM_type() {
		return M_type;
	}
	public void setM_type(Integer m_type) {
		M_type = m_type;
	}
	public String getM_mail_type() {
		return M_mail_type;
	}
	public void setM_mail_type(String m_mail_type) {
		M_mail_type = m_mail_type;
	}
	public Integer getM_keyword_idx() {
		return M_keyword_idx;
	}
	public void setM_keyword_idx(Integer m_keyword_idx) {
		M_keyword_idx = m_keyword_idx;
	}
	public String getM_keyword() {
		return M_keyword;
	}
	public void setM_keyword(String m_keyword) {
		M_keyword = m_keyword;
	}
	public Integer getSuccessNum() {
		return successNum;
	}
	public void setSuccessNum(Integer successNum) {
		this.successNum = successNum;
	}
	public Integer getFailNum() {
		return failNum;
	}
	public void setFailNum(Integer failNum) {
		this.failNum = failNum;
	}
	public Integer getSendCountNum() {
		return sendCountNum;
	}
	public void setSendCountNum(Integer sendCountNum) {
		this.sendCountNum = sendCountNum;
	}
	public String getPROCESSYN() {
		return PROCESSYN;
	}
	public void setPROCESSYN(String pROCESSYN) {
		PROCESSYN = pROCESSYN;
	}
	public String getSENDRESULT() {
		return SENDRESULT;
	}
	public void setSENDRESULT(String sENDRESULT) {
		SENDRESULT = sENDRESULT;
	}
	public String getRSLTMSG() {
		return RSLTMSG;
	}
	public void setRSLTMSG(String rSLTMSG) {
		RSLTMSG = rSLTMSG;
	}
	public String getSendCount() {
		return sendCount;
	}
	public void setSendCount(String sendCount) {
		this.sendCount = sendCount;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getFail() {
		return fail;
	}
	public void setFail(String fail) {
		this.fail = fail;
	}
	public Date getM_send() {
		return M_send;
	}
	public void setM_send(Date m_send) {
		M_send = m_send;
	}
	public Date getM_regdate() {
		return M_regdate;
	}
	public void setM_regdate(Date m_regdate) {
		M_regdate = m_regdate;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		return "MailVO [n_idx=" + n_idx + ", MSGID=" + MSGID + ", M_id=" + M_id + ", M_subject=" + M_subject + ", SEQ="
				+ SEQ + ", M_body=" + M_body + ", M_sender_idx=" + M_sender_idx + ", M_sender=" + M_sender
				+ ", M_recipi_idx=" + M_recipi_idx + ", M_recipi=" + M_recipi + ", M_group_idx=" + M_group_idx
				+ ", M_group=" + M_group + ", M_seq_number=" + M_seq_number + ", M_invitation=" + M_invitation
				+ ", FINALRESULT=" + FINALRESULT + ", M_type=" + M_type + ", M_mail_type=" + M_mail_type
				+ ", M_keyword_idx=" + M_keyword_idx + ", M_keyword=" + M_keyword + ", successNum=" + successNum
				+ ", failNum=" + failNum + ", sendCountNum=" + sendCountNum + ", PROCESSYN=" + PROCESSYN
				+ ", SENDRESULT=" + SENDRESULT + ", RSLTMSG=" + RSLTMSG + ", sendCount=" + sendCount + ", success="
				+ success + ", fail=" + fail + ", M_send=" + M_send + ", M_regdate=" + M_regdate + ", total=" + total
				+ "]";
	}
}
