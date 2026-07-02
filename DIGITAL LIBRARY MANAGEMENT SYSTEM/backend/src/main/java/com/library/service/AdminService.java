package com.library.service;

import com.library.dto.DashboardStats;
import com.library.entity.FineStatus;
import com.library.entity.IssueStatus;
import com.library.entity.User;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.BookRepository;
import com.library.repository.FineRepository;
import com.library.repository.IssueRepository;
import com.library.repository.ReservationRepository;
import com.library.repository.UserRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final IssueRepository issueRepository;
    private final FineRepository fineRepository;
    private final ReservationRepository reservationRepository;

    public AdminService(BookRepository bookRepository, UserRepository userRepository, IssueRepository issueRepository,
                        FineRepository fineRepository, ReservationRepository reservationRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.issueRepository = issueRepository;
        this.fineRepository = fineRepository;
        this.reservationRepository = reservationRepository;
    }

    public DashboardStats stats() {
        BigDecimal pendingFines = fineRepository.sumAmountByStatus(FineStatus.PENDING);
        return new DashboardStats(
                bookRepository.count(),
                userRepository.count(),
                issueRepository.countByStatus(IssueStatus.ISSUED),
                issueRepository.countByStatus(IssueStatus.ISSUED),
                pendingFines,
                reservationRepository.count()
        );
    }

    public List<User> users() {
        return userRepository.findAll();
    }

    @Transactional
    public User setActive(Long id, boolean active) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setActive(active);
        return userRepository.save(user);
    }
}
