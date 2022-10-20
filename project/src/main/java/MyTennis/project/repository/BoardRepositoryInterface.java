package MyTennis.project.repository;

import MyTennis.project.domain.Board;

public interface BoardRepositoryInterface {

    Board findOne(Long boardId);

    void updateView(Long boardId);

}
