package dev.carv.spring.sec.repository;

import dev.carv.spring.sec.model.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {

    @Query("FROM Notice WHERE CURDATE() BETWEEN noticeBeginDate AND noticeEndDate")
    List<Notice> findAllActiveNotices();

}
