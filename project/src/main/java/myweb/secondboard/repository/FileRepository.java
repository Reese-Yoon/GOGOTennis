package myweb.secondboard.repository;

import myweb.secondboard.domain.File;
import myweb.secondboard.domain.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository  extends JpaRepository<File, Long> {
}
