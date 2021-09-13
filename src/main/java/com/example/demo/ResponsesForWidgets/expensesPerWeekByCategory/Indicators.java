package com.example.demo.ResponsesForWidgets.expensesPerWeekByCategory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Indicators {
    private String category;
    private Integer summary;
}
