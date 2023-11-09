package com.rocha.app.sqs.service.receive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.rocha.app.model.SignupEvent;

import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;

@Service
public class MessageReceiver {

	private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

	@SqsListener(value = "testQueue", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void receiveMessage(String message, @Header("SenderId") String senderId) {
		logger.info("message received {} {}", senderId, message);
	}

	@SqsListener(value = "testObjectQueue", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void receiveObjectMessage(final SignupEvent message, @Header("SenderId") String senderId) {
		logger.info("object message received {} {}", senderId, message);
	}

}
