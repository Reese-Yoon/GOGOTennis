package MyTennis.project.service;


import MyTennis.project.domain.Member;
import MyTennis.project.dto.MemberLoginForm;
import MyTennis.project.dto.MemberSaveForm;
import MyTennis.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

     private final MemberRepository memberRepository;

    @Transactional
    public Long memberAdd(MemberSaveForm memberSaveForm){
        Member member = Member.createMember(memberSaveForm);
        memberRepository.save(member);
        return member.getId();
        //Repository에서 따로 쿼리문을 작성하지 않아도
        // JpaRepository를 상속받았기에 기본 쿼리문은 작성할 수 있다.
    }


}
