package RestAssd;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class mainDemo {
    public static void main(String[] args){
        Result result = JUnitCore.runClasses(GooglePostTest.class,GoogleGetTest.class,GoogleUpdateTest.class,GoogleDeleteTest.class);
        for(Failure failure: result.getFailures()) {
            System.out.println(failure.toString());
        }
            System.out.println("Result == "+result.wasSuccessful());
        }
    }
