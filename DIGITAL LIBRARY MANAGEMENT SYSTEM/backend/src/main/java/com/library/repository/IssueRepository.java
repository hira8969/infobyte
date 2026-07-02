package com.library.repository;

import com.library.entity.IssueRecord;
import com.library.entity.IssueStatus;
import com.library.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<IssueRecord, Long> {
    List<IssueRecord> findByUser(User user);

    long countByStatus(IssueStatus status);
}
