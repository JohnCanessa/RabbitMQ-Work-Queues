package com.canessa.workqueues.WorkQueues;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
 * 
 */
public class App 
{
	
	/*
	 * main entry point
	 */
    public static void main( String[] args) throws IOException, TimeoutException {
    	
    	// **** ****
    	System.out.println("main <<< args.length: " + args.length);
    	for (String arg : args)
    		System.out.println("arg ==>" + arg + "<==");
    	
    	// **** proceed based on the calling arguments ****
    	if (args.length == 0) {
    		Worker worker = new Worker();
    		
    		worker.processWork();
    	}
    	
    	// **** add new task to the queue ****
    	else if (args.length == 1) {
    		
    		// **** ****
    		NewTask newTask = new NewTask();
    		
    		// **** ****
    		String arg = args[0];
    		System.out.println("main <<< arg ==>" + arg + "<==");
    		
    		newTask.postWork(arg);
    	}
    	
    	// **** invalid set of arguments ****
    	else {
    		System.err.println("main <<< UNEXTECTED args.length: " + args.length);
    	}
    	
    	// **** ****
    	System.out.println("main <<< done");

    }
}
