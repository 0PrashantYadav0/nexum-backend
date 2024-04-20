package com.pixels.Nexum.controller;

import com.pixels.Nexum.model.Message;
import com.pixels.Nexum.service.MessageService;
import com.pixels.Nexum.utils.APIReturnModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Vector;

@RestController
@CrossOrigin
@RequestMapping("/api/message/")
public class MessageController {

    private APIReturnModel apiReturnModel;

    @Autowired
    private MessageService messageService;
    private Vector<Message> messageVector;

    @PostMapping("addMessage")
    public ResponseEntity<?> addMessage(@RequestBody Message message){
        apiReturnModel = new APIReturnModel();
        messageVector = new Vector<>();

        try {
            Message newMessage = new Message();
            newMessage.setUserId(message.getUserId());
            newMessage.setWorkerId(message.getWorkerId());
            newMessage.setMessage(message.getMessage());
            newMessage.setDate(message.getDate());
            newMessage.setUsername(message.getUsername());
            messageVector.add(newMessage);
            messageService.createMessage(newMessage);
            apiReturnModel.setStatus("Success");
            apiReturnModel.setStatusCode(200);
            apiReturnModel.setData(messageVector);
            apiReturnModel.setMessage("Message Added Successfully !");
            apiReturnModel.setCount(1);
        } catch (Exception e) {
            e.printStackTrace();
            apiReturnModel.setStatus("fail");
            apiReturnModel.setStatusCode(404);
            apiReturnModel.setMessage("Something went Wrong !!");
            apiReturnModel.setCount(0);

            apiReturnModel.setData(null);
        }

        return ResponseEntity.ok(apiReturnModel);
    }

    @GetMapping("getMessageByWorkerId/{workerId}")
    public ResponseEntity<?> getMessageByWorkerId(@PathVariable("workerId") Integer workerId){
        apiReturnModel = new APIReturnModel();
        messageVector = new Vector<>();

        try {
            List<Message> message = messageService.getMessageByWorkerId(workerId);
            messageVector.addAll(message);
            apiReturnModel.setData(messageVector);
            apiReturnModel.setStatus("Success");
            apiReturnModel.setStatusCode(200);
            apiReturnModel.setMessage("Message Found Successfully !");
            apiReturnModel.setCount(1);
        } catch (Exception e) {
            e.printStackTrace();
            apiReturnModel.setData(null);
            apiReturnModel.setStatus("fail");
            apiReturnModel.setStatusCode(404);
            apiReturnModel.setMessage("Something went Wrong !!");
            apiReturnModel.setCount(0);
        }

        return ResponseEntity.ok(apiReturnModel);
    }


    @GetMapping("getMessageByUserId/{userId}")
    public ResponseEntity<?> getMessageByUserId(@PathVariable("userId") int userId){
        apiReturnModel = new APIReturnModel();
        messageVector = new Vector<>();

        try {
            Message message = messageService.getMessageByUserId(userId);
            messageVector.add(message);
            apiReturnModel.setData(messageVector);
            apiReturnModel.setStatus("Success");
            apiReturnModel.setStatusCode(200);
            apiReturnModel.setMessage("Message Found Successfully !");
            apiReturnModel.setCount(1);
        } catch (Exception e) {
            e.printStackTrace();
            apiReturnModel.setData(null);
            apiReturnModel.setStatus("fail");
            apiReturnModel.setStatusCode(404);
            apiReturnModel.setMessage("Something went Wrong !!");
            apiReturnModel.setCount(0);
        }

        return ResponseEntity.ok(apiReturnModel);
    }
}
