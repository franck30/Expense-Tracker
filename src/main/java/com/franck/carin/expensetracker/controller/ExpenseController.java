package com.franck.carin.expensetracker.controller;


import com.franck.carin.expensetracker.dto.ExpenseDto;
import com.franck.carin.expensetracker.model.Expense;
import com.franck.carin.expensetracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

  /**
   * get an expense by its name
   * @param name
   * @return
   */
  @GetMapping("/{name}")
  @ResponseStatus(HttpStatus.OK)
  public ExpenseDto getExpenseByName(@PathVariable String name) {
    return expenseService.getExpense(name);
  }


  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ExpenseDto> getAllExpenses() {
    return expenseService.getAllExpenses();
  }


  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public void updateExpense(@RequestBody ExpenseDto expenseDto) {
    expenseService.updateExpense(expenseDto);
  }


  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteExpense(@PathVariable String id) {
    expenseService.deleteExpense(id);
  }


}
