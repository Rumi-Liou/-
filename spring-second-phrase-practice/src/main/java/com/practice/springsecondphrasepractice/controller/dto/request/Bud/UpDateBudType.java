package com.practice.springsecondphrasepractice.controller.dto.request.Bud;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpDateBudType {
    @Pattern(regexp = "^[A-Z]+$",message = "只可輸入大寫")
    @NotBlank(message = "budType不得為空")
    @Size(min=1,max = 1,message = "budtype格式輸入錯誤")
    private String budType;
}
