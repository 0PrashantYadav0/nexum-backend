package com.pixels.Nexum.service;

import com.pixels.Nexum.model.Message;
import com.pixels.Nexum.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message createMessage(Message messageModel) {
        return this.messageRepository.save(messageModel);
    }

    @Override
    public Message getMessageByUserId(int userId) {
        return this.messageRepository.findById(userId).get();
    }

    @Override
    public List<Message> getMessageByWorkerId(Integer workerId) {
       List<Message> messages = this.messageRepository.findAll();
       List<Message> workerMessages = new ArrayList<>();
         for(Message message: messages){
              if(message.getWorkerId() == workerId){
                workerMessages.add(message);
              }
         }
         return workerMessages;
    }
}
