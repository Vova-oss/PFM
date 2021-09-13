package com.example.demo.ResponsesForWidgets.expensesPerWeekOrMonthByCategory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Indicators {
    private String category;
    private Integer summary;
}
