package com.practice.springsecondphrasepractice.controller.dto.request.prod;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpDateProd {
    @NotBlank(message = "商品名稱不可為空")
    private String prodName;
    @NotBlank(message = "商品英文名稱不可輸入空")
    private String prodEname;
    @Pattern(regexp = "^[YN]+$", message = "只可輸入大寫")
    @Size(max = 1, min = 1, message = "prodEnable格式錯誤")
    private String prodEnable;
}
