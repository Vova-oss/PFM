package com.example.demo.ResponsesForWidgets.expensesPerWeekByCategory;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExpensesPerWeekByCategory {
    private List<Indicators> monthlyAverages;
    private List<Indicators> currentIndicators;
}
