package com.library.repository;

import com.library.entity.Fine;
import com.library.entity.FineStatus;
import com.library.entity.User;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FineRepository extends JpaRepository<Fine, Long> {
    List<Fine> findByIssueRecordUser(User user);

    @Query("select coalesce(sum(f.amount), 0) from Fine f where f.status = :status")
    BigDecimal sumAmountByStatus(@Param("status") FineStatus status);
}
