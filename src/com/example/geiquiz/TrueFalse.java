package com.example.geiquiz;



public class TrueFalse {
	private int mQuestion;
	private boolean mTrueQuestion;
	public int getQuestion() {
		return mQuestion;
	}
	public void setQuestion(int question) {
		mQuestion = question;
	}
	public boolean isTrueQuestion() {
		return mTrueQuestion;
	}
	public void setTrueQuestion(boolean trueQuestion) {
		mTrueQuestion = trueQuestion;
	}
	public TrueFalse(int mQuestion,boolean mTrueQuestion)
	{
		this.mQuestion=mQuestion;
		this.mTrueQuestion=mTrueQuestion;
	}
}
