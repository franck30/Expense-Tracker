package com.franck.carin.expensetracker.controller;


import com.franck.carin.expensetracker.dto.ExpenseDto;
import com.franck.carin.expensetracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
public class ExpenseController {

  private final ExpenseService expenseService;

@PostMapping
  public ResponseEntity<Void> addExpense(@RequestBody ExpenseDto expenseDto) {
    String expenseId = expenseService.addExpense(expenseDto);

    URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(expenseId)
            .toUri();

    return ResponseEntity.created(location)
            .build();
  }

}
