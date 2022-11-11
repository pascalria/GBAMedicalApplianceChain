package com.demo.service;

import com.demo.mapper.CaseMapper;
import com.demo.mapper.PatientMapper;
import com.demo.pojo.Case;
import com.demo.pojo.Patient;
import com.demo.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CaseService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public void add(Case c){

        SqlSession sqlSession = factory.openSession();

        CaseMapper mapper = sqlSession.getMapper(CaseMapper.class);

        mapper.add(c);
        sqlSession.commit();

        sqlSession.close();

    }


}
