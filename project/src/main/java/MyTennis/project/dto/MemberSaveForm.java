package MyTennis.project.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MemberSaveForm {

    @NotNull @Size(min=5, max=20, message="ID는 5~20자 이내여야 합니다.")
    private String loginId;

    @NotNull @Size(min=5, max=30, message="비밀번호는 5~30자 이내여야 합니다.")
    private String password;

    @NotNull @Size(min=2, max=10, message="닉네임은 2~10자 이내여야 합니다.")
    private String nickname;

    @NotNull @Size(max=50, message="이메일은 최대 50자 입니다.") @Email
    private String email;





}
