package com.franck.carin.expensetracker.service;


import com.franck.carin.expensetracker.dto.ExpenseDto;
import com.franck.carin.expensetracker.exception.ExpenseNotFoundException;
import com.franck.carin.expensetracker.model.Expense;
import com.franck.carin.expensetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseService {

  private final ExpenseRepository expenseRepository;


  /**
   * method to add/create an expense
   * @param expenseDto
   * @return
   */
  public String addExpense(ExpenseDto expenseDto) {
    Expense expense = mapFromDto(expenseDto);
    return expenseRepository.insert(expense).getId();
  }

  /**
   * method to retrieve/get an expense by its name
   * @param name
   * @return
   */
  public ExpenseDto getExpense(String name) {

    Expense expense =  expenseRepository.findByName(name)
            .orElseThrow(() -> new ExpenseNotFoundException(String.format("Cannot find Expense by Name - %s", name)));

    return mapToDto(expense);

  }

  /**
   * method to get all expenses
   * @return
   */
  public List<ExpenseDto> getAllExpenses() {

    return expenseRepository.findAll()
            .stream()
            .map(this::mapToDto).collect(Collectors.toList());
  }


  private Expense mapFromDto(ExpenseDto expenseDto) {
    return Expense.builder()
            .expenseName(expenseDto.getExpenseName())
            .expenseCategory(expenseDto.getExpenseCategory())
            .expenseAmount(expenseDto.getExpenseAmount())
            .build();
  }



  private ExpenseDto mapToDto(Expense expense) {
    return ExpenseDto.builder()
            .id(expense.getId())
            .expenseName(expense.getExpenseName())
            .expenseCategory(expense.getExpenseCategory())
            .expenseAmount(expense.getExpenseAmount())
            .build();
  }

}
