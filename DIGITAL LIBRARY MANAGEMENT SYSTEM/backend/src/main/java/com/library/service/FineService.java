package com.library.service;

import com.library.entity.Fine;
import com.library.entity.FineStatus;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.FineRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FineService {
    private final FineRepository fineRepository;
    private final CurrentUserService currentUserService;

    public FineService(FineRepository fineRepository, CurrentUserService currentUserService) {
        this.fineRepository = fineRepository;
        this.currentUserService = currentUserService;
    }

    public List<Fine> myFines() {
        return fineRepository.findByIssueRecordUser(currentUserService.currentUser());
    }

    public List<Fine> allFines() {
        return fineRepository.findAll();
    }

    @Transactional
    public Fine markPaid(Long id) {
        Fine fine = fineRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fine not found"));
        fine.setStatus(FineStatus.PAID);
        fine.setPaidDate(LocalDate.now());
        return fineRepository.save(fine);
    }
}
