package MyTennis.project.service;

import MyTennis.project.domain.Board;
import MyTennis.project.domain.Board;
import MyTennis.project.domain.Member;
import MyTennis.project.dto.BoardAddForm;
import MyTennis.project.dto.BoardList;
import MyTennis.project.dto.BoardUpdateForm;
import MyTennis.project.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board findOne(Long id) {
        return boardRepository.getOne(id);
    }

    @Transactional
    public Long BoardAdd(BoardAddForm form, Member member){
        Board board = Board.createBoard(form, member);
        boardRepository.save(board);
        return board.getId();
    }


    @Transactional
    public void BoardUpdate(BoardUpdateForm form, Member member){
        Board board = Board.update(form, member);
    }

    public List<Board> getBoardList() {
//        List<Board> boards = boardRepository.findAll();
//        List<BoardList> boardList = new ArrayList<>();

        return boardRepository.findAll();
    }

    public void BoardDelete(Long id){
        boardRepository.deleteById(id);
    }



}
