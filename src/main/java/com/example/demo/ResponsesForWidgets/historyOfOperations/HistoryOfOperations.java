package com.example.demo.ResponsesForWidgets.historyOfOperations;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HistoryOfOperations {
    private String date;
    private int sum;
    private String currency;
    private String  info;
}
