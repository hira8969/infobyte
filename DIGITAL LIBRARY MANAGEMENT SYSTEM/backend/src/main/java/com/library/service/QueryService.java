package com.library.service;

import com.library.dto.ContactQueryRequest;
import com.library.entity.ContactQuery;
import com.library.repository.QueryRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class QueryService {
    private final QueryRepository queryRepository;

    public QueryService(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    public ContactQuery submit(ContactQueryRequest request) {
        ContactQuery query = new ContactQuery();
        query.setName(request.name());
        query.setEmail(request.email());
        query.setSubject(request.subject());
        query.setMessage(request.message());
        return queryRepository.save(query);
    }

    public List<ContactQuery> all() {
        return queryRepository.findAll();
    }
}
