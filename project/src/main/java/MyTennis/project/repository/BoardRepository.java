package MyTennis.project.repository;


import MyTennis.project.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BoardRepository extends JpaRepository<Board, Long> {


}
