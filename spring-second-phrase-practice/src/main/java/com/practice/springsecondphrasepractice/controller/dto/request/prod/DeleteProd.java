package com.practice.springsecondphrasepractice.controller.dto.request.prod;

import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteProd {
    @Size(max=1,min=1,message = "格式錯誤")
    @Pattern(regexp = "^[A-Z]+$",message = "可輸入大寫" )
    private String prodEnable;
}
