package com.binary.CarShow.controllers;

import com.binary.CarShow.entities.Member;
import com.binary.CarShow.repositatories.MemberRepository;
import com.binary.CarShow.services.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {
    @Autowired
    private MemberServiceImpl memberService;
    @PostMapping("/register")
    public ResponseEntity<Object> registerMember(@RequestBody Member member){

        return new ResponseEntity<>(memberService.createMember(member), HttpStatus.CREATED);
    }
}
