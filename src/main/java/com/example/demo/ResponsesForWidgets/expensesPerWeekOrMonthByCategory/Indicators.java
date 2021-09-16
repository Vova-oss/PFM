package com.example.demo.ResponsesForWidgets.expensesPerWeekOrMonthByCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Indicators {
    private String category;
    private Integer summary;
}
