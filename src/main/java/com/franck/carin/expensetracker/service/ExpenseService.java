package com.franck.carin.expensetracker.service;


import com.franck.carin.expensetracker.dto.ExpenseDto;
import com.franck.carin.expensetracker.model.Expense;
import com.franck.carin.expensetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseService {

  private final ExpenseRepository expenseRepository;

  public String addExpense(ExpenseDto expenseDto) {
    Expense expense = mapFromDto(expenseDto);
    return expenseRepository.insert(expense).getId();
  }

  private Expense mapFromDto(ExpenseDto expenseDto) {
    return Expense.builder()
            .expenseName(expenseDto.getExpenseName())
            .expenseCategory(expenseDto.getExpenseCategory())
            .expenseAmount(expenseDto.getExpenseAmount())
            .build();
  }

}
