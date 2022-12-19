package ll.chattingexample.domain.dto;


import ll.chattingexample.domain.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MessageResponse {
    private List<ChatMessage> messages;
    private int count;
}
