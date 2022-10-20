package MyTennis.project.dto;

import MyTennis.project.domain.Board;
import MyTennis.project.domain.Member;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@RequiredArgsConstructor //알아서 기본 생성자 만들어줌.
public class BoardUpdateForm {

    private Long id;

    @NotNull
    @Size(min=1, max=30, message = "제목은 1~30자 이내여야 합니다.")
    private String title;

    @NotNull @Size(min=1, max=100, message = "내용은 1~100자 이내여야 합니다.")
    private String content;


    // 생성자를 통해서 Board까지 가지않고 만들수있지 않을까?
    public BoardUpdateForm(Board board, Member member){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        Board newboard = new Board();
        newboard.setTitle(board.getTitle());
        newboard.setTitle(board.getContent());
        newboard.setModifiedDate(LocalDateTime.now().format(dtf));

    }
    
}
