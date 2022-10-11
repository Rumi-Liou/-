package com.practice.springsecondphrasepractice.controller;

import com.practice.springsecondphrasepractice.controller.dto.request.Nfa.CreateNfa;
import com.practice.springsecondphrasepractice.controller.dto.request.Nfa.DeleteNfa;
import com.practice.springsecondphrasepractice.controller.dto.request.Nfa.GetNfa;
import com.practice.springsecondphrasepractice.controller.dto.request.Nfa.UpDateNfa;
import com.practice.springsecondphrasepractice.controller.dto.response.StatusResponse;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import com.practice.springsecondphrasepractice.service.NfaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Valid
@RequiredArgsConstructor
@RequestMapping("/api/v1/nfa")
public class NfaContoller {
    private final NfaService nfaService;

    @GetMapping
    public List<GetNfa> getNfa(@RequestParam(required = false, defaultValue = "") String subject,
                               @RequestParam(required = false, defaultValue = "") String startTime,
                               @RequestParam(required = false, defaultValue = "") String endTime) throws DataNotFoundException {
        return nfaService.findByTime(subject, startTime, endTime);
    }

//    @GetMapping("/u")
//    public List<GetNfa> findBytime(@RequestParam String subject, @RequestParam String startTime, @RequestParam String endTime) throws DataNotFoundException {
//        return nfaService.findBytime(subject, startTime, endTime);
//    }

    @PostMapping
    public StatusResponse createNfa(@RequestBody CreateNfa createNfa) {
        return nfaService.createNfa(createNfa);
    }

    @PutMapping("/{uUid}")
    public StatusResponse upDateNfa(@PathVariable String uUid, @RequestBody(required = false) UpDateNfa upDateNfa, @RequestBody DeleteNfa deleteNfa) throws DataNotFoundException {
//        StatusResponse response= new StatusResponse();
//        if (null == upDateNfa) {
//            response =
        return nfaService.upDateNfa(uUid, upDateNfa);
//        }else {
//            response=nfaService.deleteNfa(uUid,deleteNfa);
//        }
//        return  response;
    }


    @PutMapping("d/{uUid}")
    public StatusResponse deleteNfa(@PathVariable String uUid, @RequestBody DeleteNfa deleteNfa) throws DataNotFoundException {
        return nfaService.deleteNfa(uUid, deleteNfa);
    }

}
