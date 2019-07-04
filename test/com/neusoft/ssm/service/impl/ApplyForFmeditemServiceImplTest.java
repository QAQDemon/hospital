package com.neusoft.ssm.service.impl;

import com.neusoft.ssm.bean.CommonOption;
import com.neusoft.ssm.bean.Fmeditem;
import com.neusoft.ssm.bean.VisitItem;
import com.neusoft.ssm.bean.VisitItemDetail;
import com.neusoft.ssm.service.OutpatientDoctorWorkstationService;
import com.neusoft.ssm.bean.*;
import com.neusoft.ssm.service.ApplyForFmeditemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

@WebAppConfiguration("src/main/webapp/static")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ApplyForFmeditemServiceImplTest {
    @Resource
    ApplyForFmeditemService applyForFmeditemService;
    @Resource
    OutpatientDoctorWorkstationService outpatientDoctorWorkstationService;

    @Before
    public void bef(){
        applyForFmeditemService.setType('1');//检查
    }

    @Test
    public void searchFmeditemList() {
//        List<Fmeditem>list= applyForFmeditemService.searchFmeditemList("c");
//        List<Fmeditem>list1= applyForFmeditemService.searchFmeditemList("");
//        List<Fmeditem>list3= applyForFmeditemService.searchFmeditemList("C");
        return;
    }

    @Test
    public void getVisitItemListById() {
        List<VisitItem> list=applyForFmeditemService.getVisitItemListById(1);
        List<VisitItem> list1=applyForFmeditemService.getVisitItemListById(2);
        return;
    }

    @Test
    public void getVisitItemDetailListById() {
        List<VisitItemDetail> list = applyForFmeditemService.getVisitItemDetailListById(1);
        List<VisitItemDetail> list1 = applyForFmeditemService.getVisitItemDetailListById(2);
        return;
    }

    @Test
    public void getFmeditemListByList(){
        List<VisitItemDetail> list = applyForFmeditemService.getVisitItemDetailListById(1);
        List<Fmeditem> list1 = applyForFmeditemService.getFmeditemListByList(list);
        return;
    }

    @Test
    public void setVisitItemAndDetailList() {
        VisitItem visitItem=new VisitItem();
        visitItem.setId(7);
        visitItem.setMedicalRecordInfoId(1);
        visitItem.setApplicationDoctorId(3);
        visitItem.setExecutionStatus("2");
        List<VisitItemDetail> visitItemDetailList=new ArrayList<>();
        VisitItemDetail visitItemDetail = new VisitItemDetail();
        visitItemDetail.setVisitItemId(7);
        visitItemDetail.setFmeditemId(2);
        VisitItemDetail visitItemDetail2 = new VisitItemDetail();
        visitItemDetail2.setVisitItemId(7);
        visitItemDetail2.setFmeditemId(8);
        visitItemDetail2.setDoctorEntrustment("bbx");
        visitItemDetailList.add(visitItemDetail);
        visitItemDetailList.add(visitItemDetail2);
        applyForFmeditemService.setVisitItemAndDetailList(visitItem, visitItemDetailList);
        return;
    }

    @Test
    public void getCommonFmeditemList() {
        List<CommonOption> commonOptionList=outpatientDoctorWorkstationService.getCommonOptionById('3',1);
        List<Fmeditem> list=applyForFmeditemService.getCommonFmeditemList(commonOptionList);
        return;
    }

    @Test
    public void deleteCommonFmeditem() {
//        applyForFmeditemService.deleteCommonFmeditem(1, 2);
    }

    @Test
    public void addCommonFmeditem() {
        VisitItemDetail visitItemDetail = new VisitItemDetail();
        visitItemDetail.setFmeditemId(8);
//        applyForFmeditemService.addCommonFmeditem(1,visitItemDetail);
    }

    @Test
    public void getVisitItemResult() {
        VisitItemDetail visitItemDetail = new VisitItemDetail();
        visitItemDetail.setId(3);
//        VisitItemResult visitItemResult=applyForFmeditemService.getVisitItemResult(visitItemDetail);
        return;
    }

    @Test
    public void setVisitItemAndDetailList1() {
        VisitItem visitItem=new VisitItem();
        visitItem.setId(16);
        visitItem.setPurposeRequirement("fagg1");
        List<VisitItemDetail> visitItemDetailList=new ArrayList<>();
        VisitItemDetail visitItemDetail = new VisitItemDetail();
        visitItemDetail.setVisitItemId(7);
        visitItemDetail.setFmeditemId(2);
        VisitItemDetail visitItemDetail2 = new VisitItemDetail();
        visitItemDetail2.setVisitItemId(7);
        visitItemDetail2.setFmeditemId(8);
        visitItemDetail2.setDoctorEntrustment("bbx");
        visitItemDetailList.add(visitItemDetail);
        visitItemDetailList.add(visitItemDetail2);
        int bol=applyForFmeditemService.setVisitItemAndDetailList(visitItem, visitItemDetailList);
        return;
    }

    @Test
    public void setVisitItemAndDetailList2() {
        VisitItem visitItem = new VisitItem();
        List<VisitItemDetail> visitItemDetailList = new ArrayList<>();
        visitItem.setMedicalRecordInfoId(82);
        assertEquals(0,applyForFmeditemService.setVisitItemAndDetailList(visitItem,visitItemDetailList));
        visitItem.setMedicalRecordInfoId(81);
        assertEquals(1,applyForFmeditemService.setVisitItemAndDetailList(visitItem,visitItemDetailList));
    }
}