package com.franck.carin.expensetracker.service;


import com.franck.carin.expensetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseService {

  private final ExpenseRepository expenseRepository;

}
