package com.infinityicon.gretest;

public class Question {
	int q_id;
	int q_subject;
	String q_question;
	String q_o1;
	String q_o2;
	String q_o3;
	String q_o4;
	String q_o5;
	String q_o6;
	String q_a;
	
	public Question(int q_id, int q_subject, String q_question, String q_o1,
			String q_o2, String q_o3, String q_o4, String q_o5, String q_o6,
			String q_a) {
		super();
		this.q_id = q_id;
		this.q_subject = q_subject;
		this.q_question = q_question;
		this.q_o1 = q_o1;
		this.q_o2 = q_o2;
		this.q_o3 = q_o3;
		this.q_o4 = q_o4;
		this.q_o5 = q_o5;
		this.q_o6 = q_o6;
		this.q_a = q_a;
	}

	public int getQ_id() {
		return q_id;
	}

	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}

	public int getQ_subject() {
		return q_subject;
	}

	public void setQ_subject(int q_subject) {
		this.q_subject = q_subject;
	}

	public String getQ_question() {
		return q_question;
	}

	public void setQ_question(String q_question) {
		this.q_question = q_question;
	}

	public String getQ_o1() {
		return q_o1;
	}

	public void setQ_o1(String q_o1) {
		this.q_o1 = q_o1;
	}

	public String getQ_o2() {
		return q_o2;
	}

	public void setQ_o2(String q_o2) {
		this.q_o2 = q_o2;
	}

	public String getQ_o3() {
		return q_o3;
	}

	public void setQ_o3(String q_o3) {
		this.q_o3 = q_o3;
	}

	public String getQ_o4() {
		return q_o4;
	}

	public void setQ_o4(String q_o4) {
		this.q_o4 = q_o4;
	}

	public String getQ_o5() {
		return q_o5;
	}

	public void setQ_o5(String q_o5) {
		this.q_o5 = q_o5;
	}

	public String getQ_o6() {
		return q_o6;
	}

	public void setQ_o6(String q_o6) {
		this.q_o6 = q_o6;
	}

	public String getQ_a() {
		return q_a;
	}

	public void setQ_a(String q_a) {
		this.q_a = q_a;
	}
}
