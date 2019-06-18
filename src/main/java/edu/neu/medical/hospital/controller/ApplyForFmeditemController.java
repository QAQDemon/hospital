package edu.neu.medical.hospital.controller;

import edu.neu.medical.hospital.bean.VisitItem;
import edu.neu.medical.hospital.service.ApplyForFmeditemService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("applyForFmeditem")
public class ApplyForFmeditemController {
    @Resource
    ApplyForFmeditemService applyForFmeditemService;

    @RequestMapping("getVisitItemList/{type}/{medicalRecordInfoId}")
    public Map<String,Object> getVisitItemList(@PathVariable("type")char type, @PathVariable("medicalRecordInfoId")int medicalRecordInfoId){
        applyForFmeditemService.setType(type);
        Map<String, Object> map = new HashMap<>();
        List<VisitItem> visitItemList =applyForFmeditemService.getVisitItemListById(medicalRecordInfoId);
        map.put("visitItemList", visitItemList);
        map.put("applyForPeople", applyForFmeditemService.getApplyForPeopleName(visitItemList));
        return map;

    }

}
