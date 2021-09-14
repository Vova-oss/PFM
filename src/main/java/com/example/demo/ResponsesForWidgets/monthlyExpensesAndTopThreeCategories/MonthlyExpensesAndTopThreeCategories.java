package com.example.demo.ResponsesForWidgets.monthlyExpensesAndTopThreeCategories;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MonthlyExpensesAndTopThreeCategories {

    private List<TopThreeCategories> list;
    private int wholeSum;

}
