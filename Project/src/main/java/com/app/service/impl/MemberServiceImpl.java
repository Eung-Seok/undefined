package com.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.dao.MemberDAO;
import com.app.dto.member.MemberDTO;
import com.app.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDao; 

    @Override
    public void registerMember(MemberDTO dto) {
       
        memberDao.insertMember(dto);
    }
}