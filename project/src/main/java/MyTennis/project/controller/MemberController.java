package MyTennis.project.controller;


import MyTennis.project.domain.Member;
import MyTennis.project.dto.MemberLoginForm;
import MyTennis.project.dto.MemberSaveForm;
import MyTennis.project.service.LoginService;
import MyTennis.project.service.MemberService;
import MyTennis.project.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final LoginService loginService;


    @GetMapping("/save")
    public String MemberSave(Model model) {
        // 초기화 빈 객체를 만들어줌
        model.addAttribute("signInfo", new MemberSaveForm());
        // "signInfo"라는 memberDTO로 입력받을 준비를 하고 signForm.html로 연결!
        // home -> members/save -> loginForm.html
        return "login/signForm";
    }

    @PostMapping("/waveform")
    public String MemberSaveForm(@Validated @ModelAttribute("signInfo") MemberSaveForm signInfo,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "login/signForm";
        }

        // memberService에서 서비스로직을 실행!
        memberService.memberAdd(signInfo);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String MembersLogin(Model model) {
        model.addAttribute("loginForm", new MemberLoginForm());
        return "login/loginForm";
    }

    @PostMapping("/login_check")
    public String MemberLoginCheck(@ModelAttribute("loginForm") MemberLoginForm memberLoginForm,
                                   BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "login/loginForm";
        }

        // 유저가 입력한 아이디와 그 아이디에 해당하는 패스워드를 가진 객체가 있는지 검사
        Member loginMember = loginService.loginCheck(memberLoginForm);

        if(loginMember == null){
            bindingResult.reject("loginFall","아이디 또는 비밀번호가 맞지 않습니다.");
            return "/login/loginForm";
        }


        // 로그인 성공 로직
        // fasle -> 세션이 없으면 생성 안함.
        request.getSession().setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }

        return "redirect:/";
    }




}

