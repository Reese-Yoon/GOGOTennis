package MyTennis.project.domain;

import MyTennis.project.dto.MemberSaveForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Optional;

@Entity @Getter @Setter
@NoArgsConstructor
public class Member implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_Id")
    private Long Id;

    @NotNull @Column(unique = true, length = 20)
    private String LoginId;

    @NotNull @Column(unique = true, length = 30)
    private String Password;

    @NotNull @Column(unique = true, length = 10)
    private String Nickname;

    @NotNull @Column(unique = true, length = 50)
    private String Email;


    // MemberSaveForm에서 입력한 값을 받아서 데이터베이스에 저장한다.
    // Control
    public static Member createMember(MemberSaveForm memberSaveForm){
        Member member = new Member();

        member.LoginId = memberSaveForm.getLoginId();
        member.Password = memberSaveForm.getPassword();
        member.Nickname = memberSaveForm.getNickname();
        member.Email = memberSaveForm.getEmail();

        return member;
    }



}
