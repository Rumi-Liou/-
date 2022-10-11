package com.practice.springsecondphrasepractice.controller.dto.request.Bud;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AfterBeforeBusinessDate {
    String budYmd;
    String prevYmd;
    String NextYmd;
}
