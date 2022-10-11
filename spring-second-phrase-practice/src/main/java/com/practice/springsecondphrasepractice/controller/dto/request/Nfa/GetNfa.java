package com.practice.springsecondphrasepractice.controller.dto.request.Nfa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetNfa {
    private String uuid;
    private String subject;
    private String content;
    private String enable;
    private String createTime;
}
