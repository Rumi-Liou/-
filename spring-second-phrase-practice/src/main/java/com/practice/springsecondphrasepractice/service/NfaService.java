package com.practice.springsecondphrasepractice.service;

import com.practice.springsecondphrasepractice.Modle.NfaRepository;
import com.practice.springsecondphrasepractice.Modle.entity.Nfa;
import com.practice.springsecondphrasepractice.controller.dto.request.Nfa.CreateNfa;
import com.practice.springsecondphrasepractice.controller.dto.request.Nfa.DeleteNfa;
import com.practice.springsecondphrasepractice.controller.dto.request.Nfa.GetNfa;
import com.practice.springsecondphrasepractice.controller.dto.request.Nfa.UpDateNfa;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class NfaService {
    private final NfaRepository nfaRepository;

    public List<GetNfa> getAll() throws DataNotFoundException {
        List<Nfa> allNfa = nfaRepository.findAll();
        if (allNfa.isEmpty()) {
            throw new DataNotFoundException("資料不存在");
        }
        return allNfa.stream()
                .map(this::toGetNfa)
                .collect(Collectors.toList());
    }

    public List<GetNfa> findByTime(String subject, String startTime, String endTime) throws DataNotFoundException {
        List<Nfa> nfaList = nfaRepository.findAll(timeSpecification(subject, startTime, endTime));
        if (nfaList.isEmpty()) {
            throw new DataNotFoundException("資料不存在");
        }
        return nfaList.stream()
                .filter(nfa -> "Y".equalsIgnoreCase(nfa.getNfaEnable()))
                .map(this::toGetNfa)
                .collect(Collectors.toList());
    }

    private Specification<Nfa> timeSpecification(String subject, String startTime, String endTime) {
        return (nfa, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>(3);
            if (StringUtils.hasText(subject)) {
                predicates.add(cb.like(nfa.get("nfaSubject"), "%" + subject + "%"));
            }
            if (StringUtils.hasText(startTime)) {
                predicates.add(cb.equal(nfa.get("nfaSTime"), startTime));
            }
            if (StringUtils.hasText(endTime)) {
                predicates.add(cb.equal(nfa.get("nfaETime"), endTime));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public StatusResponse createNfa(CreateNfa createNfa) {
        String fx = DateTimeFormatter.ofPattern("yyyyMMddhhmmssSSS").format(now());
        String uUid = fx;
        Nfa nfa = nfaRepository.findByNfaUuid(uUid);
        System.out.println("uUid" + fx);
        if (null == nfa) {
            Nfa newNfa = new Nfa();
            newNfa.setNfaUuid(uUid);
            newNfa.setNfaContent(createNfa.getContent());
            newNfa.setNfaEnable(createNfa.getEnable());
            newNfa.setNfaSubject(createNfa.getSubject());
            newNfa.setNfaSTime(createNfa.getStartDate());
            newNfa.setNfaETime(createNfa.getEndDate());
            newNfa.setNfaUTime(LocalDateTime.now());
            nfaRepository.save(newNfa);
        } else {
            return new StatusResponse("資料不存在");
        }

        return new StatusResponse("新增成功");
    }

    public StatusResponse upDateNfa(String uUid, UpDateNfa upDateNfa) throws DataNotFoundException {
        Nfa nfa = nfaRepository.findByNfaUuid(uUid);
        if (null == nfa) {
            throw new DataNotFoundException("查無資料");
        } else {
            Nfa upDate = new Nfa();
            upDate.setNfaSubject(upDateNfa.getSubject());
            upDate.setNfaContent(upDateNfa.getContent());
            upDate.setNfaEnable(upDateNfa.getEnable());
            upDate.setNfaETime(upDateNfa.getEndDate());
            upDate.setNfaSTime(upDateNfa.getStartDate());
            upDate.setNfaUTime(LocalDateTime.now());
            nfaRepository.save(upDate);
        }

        return new StatusResponse("資料刪除成功");
    }

    public StatusResponse deleteNfa(String uUid, DeleteNfa deleteNfa) throws DataNotFoundException {
        Nfa nfa = nfaRepository.findByNfaUuid(uUid);
        if (null == nfa) {
            throw new DataNotFoundException("查無資料");
        } else {
            nfa.setNfaEnable(deleteNfa.getEnable());
            nfaRepository.save(nfa);
        }
        return new StatusResponse("刪除成功");
    }

    public List<GetNfa> nfaList(List<Nfa> nfaList) {

        return nfaList.stream()
                .map(this::toGetNfa)
                .collect(Collectors.toList());
    }

    private GetNfa toGetNfa(Nfa nfa) {
        GetNfa getNfa = new GetNfa();
        getNfa.setUuid(nfa.getNfaUuid());
        getNfa.setSubject(nfa.getNfaSubject());
        getNfa.setContent(nfa.getNfaContent());
        getNfa.setEnable(nfa.getNfaEnable());
        getNfa.setCreateTime(String.valueOf(nfa.getNfaUTime()));
        return getNfa;
    }

}
