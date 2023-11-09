package com.rocha.app.sqs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;

@Service
public class MessageSenderWithTemplate {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageSenderWithTemplate.class);
	
	private static final String TEST_QUEUE = "testQueue";
	
	private QueueMessagingTemplate messagingTemplate;
	
	 public void send(final String messagePayload) {
		 
		 Message<String> msg = MessageBuilder.withPayload(messagePayload)
					.setHeader("sender", "app1")
					.setHeaderIfAbsent("country", "AE")
					.build();
	        messagingTemplate.convertAndSend(TEST_QUEUE, msg);
	        LOGGER.info("message sent");
		 
	 }
	 
	 public void sendToFifoQueue(final String messagePayload, final String messageGroupID, final String messageDedupID) {
		 
		 Message<String> msg = MessageBuilder.withPayload(messagePayload)
					.setHeader("message-group-id", messageGroupID)
					.setHeader("message-deduplication-id", messageDedupID)
					.build();
	        messagingTemplate.convertAndSend(TEST_QUEUE, msg);
	        LOGGER.info("message sent");
		 
	 }
	

}
