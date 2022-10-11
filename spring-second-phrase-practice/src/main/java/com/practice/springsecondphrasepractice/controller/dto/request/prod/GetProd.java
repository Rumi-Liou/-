package com.practice.springsecondphrasepractice.controller.dto.request.prod;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetProd {
    private String prodId;
    private String prodKind;
    private String prodName;
    private String prodEname;
    private String prodCcy;
    private String prodEnale;
}
