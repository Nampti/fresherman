package com.fresher.fresherserivce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresher.fresherserivce.model.Center;
import com.fresher.fresherserivce.model.Fresher;
import com.fresher.fresherserivce.repository.table.CenterRepositoryJPA;
import com.fresher.fresherserivce.repository.table.FresherRepositoryJPA;
import com.fresher.fresherserivce.service.DashboardService;
import com.fresher.fresherserivce.service.FresherService;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    private final FresherRepositoryJPA fresherRepositoryJPA;

    private final CenterRepositoryJPA centerRepositoryJPA;

    @GetMapping("/freshers-count")
    public ResponseEntity<InputStreamResource> viewFreshersCount(@RequestParam String format) {
        return null;
    }

    @GetMapping("/test")
    public String showDashboard(Model model) {
        List<Fresher> freshers = fresherRepositoryJPA.findAll();
        List<Center> centers = centerRepositoryJPA.findAll();

        model.addAttribute("freshers", freshers);
        model.addAttribute("centers", centers);

        return "dashboard";
    }
}
