package model;

import java.util.ArrayList;
import java.util.Objects;

public class Question {

	private static int counter =0;
	private int questionNumber;
	private String question;
	private ArrayList<String> answers;
	private int correctAnswerNum;
	private int questionDifficulty;
	
	public Question(String question, ArrayList<String> answers, int correctAnswerNum , int questionDifficulty) {
		setCounter(getCounter()+1);
		this.questionNumber = getCounter();
		this.question = question;
		this.answers = answers;
		this.correctAnswerNum = correctAnswerNum;
		this.questionDifficulty=questionDifficulty;
		
	}
	
	
	public static int getCounter() {
		return counter;
	}
	public static void setCounter(int counter) {
		Question.counter = counter;
	}
	public int getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public ArrayList<String> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}
	public int getCorrectAnswerNum() {
		return correctAnswerNum;
	}
	public void setCorrectAnswerNum(int correctAnswerNum) {
		this.correctAnswerNum = correctAnswerNum;
	}


	public int getQuestionDifficulty() {
		return questionDifficulty;
	}


	public void setQuestionDifficulty(int questionDifficulty) {
		this.questionDifficulty = questionDifficulty;
	}

	 @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null || getClass() != obj.getClass()) return false;
	        Question question = (Question) obj;
	        return getQuestionNumber() == question.getQuestionNumber();
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(getQuestionNumber());
	    }
	@Override
	public String toString() {
		return "Question [questionNumber=" + questionNumber + ", question=" + question + ", answers=" + answers
				+ ", correctAnswerNum=" + correctAnswerNum + ", questionDifficulty=" + questionDifficulty + "]";
	}

	
}
