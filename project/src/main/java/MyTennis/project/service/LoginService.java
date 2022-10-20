package MyTennis.project.service;

import MyTennis.project.domain.Member;
import MyTennis.project.dto.MemberLoginForm;
import MyTennis.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;


    public  Member loginCheck(MemberLoginForm loginForm){

        String loginId = loginForm.getLoginId();
        String password= loginForm.getPassword();

        return memberRepository.findByLoginId(loginId).filter(m->m.getPassword().equals(password))
                .orElse(null);
    }


}
