package com.practice.springsecondphrasepractice.controller;

import com.practice.springsecondphrasepractice.Modle.entity.Bud;
import com.practice.springsecondphrasepractice.Annotation.DateTime.DateTimePattern;
import com.practice.springsecondphrasepractice.controller.dto.request.Bud.AfterBeforeBusinessDate;
import com.practice.springsecondphrasepractice.controller.dto.request.Bud.CreateBud;
import com.practice.springsecondphrasepractice.controller.dto.request.Bud.UpDateBudType;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.exception.ParamInvalidException;
import com.practice.springsecondphrasepractice.service.BudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
@Validated
@RestController
@RequestMapping("/api/v1/bud")
public class BudController {
    @Autowired
    BudService budService;
    @GetMapping
    public List<Bud> getBud(@RequestParam (required = false)@DateTimePattern(pattern = "uuuMMdd",message = "起始日期格式輸入有誤") String starDate,@RequestParam (required = false) @DateTimePattern(pattern = "uuuuMMdd",message = "終止日期格式輸入有誤")String endDate,@DateTimePattern(pattern = "uuuuMMdd",message = "日期格式輸入有誤")@RequestParam (required = false)String budymd,@RequestParam(required = false)String year) throws DataNotFoundException, ParseException, ParamInvalidException {
        List<Bud> budList=new ArrayList<>();
        if(starDate != null && endDate!=null){
            budList=budService.getBusinessDay(starDate,endDate);
        } else if (year!=null) {
            budList=budService.getYearBussiness(year);
        }
        else if(budymd!=null){
            AfterBeforeBusinessDate abbd=budService.getAfterBeforeBusinessDate(budymd);
            budList= (List<Bud>) abbd;
        }
        else {
            budList=budService.getBud();
        }

        return budList;

    }
    @GetMapping("/{budymd}")
    public Bud getBud(@PathVariable String budymd) throws DataNotFoundException {
        return budService.getBudByID(budymd);
    }
//    @GetMapping("/u")
//    public List<Bud> getBusinessDay(@RequestParam (required = false)@DateTimePattern(pattern = "uuuMMdd",message = "起始日期格式輸入有誤") String starDate,@RequestParam (required = false) @DateTimePattern(pattern = "uuuuMMdd",message = "終止日期格式輸入有誤" )String endDate) throws DataNotFoundException, ParamInvalidException, ParseException {
//        List<Bud> getBusiness=budService.getBusinessDay(starDate, endDate);
//        return getBusiness;
//    }
//    @GetMapping("/n")
//    public AfterBeforeBusinessDate getAfterBeforeBusinessDate(@DateTimePattern(pattern = "uuuuMMdd",message = "日期格式輸入有誤")@RequestParam (required = false)String budymd) throws ParseException, ParamInvalidException, DataNotFoundException {
//        AfterBeforeBusinessDate getABBD=budService.getAfterBeforeBusinessDate(budymd);
//        return  getABBD;
//    }
//    @GetMapping("/z")
//    public List<Bud> getYearBusiness(@RequestParam(required = false)String year) throws ParseException ,ParamInvalidException,DataNotFoundException{
//        List<Bud> yearBusiness=budService.getYearBussiness(year);
//        return  yearBusiness;
//    }
    @PostMapping
    public StatusResponse createBusiness(@Valid @RequestBody CreateBud createBud) throws ParseException, ParamInvalidException,DataNotFoundException {
        return  budService.createBud(createBud);
    }
    @PutMapping("/{budymd}")
    public  StatusResponse upDateBudType(@DateTimePattern(pattern = "uuuuMMdd",message ="日期格式輸入錯誤" )@PathVariable String budymd,@Valid @RequestBody UpDateBudType upDateBudType) throws ParseException,ParamInvalidException,DataNotFoundException{
        return  budService.updateBudType(budymd,upDateBudType);

    }
}
