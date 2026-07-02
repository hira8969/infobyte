package com.library.controller;

import com.library.dto.ContactQueryRequest;
import com.library.entity.ContactQuery;
import com.library.service.QueryService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QueryController {
    private final QueryService queryService;

    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @PostMapping("/contact")
    public ContactQuery submit(@Valid @RequestBody ContactQueryRequest request) {
        return queryService.submit(request);
    }

    @GetMapping("/admin/queries")
    public List<ContactQuery> queries() {
        return queryService.all();
    }
}
