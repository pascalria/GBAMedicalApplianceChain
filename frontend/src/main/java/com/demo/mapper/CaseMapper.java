package com.demo.mapper;

import com.demo.pojo.Case;
import com.demo.pojo.Patient;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CaseMapper {

    @Insert("insert into tb_case values(null,#{patientId},#{medicalNeed},#{createDate})")
    @Options(useGeneratedKeys = true,keyProperty = "caseId")
    void add(Case c);

    @Select("select * from patient")
    List<Patient> selectAll();

}
