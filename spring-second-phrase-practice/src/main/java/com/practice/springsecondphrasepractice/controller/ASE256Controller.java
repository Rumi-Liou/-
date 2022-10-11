package com.practice.springsecondphrasepractice.controller;

import com.practice.springsecondphrasepractice.controller.dto.request.ASE256Request;
import com.practice.springsecondphrasepractice.controller.dto.response.ASE256Response;
import com.practice.springsecondphrasepractice.service.ASE256Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class ASE256Controller {
    private final ASE256Service ase256Service;

    @PostMapping("/encode/aes/ecb/{sKey}")
    public ASE256Response getECBEncrypt(@PathVariable String sKey, @RequestBody ASE256Request request) throws Exception {
        return  ase256Service.ECBEncrypt(sKey, request);
    }
    @PostMapping("/decode/aes/ecb/{sKey}")
    public ASE256Response getECBDncrypt(@PathVariable String sKey, @RequestBody ASE256Request request) throws Exception{
        return  ase256Service.ECBDecrypt(sKey,request);
    }
    @PostMapping("/encode/aes/cbc/{sKey}")
    public  ASE256Response getCBCEndcrypt(@PathVariable String sKey,@RequestBody ASE256Request request) throws Exception {
        return ase256Service.CBCEncrypt(sKey, request);
    }
    @PostMapping("/decode/aes/cbc/{sKey}")
    public  ASE256Response getCbcDecrypt(@PathVariable String sKey,@RequestBody ASE256Request request) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return  ase256Service.getCBCDecrypt(sKey,request);
    }
}
