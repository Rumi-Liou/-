package com.practice.springsecondphrasepractice.controller.dto.request.Bud;

import com.practice.springsecondphrasepractice.Annotation.DateTime.DateTimePattern;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBud {
    @NotBlank(message = "日期不可為空")
    @DateTimePattern(pattern = "uuuuMMdd", message = "日期格式錯誤")
//    @Pattern(regexp = "yyyyMMmm",)
    private String budYmd;
    @Pattern(regexp = "^[A-Z]+$",message = "日期種類只可輸入大寫")
    @NotBlank(message = "日期種類不可為空")
    @Size(min = 1, max = 1, message = "種類格式輸入錯誤")
    private String budType;
    //手動輸入budType的原因:當日可能為休假日或颱風假
}
