package com.example.demo.ResponsesForWidgets.expensesByDay;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExpensesByDay {
    private int maxAmount;
    private List<Amount> currentAmount;
    private List<Amount> averageAmount;
}
