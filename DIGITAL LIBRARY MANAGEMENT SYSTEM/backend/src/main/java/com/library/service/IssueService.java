package com.library.service;

import com.library.entity.Book;
import com.library.entity.Fine;
import com.library.entity.IssueRecord;
import com.library.entity.IssueStatus;
import com.library.entity.User;
import com.library.exception.BadRequestException;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.BookRepository;
import com.library.repository.FineRepository;
import com.library.repository.IssueRepository;
import com.library.util.FineCalculator;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IssueService {
    private final IssueRepository issueRepository;
    private final BookRepository bookRepository;
    private final FineRepository fineRepository;
    private final CurrentUserService currentUserService;
    private final FineCalculator fineCalculator;
    private final long dueDays;

    public IssueService(IssueRepository issueRepository, BookRepository bookRepository, FineRepository fineRepository,
                        CurrentUserService currentUserService, FineCalculator fineCalculator,
                        @Value("${app.library.due-days}") long dueDays) {
        this.issueRepository = issueRepository;
        this.bookRepository = bookRepository;
        this.fineRepository = fineRepository;
        this.currentUserService = currentUserService;
        this.fineCalculator = fineCalculator;
        this.dueDays = dueDays;
    }

    @Transactional
    public IssueRecord issue(Long bookId) {
        User user = currentUserService.currentUser();
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        if (book.getAvailableQuantity() <= 0) {
            throw new BadRequestException("Book is unavailable. Please reserve it.");
        }
        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        IssueRecord record = new IssueRecord();
        record.setUser(user);
        record.setBook(book);
        record.setIssueDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(dueDays));
        return issueRepository.save(record);
    }

    @Transactional
    public IssueRecord returnBook(Long issueId) {
        IssueRecord record = issueRepository.findById(issueId)
                .orElseThrow(() -> new ResourceNotFoundException("Issue record not found"));
        if (record.getStatus() == IssueStatus.RETURNED) {
            throw new BadRequestException("Book already returned");
        }
        record.setStatus(IssueStatus.RETURNED);
        record.setReturnDate(LocalDate.now());
        Book book = record.getBook();
        book.setAvailableQuantity(book.getAvailableQuantity() + 1);

        long overdueDays = fineCalculator.overdueDays(record.getDueDate(), record.getReturnDate());
        if (overdueDays > 0) {
            Fine fine = new Fine();
            fine.setIssueRecord(record);
            fine.setOverdueDays(overdueDays);
            fine.setAmount(fineCalculator.amount(overdueDays));
            fineRepository.save(fine);
            record.setFine(fine);
        }
        return issueRepository.save(record);
    }

    public List<IssueRecord> myIssues() {
        return issueRepository.findByUser(currentUserService.currentUser());
    }

    public List<IssueRecord> allIssues() {
        return issueRepository.findAll();
    }
}
