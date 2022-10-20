package MyTennis.project.domain;

import MyTennis.project.dto.BoardAddForm;
import MyTennis.project.dto.BoardUpdateForm;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long Id;

    @NotNull
    @Column(length=31)
    private String title;

    @NotNull
    @Column(length = 145)
    private String content;

    @Column(length = 11)
    @NotNull
    private String writer; // Member nickname랑 연결!

    @Column(columnDefinition = "integer default 0")
    private Integer view;

    @CreatedDate
    @Column(length = 40)
    private String createdDate;

    @CreatedDate
    @Column(length = 40)
    private String modifiedDate;

    // 외래키!
    @ManyToOne(fetch=LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Board createBoard(BoardAddForm boardAddForm, Member member){
        Board board = new Board();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

//        board.title= boardAddForm.getTitle();
//        board.content=boardAddForm.getContent();
//        board.writer= member.getNickname();
//        board.view=0;
//        board.createdDate= LocalDateTime.now().format(dtf);
//        board.modifiedDate=LocalDateTime.now().format(dtf);

        board.setTitle(boardAddForm.getTitle());
        board.setContent(boardAddForm.getContent());
        board.setWriter(member.getNickname());
        board.setView(0);
        board.setCreatedDate(LocalDateTime.now().format(dtf));
        board.setModifiedDate(LocalDateTime.now().format(dtf));

        board.setMember(member);

        return board;

    }


    // 게시글 수정!
    public static Board update(BoardUpdateForm form, Member member){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

        Board newboard = new Board();
        newboard.setId(form.getId());
        newboard.setTitle(form.getTitle());
        newboard.setContent(form.getContent());
        newboard.setWriter(member.getNickname());
        newboard.setMember(member); // 왜 Member가 필요하지?
        newboard.setModifiedDate(LocalDateTime.now().format(dtf));

        return newboard;
    }

    @Builder
    // 조회수 증가
    public void increaseView() {
        this.view++;
    }

    @Builder
    // 게시글 삭제
    public void delete(){

    }

}
