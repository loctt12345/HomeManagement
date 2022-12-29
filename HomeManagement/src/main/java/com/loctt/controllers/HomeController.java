/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.controllers;

import com.loctt.pojos.Member;
import com.loctt.pojos.PayingHistory;
import com.loctt.service.MemberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author loc12345
 */
@Controller
public class HomeController {

    @Autowired
    private MemberService memberService; 
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("/")
    @Transactional
    public String index(Model model,
            @RequestParam(name = "kw", required = false, defaultValue = "") String kw,
            @RequestParam(name = "page", defaultValue = "1") String page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id =  authentication.getName();
        Member member = this.memberService.getMemberById(id);
        if (member.getRole().equals("admin")) {
            int pageInt = Integer.parseInt(page);
            model.addAttribute("memberList",
                    this.memberService.getMemberList(kw, pageInt));
            model.addAttribute("number_member", this.memberService.countMember());
            model.addAttribute("kw", kw);
            model.addAttribute("current_page", pageInt);
            model.addAttribute("max_page", 6);
        }
        if (member.getRole().equals("user")) {
            model.addAttribute("member", member);
            model.addAttribute("listPayingHistory", 
                    this.memberService.getPayingHistoryById(member.getId()));
        }
        return "index";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping("/info")
    public String info(Model model,
            @RequestParam(name = "id", required = false, defaultValue = "") String id) {
        Member mem = this.memberService.getMemberById(id);
        model.addAttribute("member", mem);
        if (mem != null) {
            model.addAttribute("listPayingHistory", this.memberService.getPayingHistoryById(id));
        }
        return "info";
    }

    @RequestMapping("/pay")
    public String pay(@RequestParam(name = "id") String id,
            @RequestParam(name = "rollback", defaultValue = "false") String rollback) {
        Member mem = this.memberService.getMemberById(id);
        if (mem != null) {
            if (rollback.equalsIgnoreCase("false")) {
                this.memberService.confirmPayingThisMonth(id);
            } else {
                this.memberService.cancelPayingThisMonth(id);
            }
        }
        return "redirect:/info?id=" + id;
    }

    @RequestMapping(value = "/updatePage")
    public String updateMemberPage(@RequestParam(value = "member_id") String memberID, Model model) {
        model.addAttribute("old_member", this.memberService.getMemberById(memberID));
        model.addAttribute("member", new Member());
        return "updatePage";
    }

    @PostMapping(value = "/updateMember")
    public String updateMember(@ModelAttribute(value = "member") Member member) {
        this.memberService.updateMember(member);
        return "redirect:/info?id=" + member.getId();
    }

    @RequestMapping(value = "/deleteMember")
    public String deleteMember(@RequestParam(value = "id") String id) {
        this.memberService.deleteMember(id);
        return "redirect:/";
    }
    
    @RequestMapping(value="/changePasswordPage")
    public String changePasswordPage(
            @RequestParam(value="status", required = false)String status, Model model) {
        model.addAttribute("status", status);
        return "changePassword";
    }
    
    @RequestMapping(value="/changePassword") 
    public String changePassword (
            @RequestParam(value="id") String id,
            @RequestParam(value="current_password") String currentPassword, 
            @RequestParam(value="new_password_confirm") String newPasswordConfirm, 
            @RequestParam(value="new_password") String newPassword) {
        if (!newPassword.equals(newPasswordConfirm)) {
            return "redirect:/changePasswordPage?status=NotMatchConfirm";
        }
        
        Member member = this.memberService.getMemberById(id);
        if (!this.passwordEncoder.matches(currentPassword, member.getPassword())) {
            return "redirect:/changePasswordPage?status=NotMatchCurrentPassword";
        }
        
        this.memberService.changePassword(id, newPassword);
        
        return "redirect:/changePasswordPage?status=changPasswordSuccessfully";
    }
}
