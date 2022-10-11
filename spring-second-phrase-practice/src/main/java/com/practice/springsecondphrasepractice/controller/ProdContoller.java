package com.practice.springsecondphrasepractice.controller;

import com.practice.springsecondphrasepractice.controller.dto.request.prod.CreateProd;
import com.practice.springsecondphrasepractice.controller.dto.request.prod.DeleteProd;
import com.practice.springsecondphrasepractice.controller.dto.request.prod.GetProd;
import com.practice.springsecondphrasepractice.controller.dto.request.prod.UpDateProd;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.exception.ParamInvalidException;
import com.practice.springsecondphrasepractice.service.ProdService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/prod")
@RequiredArgsConstructor
public class ProdContoller {

    private final ProdService prodService;

    @GetMapping
    public List<GetProd> getAllProd(@Size(min = 3,max = 3,message = "只可輸入三位")@Pattern( regexp = "^[A-Z]+$",message = "只可輸入大寫")@RequestParam(required = false) String kind, @Size(min = 3,max = 3,message = "只可輸入三位") @Pattern( regexp = "^[A-Z]+$",message = "只可輸入大寫")@RequestParam(required = false) String prodCcy) throws DataNotFoundException {
        List<GetProd> prods = new ArrayList<>();
        if (null != kind) {
            prods = prodService.getProdEnaleBykind(kind);
        } else if (null != prodCcy) {
            prods = prodService.getProdEnableByCcy(prodCcy);
        } else {
            prods = prodService.getAllProd();
        }
        return prods;
    }

//    @GetMapping("/u")
//    public List<GetProd> getProdEnaleBykind( @RequestParam String kind) throws DataNotFoundException {
//        return prodService.getProdEnaleBykind(kind);
//    }

//    @GetMapping("/c")
//    public List<GetProd> getProdEnableByCcy(@RequestParam String prodCcy) throws DataNotFoundException {
//        return prodService.getProdEnableByCcy(prodCcy);
//    }

    @GetMapping("/{prodId}")
    public GetProd getProdById(@PathVariable String prodId) throws DataNotFoundException, ParamInvalidException {
        GetProd getProd = prodService.getProdById(prodId);
        return getProd;
    }

    @PostMapping
    public StatusResponse createProd(@Valid @RequestBody CreateProd createProd) {
        return prodService.createProd(createProd);
    }

    @PutMapping("/{prodId}")
    public StatusResponse upDateProd(@PathVariable String prodId, @RequestBody UpDateProd upDateProd) throws ParamInvalidException {
        return prodService.upDateProd(prodId, upDateProd);

    }

    @PostMapping("/{prodId}")
    public StatusResponse deleteProd(@PathVariable String prodId, @RequestBody DeleteProd deleteProd) {
        return prodService.deleteProd(prodId, deleteProd);
    }
}
