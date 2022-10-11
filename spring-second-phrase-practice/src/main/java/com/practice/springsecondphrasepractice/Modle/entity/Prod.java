package com.practice.springsecondphrasepractice.Modle.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="prod")
public class Prod {
    @Id
    @Column(name = "prod_id")
    private String prodId;
    @Column(name = "prod_kind")
    private String prodKind;
    @Column(name = "prod_name")
    private String prodName;
    @Column(name = "prod_ename")
    private String prodEname;
    @Column(name = "prod_ccy")
    private String prodCcy;
    @Column(name = "prod_enable")
    private String prodEnale;
    @Column(name = "prod_i_time")
    private LocalDateTime prod_i_time;
    @Column(name = "prod_u_time")
    private LocalDateTime prod_u_time;
}
