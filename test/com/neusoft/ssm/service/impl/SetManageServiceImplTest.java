//package edu.neu.medical.hospital.service.impl;
//
//import SetGroup;
//import SetSub;
//import SetManageService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
//@WebAppConfiguration("src/main/webapp/static")
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
//public class SetManageServiceImplTest {
//
//    @Resource
//    SetManageService setManageService;
//
//    @Before
//    public void bef(){
//        setManageService.setType('1');
//        setManageService.setCategory('3');
//        setManageService.setBelongId(1);
//    }
//
//    @Test
//    public void addSetAndSub() {
//        SetGroup set=new SetGroup();
//        set.setSetCode("aaaa");
//        set.setSetName("leile");
//        set.setBelongId(1);
//        set.setBusinessClassify("1");
//        set.setUseScope("3");
//        SetSub setSub = new SetSub();
//        setSub.setResponseId(2);
//        setSub.setEntrust("hao");
//        SetSub setSub1 = new SetSub();
//        setSub1.setResponseId(4);
//        setSub1.setEntrust("hao1");
//        List<SetSub> list=new ArrayList<>();
//        list.add(setSub);
//        list.add(setSub1);
//        setManageService.addSetAndSub(set, list);
//    }
//
//    @Test
//    public void updateMedrecTemplate() {
//        SetGroup set=new SetGroup();
//        set.setId(3);
//        set.setSetCode("aaaa");
//        set.setSetName("leile333");
//        set.setBelongId(1);
//        set.setBusinessClassify("1");
//        set.setUseScope("3");
//        SetSub setSub = new SetSub();
//        setSub.setSetId(3);
//        setSub.setResponseId(4);
//        setSub.setEntrust("hao13");
//        SetSub setSub1 = new SetSub();
//        setSub1.setSetId(3);
//        setSub1.setResponseId(5);
//        setSub1.setEntrust("hao1dd");
//        SetSub setSub2 = new SetSub();
//        setSub2.setSetId(3);
//        setSub2.setResponseId(6);
//        setSub2.setEntrust("33aas");
//        List<SetSub> list=new ArrayList<>();
//        list.add(setSub);
//        list.add(setSub1);
//        list.add(setSub2);
//        setManageService.updateSetGroup(set, list);
//    }
//
//    @Test
//    public void cancelSet (){
//        SetGroup set=new SetGroup();
//        set.setId(3);
////        setManageService.cancelSetGroup(set);
//    }
//
//    @Test
//    public void searchSetGroupList() {
//        List<SetGroup> list = setManageService.searchSetGroupList("a");
//        return;
//    }
//
//    @Test
//    public void getSubInfoList() {
//        setManageService.setType('4');
//        SetSub setSub = new SetSub();
//        setSub.setSetId(3);
//        setSub.setResponseId(4);
//        setSub.setEntrust("hao13");
//        SetSub setSub1 = new SetSub();
//        setSub1.setSetId(3);
//        setSub1.setResponseId(5);
//        setSub1.setEntrust("hao1dd");
//        SetSub setSub2 = new SetSub();
//        setSub2.setSetId(3);
//        setSub2.setResponseId(6);
//        setSub2.setEntrust("33aas");
//        List<SetSub> list=new ArrayList<>();
//        list.add(setSub);
//        list.add(setSub1);
//        list.add(setSub2);
//        List<Object> list1=setManageService.getSubInfoList(list);
//        return;
//    }
//}