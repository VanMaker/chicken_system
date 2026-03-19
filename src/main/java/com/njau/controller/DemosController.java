package com.njau.controller;

import com.njau.entity.Score_New;
import com.njau.utils.AesUtil;
import com.njau.utils.ConfigUtils;
import com.njau.utils.TimeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class DemosController {

    @RequestMapping("/")
    public String home() {
        return "Welcome to the homepage!";
    }

    @GetMapping("/getScore")
    public ResponseEntity<Score_New> getResult(@RequestParam String dead_r, @RequestParam String feed, @RequestParam String drink, @RequestParam String hum, @RequestParam String temp, @RequestParam String layegg, @RequestParam String awe) {
        AesUtil aesUtil = new AesUtil();
        ConfigUtils configUtils = new ConfigUtils();
        TimeUtils timeUtils = new TimeUtils();
        ComputeTest computetest = new ComputeTest(configUtils, timeUtils, aesUtil);
//        System.out.println(dead_r);
        computetest.test(dead_r, feed, drink, layegg, awe, temp, hum);
        Score_New score = computetest.getScore();
        return ResponseEntity.ok(score);
    }


}
