package com.practice.springsecondphrasepractice.controller.dto.request.Nfa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteNfa {
    @Pattern(regexp = "^[A-Z]+$",message = "日期種類只可輸入大寫")
    @NotBlank(message = "日期種類不可為空")
    @Size(min = 1, max = 1, message = "種類格式輸入錯誤")
    private String enable;
}
