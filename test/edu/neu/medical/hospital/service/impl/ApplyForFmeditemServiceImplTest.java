package edu.neu.medical.hospital.service.impl;

import edu.neu.medical.hospital.bean.*;
import edu.neu.medical.hospital.service.ApplyForFmeditemService;
import edu.neu.medical.hospital.service.OutpatientDoctorWorkstationService;
import javafx.geometry.VerticalDirection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
}