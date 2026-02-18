package com.app.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.MemberDAO;
import com.app.dto.member.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void insertMember(MemberDTO dto) {
        // "MemberMapper.insertMember"는 member_mapper.xml의 namespace와 id입니다.
        sqlSession.insert("MemberMapper.insertMember", dto);
    }
}