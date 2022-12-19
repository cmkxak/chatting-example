package ll.chattingexample.controller;

import ll.chattingexample.domain.ChatMessage;
import ll.chattingexample.domain.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@RequestMapping("/chat")
@Controller
public class ChatController {
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @GetMapping("/room")
    public String showRoom(){
        return "chat/room";
    }


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
        List<ChatMessage> messages = filterMessage(request);
        return new RsData<>("S-1", "성공", new MessageResponse(messages, messages.size()));
    }

    private List<ChatMessage> filterMessage(MessageRequest request) {
        List<ChatMessage> messages = chatMessages;

        if (request.getFromId() != null){
            int idx = IntStream.range(0, messages.size())
                    .filter(i -> chatMessages.get(i).getId() == request.getFromId())
                    .findFirst()
                    .orElse(-1);

            if (idx != -1){
                messages = messages.subList(idx, messages.size());
            }
        }
        return messages;
    }
}
