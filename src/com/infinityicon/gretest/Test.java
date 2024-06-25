package com.infinityicon.gretest;

public class Test {
	private int testID;
	private int answerID;//global unique answer var
	private int questionID;
	private String answer;
	private int wrong; //0 or 1 wrong or right
	private long testdate;
	private int subjectID; //test subject it

	public Test(int testID, int answerID, int questionID, String answer,
			int wrong, long testdate, int subjectID ) {
		super();
		this.testID = testID;
		this.answerID = answerID;
		this.questionID = questionID;
		this.answer = answer;
		this.wrong = wrong;
		this.testdate = testdate;
		this.subjectID = subjectID;
	}

	public Test(int testID, int questionID, String answer,
			int wrong, long testdate, int subjectID ) {
		super();
		this.testID = testID;
		this.questionID = questionID;
		this.answer = answer;
		this.wrong = wrong;
		this.testdate = testdate;
		this.subjectID = subjectID;
	}

	public int getTestID() {
		return testID;
	}

	public void setTestID(int testID) {
		this.testID = testID;
	}

	public int getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}

	public int getAnswerID() {
		return answerID;
	}

	public void setAnswerID(int answerID) {
		this.answerID = answerID;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getWrong() {
		return wrong;
	}

	public void setWrong(int wrong) {
		this.wrong = wrong;
	}

	public long getTestdate() {
		return testdate;
	}

	public void setTestdate(long testdate) {
		this.testdate = testdate;
	}
}
