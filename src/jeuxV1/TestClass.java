package jeuxV1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class TestClass {
   
		// timer
    	private static int heurs = 00;
    	private static int minutes = 00;
    	private static int seconds = 00;
    	 
    public static void main(String[] args) {
    	
    	
    	final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
            	System.out.println(" | Time : " + heurs + " : " + minutes + " : " + seconds);
            	
                seconds++;
                if(seconds == 60){
                	minutes++;
                	seconds = 0;
                }
                if(minutes == 60){
                	heurs++;
                	minutes = 0;
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
        
    }
}