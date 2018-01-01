package com.example.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.service.impl.SmsMessageSendImpl;
import com.example.vo.SmsMessageVo;
@Component 
public class Consumer {
	@Autowired 
	private SmsMessageSendImpl smsMessageSend;
	@JmsListener(destination = "mytest.queue")  
	public void receiveQueue(String sms) {  
		 SmsMessageVo smsMessageVo = new SmsMessageVo();
		 smsMessageVo.setContent(sms);
		 smsMessageSend.sendTo(smsMessageVo);
	 }  
}
