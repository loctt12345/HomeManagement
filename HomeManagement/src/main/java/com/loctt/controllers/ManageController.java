/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.controllers;

import com.loctt.pojos.Member;
import com.loctt.service.HomeService;
import com.loctt.service.MemberService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author loc12345
 */
@Controller
public class ManageController {
    
    @Autowired
    private MemberService memberService;
    @Autowired
    private HomeService homeService;
    
    @RequestMapping("/manage")
    public String manage() {
        return "manage";
    }
    
    @RequestMapping("/manage/money")
    public String money(Model model) {
        model.addAttribute("date", this.homeService.getPayingDate());
        return "money";
    }
    
    @RequestMapping("/manage/setAll")
    public String setAll() {
        this.homeService.resetPaying();
        return "redirect:/";
    }
    
    @RequestMapping("/manage/setAuto")
    public String resetAuto(@RequestParam(value="date") String date) {
        this.homeService.resetPayingAuto(date);
        return "redirect:/manage/money";
    }
    
    @RequestMapping("/manage/removeAuto")
    public String removeAuto() {
        this.homeService.removePayingAuto();
        return "redirect:/manage/money";
    }
    
    @RequestMapping("/manage/addMemberPage")
    public String addNewMemberPage(Model model) {
        model.addAttribute("member", new Member());
        return "addNewMember";
    }
    
    @PostMapping(value = "/manage/addMember", 
            produces = "application/x-www-form-urlencoded;charset=UTF-8")
    public String addNewMember(@ModelAttribute(value="member") Member member) {
        this.memberService.addNewMember(member.getFullName(), member.getHomeRole(), 
                member.getRoomID());
        return "redirect:/";
    }
    
}
