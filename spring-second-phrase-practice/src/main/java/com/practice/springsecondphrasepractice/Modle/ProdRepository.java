package com.practice.springsecondphrasepractice.Modle;

import com.practice.springsecondphrasepractice.Modle.entity.Prod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdRepository extends JpaRepository<Prod,String> {
    Prod findByProdId(String prodId);
    List<Prod> findByProdKind(String proKind);
    List<Prod> findByProdCcy(String proCcy);

}
