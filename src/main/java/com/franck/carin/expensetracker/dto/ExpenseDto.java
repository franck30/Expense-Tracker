package com.franck.carin.expensetracker.dto;


import com.franck.carin.expensetracker.model.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseDto {
  private String id;
  private String expenseName;
  private ExpenseCategory expenseCategory;
  private BigDecimal expenseAmount;
}
