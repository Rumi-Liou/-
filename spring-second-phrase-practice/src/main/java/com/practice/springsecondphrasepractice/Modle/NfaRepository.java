package com.practice.springsecondphrasepractice.Modle;

import com.practice.springsecondphrasepractice.Modle.entity.Nfa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NfaRepository extends JpaRepository<Nfa, String>, JpaSpecificationExecutor<Nfa> {
    //    List<Nfa> findByNfaSTimeBetween(String starDate, String endDate);
    //  List<Nfa> findByNfaSubjectAndNfaSTimeAndNfaETime(String subject, String starDate, String endDate);
    List<Nfa> findByNfaSubjectStartingWith(String subject);

    List<Nfa> findByNfaSTimeAndNfaETime(String starDate, String endDate);

    List<Nfa> findByNfaSTime(String starDate);

    List<Nfa> findByNfaETime(String endTIme);

    Nfa findByNfaUuid(String nfaId);
}
