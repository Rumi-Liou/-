package com.practice.springsecondphrasepractice.Modle.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nfa")
public class Nfa {
    @Id
    @Column(name = "nfa_uuid")
    private String nfaUuid;
    @Column(name = "nfa_subject")
    private String nfaSubject;
    @Column(name="nfa_content")
    private String nfaContent;
    @Column(name = "nfa_enable")
    private String nfaEnable;
    @Column(name = "nfa_s_time")
    private String nfaSTime;
    @Column(name = "nfa_e_time")
    private String nfaETime;
    @Column(name = "nfa_u_time")
    private LocalDateTime nfaUTime;
}
