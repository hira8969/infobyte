package com.library.controller;

import com.library.dto.IssueRequest;
import com.library.entity.IssueRecord;
import com.library.service.IssueService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issues")
public class IssueController {
    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping
    public IssueRecord issue(@Valid @RequestBody IssueRequest request) {
        return issueService.issue(request.bookId());
    }

    @PutMapping("/return/{issueId}")
    public IssueRecord returnBook(@PathVariable Long issueId) {
        return issueService.returnBook(issueId);
    }

    @GetMapping("/me")
    public List<IssueRecord> myIssues() {
        return issueService.myIssues();
    }
}
