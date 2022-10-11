package com.practice.springsecondphrasepractice.Modle.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="bud")
public class Bud {
    @Id
    @Column(name = "bud_ymd")
     private String budYmd;
    @Column(name = "bud_type")
     private String budType;
    @Column(name = "bud_u_time")
     private LocalDateTime budUTime;

}
