package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import controller.QuestionController;
import model.Question;

public class RandomQuestionTest {

    private QuestionController questionControllerInstance ;
   
    @Before
    public void setUp() {
    	
        questionControllerInstance = QuestionController.getInstance();

        // Specific Data for the test
        //first example
        ArrayList<String> answers1 = new ArrayList<>();
        String ans11= "Software Requirements Specification";
        String ans12= "Supplemental Restraint System";
        String ans13= "Scoliosis Research Society";
        String ans14= "Simple Realtime Server";
        answers1.add(ans11);
        answers1.add(ans12);
        answers1.add(ans13);
        answers1.add(ans14);
        Question q1 = new Question("What is SRS?", answers1, 1, 1);

        //second example
        ArrayList<String> answers2 = new ArrayList<>();
        String ans21= "Gantt";
        String ans22= "Data-flow testing";
        String ans23= "ERD";
        String ans24= "OLAP";
        answers2.add(ans21);
        answers2.add(ans22);
        answers2.add(ans23);
        answers2.add(ans24);
        Question q2 = new Question("Which one of these is Test Model?", answers1, 2, 2);
       
        //adding the questions to the hashmao
        questionControllerInstance.getAllQuestions().put(q1.getQuestionNumber(), q1);
        questionControllerInstance.getAllQuestions().put(q2.getQuestionNumber(), q2);

    }
    
    @Test
    public void testGetRandomQuestion() {
   
        // Test getRandomQuestion method
        Question result = questionControllerInstance.getRandomQuestion();
        assertNotNull(result);
        assertTrue( questionControllerInstance.getAllQuestions().containsValue(result));
    }
    
    @Test
    public void testGetQuestionByIDExisting() {
        int existingQuestionID = 1;
        Question result = questionControllerInstance.getQuestionByID(existingQuestionID);
        assertNotNull(result);
        assertEquals(existingQuestionID, result.getQuestionNumber());
    }

}
