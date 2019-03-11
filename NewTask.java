package com.canessa.workqueues.WorkQueues;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;


/*
 * 
 */
public class NewTask {
	
	// **** name for our queue ****
    private final static String TASK_QUEUE_NAME = "task_queue";
    
	/*
	 * constructor
	 */
	public NewTask() {
	}
	
	/*
	 * 
	 */
	public void postWork(String task) throws IOException, TimeoutException {
		
    	// **** connect to RabbitMQ ****
        ConnectionFactory factory = new ConnectionFactory();
        
        // **** default host to use for connections ****
        factory.setHost("localhost");
        
        // **** open a channel ****
        try (Connection connection = factory.newConnection();
        		
        	// **** ****
        	Channel channel = connection.createChannel()) {
        	
        	// **** declare a durable queue ****
        	boolean durable = true;
        	channel.queueDeclare(	TASK_QUEUE_NAME,		// name of queue
        							durable,				// durable
        							false,					// exclusive
        							false,					// auto delete
        							null);					// construction arguments for the queue
        	
    		// **** ****
    		String message = " " + task;
     		
        	// **** send persistent messages ****
			channel.basicPublish	("",					// exchange the exchange to publish the message to
									TASK_QUEUE_NAME,		// routingKey the routing key
									MessageProperties.PERSISTENT_TEXT_PLAIN,
									message.getBytes("UTF-8"));
			
			// **** ****
			System.out.println("postWork <<< [x] Sent message ==>" + message + "<==");
        }

	}

}
