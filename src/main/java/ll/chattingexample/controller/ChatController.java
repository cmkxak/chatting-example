package ll.chattingexample.controller;

import ll.chattingexample.domain.ChatMessage;
import ll.chattingexample.domain.dto.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private List<ChatMessage> chatMessages = new ArrayList<>();

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<WriteMessageResponse> writeMessage(@RequestBody WriteMessageRequest request){
        ChatMessage message = new ChatMessage(request.getAuthorName(), request.getContent());

        chatMessages.add(message);

        return new RsData<>("S-1", "메시지가 작성되었습니다.", new WriteMessageResponse(message.getId()));
    }

    @GetMapping("/messages")
    @ResponseBody
    public RsData<MessageResponse> getMessages(MessageRequest request){
        List<ChatMessage> messages = chatMessages;

        return new RsData<>("S-1", "성공", new MessageResponse(messages, messages.size()));
    }
}
