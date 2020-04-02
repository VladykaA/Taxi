package com.vladyka.taxi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {

    private String modelName;
    private String addressTo;
    private String addressFrom;
    private long userId;

}
