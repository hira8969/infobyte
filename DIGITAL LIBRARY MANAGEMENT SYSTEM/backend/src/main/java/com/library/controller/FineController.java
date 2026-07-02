package com.library.controller;

import com.library.entity.Fine;
import com.library.service.FineService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fines")
public class FineController {
    private final FineService fineService;

    public FineController(FineService fineService) {
        this.fineService = fineService;
    }

    @GetMapping("/me")
    public List<Fine> myFines() {
        return fineService.myFines();
    }

    @GetMapping
    public List<Fine> allFines() {
        return fineService.allFines();
    }

    @PatchMapping("/{id}/paid")
    public Fine markPaid(@PathVariable Long id) {
        return fineService.markPaid(id);
    }
}
