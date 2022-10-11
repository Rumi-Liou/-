package com.practice.springsecondphrasepractice.service;

import com.practice.springsecondphrasepractice.Modle.ProdRepository;
import com.practice.springsecondphrasepractice.Modle.entity.Prod;
import com.practice.springsecondphrasepractice.controller.dto.request.prod.CreateProd;
import com.practice.springsecondphrasepractice.controller.dto.request.prod.DeleteProd;
import com.practice.springsecondphrasepractice.controller.dto.request.prod.UpDateProd;
import com.practice.springsecondphrasepractice.controller.dto.request.prod.GetProd;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.exception.ParamInvalidException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProdService {

    private final ProdRepository prodRepository;

    public List<GetProd> getAllProd() throws DataNotFoundException {
        List<Prod> allProd = prodRepository.findAll();
        List<GetProd> prods = new ArrayList<>();
        if (allProd.isEmpty()) {
            throw new DataNotFoundException("資料不存在");
        } else {
            prods = setProd(allProd);
        }
        return prods;
    }

    public List<GetProd> getProdEnaleBykind(String prodKind) throws DataNotFoundException {
        List<Prod> prods = prodRepository.findByProdKind(prodKind);
        List<GetProd> prodList = new ArrayList<>();
        if (prods.isEmpty()) {
            throw new DataNotFoundException("資料不存在");
        } else {
            getNEnable(prods);
            prodList = getEnable(prods);
        }
        return prodList;
    }

    public List<GetProd> getProdEnableByCcy(String prodCcy) throws DataNotFoundException {
        List<Prod> prods = prodRepository.findByProdCcy(prodCcy);
        List<GetProd> getProdList = new ArrayList<>();
        if (prods.isEmpty()) {
            throw new DataNotFoundException("資料不存在");
        } else {
            getProdList = getEnable(prods);
        }
        return getProdList;
    }

    public GetProd getProdById(String prodId) throws DataNotFoundException, ParamInvalidException {
        Prod prod = prodRepository.findByProdId(prodId);
        GetProd getProd = new GetProd();
        if (null == prod) {
            throw new ParamInvalidException(Arrays.asList("資料不存在"));
        } else {
            getProd=getTheProd(prod);
        }
        return getProd;
    }

    public StatusResponse createProd(CreateProd createProd) {
        String prod=createProd.getProdKind();
        String id=createProd.getProdCcy();
        String prodId=prod+"_"+id;
       Prod checkProd=prodRepository.findByProdId(prodId);
        if(null==checkProd){
            Prod prod1=new Prod();
            prod1.setProdId(prodId);
            prod1.setProdKind(createProd.getProdKind());
            prod1.setProdName(createProd.getProdName());
            prod1.setProdEname(createProd.getProdEname());
            prod1.setProdCcy(createProd.getProdCcy());
            prod1.setProdEnale(createProd.getProdEnale());
            prod1.setProd_i_time(LocalDateTime.now());
            prod1.setProd_u_time(LocalDateTime.now());
            prodRepository.save(prod1);
        }
        else {
            return new StatusResponse("資料已存在");
        }
        return new StatusResponse("新增成功");
    }

    public StatusResponse upDateProd(String prodId, UpDateProd upDateProd) throws ParamInvalidException {
        Prod prods=prodRepository.findByProdId(prodId);
        if(null==prods){
            throw  new ParamInvalidException(Arrays.asList("資料不存在"));
        }
        else {
            prods.setProdName(upDateProd.getProdName());
            prods.setProdEname(upDateProd.getProdEname());
            prods.setProdEnale(upDateProd.getProdEnable());
            prods.setProd_u_time(LocalDateTime.now());
            prodRepository.save(prods);
        }
            return new StatusResponse("資料更改成功");
    }

    public StatusResponse deleteProd(String prodId, DeleteProd deleteProd)  {
        Prod prod=prodRepository.findByProdId(prodId);
        if(null==prod){
            return new StatusResponse("資料不存在");
        }else
        {
            if(deleteProd.getProdEnable().equals("Y")||deleteProd.getProdEnable().equals("N")){
          prod.setProdEnale(deleteProd.getProdEnable());
            }
            else {
                return  new StatusResponse("只可輸入Y/N");
            }
        }
        return new StatusResponse("刪除成功");
    }

    public List<GetProd> setProd(List<Prod> prodList) {
        List<GetProd> getProd = new ArrayList();
        for (Prod prod : prodList) {
            GetProd theProd = new GetProd();
            theProd.setProdId(prod.getProdId());
            theProd.setProdKind(prod.getProdKind());
            theProd.setProdName(prod.getProdName());
            theProd.setProdEname(prod.getProdEname());
            theProd.setProdCcy(prod.getProdCcy());
            theProd.setProdEnale(prod.getProdEnale());
            getProd.add(theProd);
        }
        return getProd;
    }

    public GetProd getTheProd(Prod prod) {
        GetProd getProd = new GetProd();
        getProd.setProdId(prod.getProdId());
        getProd.setProdKind(prod.getProdKind());
        getProd.setProdName(prod.getProdName());
        getProd.setProdEname(prod.getProdEname());
        getProd.setProdCcy(prod.getProdCcy());
        getProd.setProdEnale(prod.getProdEnale());
        return getProd;
    }

    public List<GetProd> getEnable(List<Prod> prodList) {
        List<GetProd> getProds = new ArrayList<>();
        for (Prod prod : prodList) {
            if (prod.getProdEnale().equals("Y")) {
                GetProd getProd = new GetProd();
                getProd.setProdId(prod.getProdId());
                getProd.setProdKind(prod.getProdKind());
                getProd.setProdName(prod.getProdName());
                getProd.setProdEname(prod.getProdEname());
                getProd.setProdCcy(prod.getProdCcy());
                getProd.setProdEnale(prod.getProdEnale());
                getProds.add(getProd);
            }
        }
        return getProds;
    }

    public void getNEnable(List<Prod> prodList) throws DataNotFoundException {
        for (Prod prod : prodList) {
            if (prod.getProdEnale().equals("N")) {
                throw new DataNotFoundException("暫無已啟用資料");
            }
        }
    }

}

