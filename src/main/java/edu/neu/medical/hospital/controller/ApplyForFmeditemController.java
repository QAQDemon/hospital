package edu.neu.medical.hospital.controller;

import edu.neu.medical.hospital.bean.VisitItem;
import edu.neu.medical.hospital.service.ApplyForFmeditemService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("applyForFmeditem")
public class ApplyForFmeditemController {
    @Resource
    ApplyForFmeditemService applyForFmeditemService;

    @RequestMapping("getVisitItemList/{type}/{medicalRecordInfoId}")
    public List<VisitItem> getVisitItemList(@PathVariable("type")char type, @PathVariable("medicalRecordInfoId")int medicalRecordInfoId){
        applyForFmeditemService.setType(type);
        return applyForFmeditemService.getVisitItemListById(medicalRecordInfoId);
    }

}
