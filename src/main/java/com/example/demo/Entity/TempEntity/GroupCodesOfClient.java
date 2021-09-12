package com.example.demo.Entity.TempEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupCodesOfClient {

    private Long client_id;
    private Integer countOfCode;
    private String groupCode;
    private Integer summary;

}
