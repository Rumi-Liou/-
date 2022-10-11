package com.practice.springsecondphrasepractice.service;

import com.practice.springsecondphrasepractice.Modle.BudRepository;
import com.practice.springsecondphrasepractice.Modle.entity.Bud;
import com.practice.springsecondphrasepractice.controller.dto.request.Bud.AfterBeforeBusinessDate;
import com.practice.springsecondphrasepractice.controller.dto.request.Bud.CreateBud;
import com.practice.springsecondphrasepractice.controller.dto.request.Bud.UpDateBudType;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.exception.ParamInvalidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class BudService {
    private final BudRepository budRepository;

    public List<Bud> getBud() throws DataNotFoundException {
        List<Bud> budList = budRepository.findAll();
        if (budList.isEmpty()) {
            throw new DataNotFoundException("資料不存在");
        }
        return budList;
    }

    public Bud getBudByID(String budYmd) throws DataNotFoundException {
        Bud bud = budRepository.findByBudYmd(budYmd);
        if (null == bud) {
            throw new DataNotFoundException("資料不存在");
        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        DateFormat dtf=DateFormat.getDateInstance();
//        if(sdf.setLenient(true))
//
//        }
        return bud;
    }

    public List<Bud> getBusinessDay(String starDate, String endDate) throws ParseException, DataNotFoundException {
        //   List<Bud> getDate=budRepository.findBybudYmdBETWEEN(starDate,endDate);
        List<Bud> getBussinessDate = new ArrayList<>();
        List<Bud> getDate = budRepository.findByBudYmdBetween(starDate, endDate);
        int i = starDate.compareTo(endDate);
        if (i == 1) {
            throw new DataNotFoundException("起始日期不可大於終止日期");
        }
        for (Bud bud : getDate) {
            Bud budcheck = new Bud();
            if (bud.getBudType().equals("Y")) {
                budcheck.setBudYmd(bud.getBudYmd());
                budcheck.setBudType(bud.getBudType());
                budcheck.setBudUTime(bud.getBudUTime());
                getBussinessDate.add(budcheck);
            }
        }
        return getBussinessDate;
    }


    //    public  List<Bud> getYearBusinessDay(String Year){
//        List<Bud> getYear=
//    }
    public AfterBeforeBusinessDate getAfterBeforeBusinessDate(String budymd) throws ParamInvalidException, ParseException, DataNotFoundException {
        Calendar calendar = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        AfterBeforeBusinessDate abbd = new AfterBeforeBusinessDate();
        SimpleDateFormat stz = new SimpleDateFormat("yyyyMMdd");
        Date date = stz.parse(budymd);
        calendar.setTime(date);
        cal.setTime(date);
        int budid = Integer.getInteger(budymd);
//        List<Bud> budList=budRepository.findById(budid);

        int i = 0;
        int y = 0;
        while (i < 1) {
            calendar.add(Calendar.DATE, -1);
            i++;
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                i--;
            }
            while (y < 1) {
                cal.add(Calendar.DATE, 1);
                y++;
                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    y--;
                }
            }
            abbd.setBudYmd(budymd);
            abbd.setPrevYmd(stz.format(calendar.getTime()));
            abbd.setNextYmd(stz.format(cal.getTime()));
        }
        if (null == budymd) {
            throw new DataNotFoundException("資料不存在");
        }
        return abbd;

    }

    public List<Bud> getYearBussiness(String year) throws ParseException, DataNotFoundException {

        //    List<Bud> budList = budRepository.findAll();
        List<Bud> getBussinessDate = new ArrayList<>();
        List<Bud> buds = budRepository.findByBudYmdStartingWith(year);
        if (buds.isEmpty()) {
            throw new DataNotFoundException("查無資料");
        }
        for (Bud bud : buds) {
            Bud yearBud = new Bud();
            // if(date.equals(year)){
            if (bud.getBudType().equals("Y")) {
                yearBud.setBudYmd(bud.getBudYmd());
                yearBud.setBudType(bud.getBudType());
                yearBud.setBudUTime(bud.getBudUTime());
                getBussinessDate.add(yearBud);
            }
        }
        // System.out.println(date);
        // }
        return getBussinessDate;

    }

    public StatusResponse createBud(CreateBud theCreatBud) throws ParseException, ParamInvalidException {
        Bud createBud = new Bud();
        createBud.setBudYmd(theCreatBud.getBudYmd());
        createBud.setBudType(theCreatBud.getBudType());
        createBud.setBudUTime(getDate());
        if (theCreatBud.getBudType().equals("Y") || theCreatBud.getBudType().equals("N")) {
            List<Bud> checkBud = budRepository.findAllById(Collections.singleton(theCreatBud.getBudYmd()));
            if (checkBud.isEmpty()) {
                budRepository.save(createBud);
            } else {
                throw new ParamInvalidException(Arrays.asList("資料已經存在"));
            }
        } else {
            throw new ParamInvalidException(Arrays.asList("只可輸入Y/N"));
        }
        return new StatusResponse("新增成功");
    }

    public StatusResponse updateBudType(String budymd, UpDateBudType upDateBudType) throws DataNotFoundException, ParamInvalidException {
        Bud checkBud = budRepository.findByBudYmd(budymd);
        if (upDateBudType.getBudType().equals("Y") || upDateBudType.getBudType().equals("N")) {
            if (null != checkBud) {
                if (upDateBudType.getBudType().equals(checkBud.getBudType())) {
                    throw new ParamInvalidException(Arrays.asList("資料重複"));
                } else {
                    checkBud.setBudType(upDateBudType.getBudType());
                    budRepository.save(checkBud);
                }
            } else {
                throw new ParamInvalidException(Arrays.asList("資料不存在"));
            }
        } else {
            throw new ParamInvalidException(Arrays.asList("只可輸入Y/N"));
        }

        return new StatusResponse("異動成功");
    }

    public LocalDateTime getDate() {
        LocalDateTime date = LocalDateTime.now();
        return date;
    }

    public List<Bud> setBudList(List<Bud> budList) {
        List<Bud> getBussinessDate = new ArrayList<>();
        for (Bud bud : budList) {
            Bud addbud = new Bud();
            addbud.setBudYmd(bud.getBudYmd());
            addbud.setBudType(bud.getBudType());
            addbud.setBudUTime(bud.getBudUTime());
            getBussinessDate.add(addbud);
        }
        return getBussinessDate;
    }
//    public  Bud setBud(Bud bud){
//        Bud thebud=new Bud();
//        thebud.setBudYmd();
//        thebud.setBudType();
//        thebud.setBudUTime();
//    }
//    public  String getBudType(String budYmd) throws ParseException {
//        String budType;
//        Calendar calendar=Calendar.getInstance();
//        SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
//        Date date=format.parse(budYmd);
//        calendar.setTime(date);
//        if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
//            budType="N";
//        }
//        else{
//            budType="Y";
//        }
//        return budType;
//    }
}
