package edu.neu.medical.hospital.service.impl;

import edu.neu.medical.hospital.bean.SetGroup;
import edu.neu.medical.hospital.bean.SetSub;
import edu.neu.medical.hospital.service.SetManageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@WebAppConfiguration("src/main/webapp/static")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SetManageServiceImplTest {

    @Resource
    SetManageService setManageService;

    @Before
    public void bef(){
        setManageService.setType('1');
        setManageService.setCategory('3');
        setManageService.setBelongId(1);
    }

    @Test
    public void addSetAndSub() {
        SetGroup set=new SetGroup();
        set.setSetCode("aaaa");
        set.setSetName("leile");
        set.setBelongId(1);
        set.setBusinessClassify("1");
        set.setUseScope("3");
        SetSub setSub = new SetSub();
        setSub.setResponseId(2);
        setSub.setEntrust("hao");
        SetSub setSub1 = new SetSub();
        setSub1.setResponseId(4);
        setSub1.setEntrust("hao1");
        List<SetSub> list=new ArrayList<>();
        list.add(setSub);
        list.add(setSub1);
        setManageService.addSetAndSub(set, list);
    }
}