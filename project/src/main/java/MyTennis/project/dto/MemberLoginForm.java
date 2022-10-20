package MyTennis.project.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MemberLoginForm {

    @NotNull @Size(min=5, max=20, message="ID는 5~20자 이내여야 합니다.")
    private String loginId;

    @NotNull @Size(min=5, max=30, message="비밀번호는 5~30자 이내여야 합니다.")
    private String password;
}
