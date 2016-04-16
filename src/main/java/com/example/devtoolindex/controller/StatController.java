package com.example.devtoolindex.controller;

import com.example.devtoolindex.db.entity.IPStatEntity;
import com.example.devtoolindex.db.service.IPStatService;
import com.example.devtoolindex.response.StatResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j @RestController @RequestMapping("/stat") public class StatController {

    @Autowired IPStatService ipStatService;

    @RequestMapping("/general") @ResponseBody public StatResult general() {
        int count = ipStatService.count();
        IPStatEntity ipStatEntity = ipStatService.findLatest();
        log.debug("aaa" + ipStatEntity.getVisitDate());
        return new StatResult(count, ipStatEntity);
    }
}
