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
public class CreateProd {
    @NotBlank(message = "' 商品類別不可為空")
    @Size(max = 3, min = 3, message = "商品類別輸入格式錯誤")
    @Pattern(regexp = "^[A-Z]+$", message = "只可輸入大寫")
    private String prodKind;
    @NotBlank(message = "商品名稱不可為空")
    private String prodName;
    @NotBlank(message = "商品英文名稱不可為空")
    private String prodEname;
    @Pattern(regexp = "^[A-Z]+$", message = "只可輸入大寫")
    @Size(max = 3, min = 3, message = "幣別代碼輸入格式錯誤")
    @NotBlank(message = "幣別代碼不可為空")
    private String prodCcy;
    @NotBlank(message = "請確認是否要啟用")
    @Pattern(regexp = "^[A-Z]+$", message = "只可輸入大寫")
    @Size(max = 1, min = 1, message = "prodEnable格式錯誤")
    private String prodEnale;
}
