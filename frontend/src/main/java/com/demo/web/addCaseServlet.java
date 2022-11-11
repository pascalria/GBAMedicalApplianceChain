package com.demo.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.chain.client.HelloWorldClient;
import com.demo.chain.client.StroeHashClient;
import com.demo.chain.contract.StoreHash;
import com.demo.pojo.Case;
import com.demo.pojo.Patient;
import com.demo.service.CaseService;
import com.demo.service.PatientService;
import com.demo.util.BuildKey;
import com.demo.util.EncryptDSAUtil;
import com.demo.util.EncryptSha256Util;
import lombok.SneakyThrows;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi;
import org.bouncycastle.jcajce.provider.digest.SHA256;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/addCaseServlet")
public class addCaseServlet extends HttpServlet {

    private CaseService caseService = new CaseService();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //读取页面回传数据
        BufferedReader br = req.getReader();
        String params = br.readLine();
        //创建病例对象（缺少创建日期
        Case c = JSON.parseObject(params,Case.class);
        JSONObject arr = JSONObject.parseObject(params);
        //获取当前日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String createDate = simpleDateFormat.format(date);
        c.setCreateDate(createDate);
        //调用Service将病例添加到本地数据库
        caseService.add(c);

        //获取并加载私匙
        String privateKeyStr = arr.getString("privateKey");
        PrivateKey privateKey = BuildKey.getPrivateKey(privateKeyStr);
        //对病例进行sha256（使用Case.toString()作为病例
        String sha256Str = EncryptSha256Util.getSha256Str(c.toString());
        //使用私钥进行签名
        byte[] signHash = EncryptDSAUtil.DSASign(privateKey,sha256Str);

        //调用智能合约
        StroeHashClient stroeHashClient = new StroeHashClient();
        stroeHashClient.initialize();
        //上链
        int caseId = c.getCaseId();
        stroeHashClient.StoreHashIn(caseId,signHash);


        resp.getWriter().write("success");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
