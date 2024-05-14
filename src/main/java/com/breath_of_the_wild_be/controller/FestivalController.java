package com.breath_of_the_wild_be.controller;

import com.breath_of_the_wild_be.service.festivalService.FestivalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/festivals")
public class FestivalController {

    private final FestivalService festivalService;

    @Autowired
    public FestivalController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @GetMapping("/festivals")
    public String getFestivals(Model model) {
        // fetchDataAndProcess 메서드를 호출하여 데이터를 가져옴
        festivalService.fetchDataAndProcess();

        // 저장된 축제 목록을 가져와 모델에 추가
//        List<Festival> festivalList = festivalService.getAllFestivals();
//        model.addAttribute("festivals", festivalList);

        // festivals.html로 이동
        return "festivals";
    }
    @PostMapping("/fes")
    public String loadAndSave(@RequestParam("date") String date, Model model) {
        return "redirect:/findname";
    }

}