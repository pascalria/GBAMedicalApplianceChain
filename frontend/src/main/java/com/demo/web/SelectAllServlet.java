package com.demo.web;

import com.alibaba.fastjson.JSON;
import com.demo.chain.client.HelloWorldClient;
import com.demo.chain.contract.HelloWorld;
import com.demo.pojo.Patient;
import com.demo.service.PatientService;
import org.fisco.bcos.sdk.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {

    private PatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        List<Patient> patients = patientService.selectAll();
        System.out.println(patients);

        HelloWorldClient helloWorldClient = new HelloWorldClient();
        helloWorldClient.initialize();
        helloWorldClient.registerP();
//        try {
//            helloWorldClient.helloSet("aaaaa");
//            helloWorldClient.helloGet();
//            helloWorldClient.helloSet("bbbbb");
//            helloWorldClient.helloGet();
//        } catch (ContractException e) {
//            throw new RuntimeException(e);
//        }



//        String jsonString = JSON.toJSONString(patients);
//        System.out.println(jsonString);

//        resp.setContentType("text/json;charset=utf-8");
//        resp.getWriter().write(jsonString);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        this.doGet(req,resp);
    }
}
