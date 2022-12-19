package ll.chattingexample.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RsData<T>{
    private String resultCode;
    private String message;
    private T data;
}
