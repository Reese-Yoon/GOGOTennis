package MyTennis.project.repository;

import MyTennis.project.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryInterface{

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Board findOne(Long boardId) {

        return em.find(Board.class, boardId);
    }

    @Override
    public void updateView(Long boardId) {
        Board board = findOne(boardId);
        // 미완성
    }
}
