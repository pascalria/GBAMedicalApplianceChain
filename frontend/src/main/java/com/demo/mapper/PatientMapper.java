package com.demo.mapper;

import com.demo.pojo.Patient;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PatientMapper {

    @Select("select * from patient")
    List<Patient> selectAll();


}
