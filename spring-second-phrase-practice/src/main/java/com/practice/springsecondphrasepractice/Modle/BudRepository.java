package com.practice.springsecondphrasepractice.Modle;

import com.practice.springsecondphrasepractice.Modle.entity.Bud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudRepository extends JpaRepository <Bud,String>{
    Bud findByBudYmd(String budYmd);

   List<Bud> findByBudYmdBetween(String starDate, String endDate);
    List<Bud> findByBudYmdStartingWith(String year);
    List<Bud> findByBudYmdLike(String year);
}
