package com.library.repository;

import com.library.entity.ContactQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryRepository extends JpaRepository<ContactQuery, Long> {
}
