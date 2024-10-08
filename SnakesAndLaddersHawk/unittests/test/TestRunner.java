package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 Result resultGameUniqueID = JUnitCore.runClasses(GameUniqueID.class);

	        // Check if there are any failures in the test
	        if (resultGameUniqueID.getFailureCount() == 0) {
	            System.out.println("All tests passed successfully!");
	        } else {
	            System.out.println("Test failures:");

	            for (Failure failure : resultGameUniqueID.getFailures()) {
	                System.out.println(failure.toString());
	            }
	        }
	        
	        Result resultPlayerMoveMethod = JUnitCore.runClasses(PlayerMoveMethod.class);

	        // Check if there are any failures in the test
	        if (resultPlayerMoveMethod.getFailureCount() == 0) {
	            System.out.println("All tests passed successfully!");
	        } else {
	            System.out.println("Test failures:");

	            for (Failure failure : resultPlayerMoveMethod.getFailures()) {
	                System.out.println(failure.toString());
	            }
	        }
	        
	        Result resultRandomResult = JUnitCore.runClasses(RandomQuestionTest.class);

	        // Check if there are any failures in the test
	        if (resultRandomResult.getFailureCount() == 0) {
	            System.out.println("All tests passed successfully!");
	        } else {
	            System.out.println("Test failures:");

	            for (Failure failure : resultRandomResult.getFailures()) {
	                System.out.println(failure.toString());
	            }
	        }
	}

}
