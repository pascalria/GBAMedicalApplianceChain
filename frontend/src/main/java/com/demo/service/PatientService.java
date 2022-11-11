package com.demo.service;

import com.demo.mapper.PatientMapper;
import com.demo.pojo.Patient;
import com.demo.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PatientService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Patient> selectAll(){

        SqlSession sqlSession = factory.openSession();

        PatientMapper mapper = sqlSession.getMapper(PatientMapper.class);

        List<Patient> patients = mapper.selectAll();

        sqlSession.close();

        return patients;

    }


}
