package myweb.secondboard.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import myweb.secondboard.domain.File;
import myweb.secondboard.domain.Member;
import myweb.secondboard.domain.Record;
import myweb.secondboard.dto.MemberSaveForm;
import myweb.secondboard.dto.MemberUpdateForm;
import myweb.secondboard.dto.UpdatePasswordForm;
import myweb.secondboard.repository.MemberRepository;
import myweb.secondboard.repository.RecordRepository;
import myweb.secondboard.web.PasswordEncrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  private final FileService fileService;

  private final RecordRepository recordRepository;

  @Transactional
  public Long signUp(MemberSaveForm form) throws NoSuchAlgorithmException {
    Record record = Record.createRecord();
    recordRepository.save(record);
    Member member = Member.createMember(form, record);
    memberRepository.save(member);
    return member.getId();
  }

  public Optional<Member> findByLoginId(String loginId) {
    return memberRepository.findByLoginId(loginId);
  }

  public Optional<Member> findByNickname(String nickname) {
    return memberRepository.findByNickname(nickname);
  }

  public Optional<Member> findByEmail(String email) {
    return memberRepository.findByEmail(email);
  }

  public Member findById(Long memberId) {
    return memberRepository.findById(memberId).get();
  }

  @Transactional
  public Member kakaoSignUp(Map<String, Object> userInfo, String access_token) {
    Member member = Member.createKakaoMember(userInfo, access_token);
    memberRepository.save(member);
    return member;
  }

  @Transactional
  public void renewAccessToken(Member member, String access_token) {
    member.setAccessToken(access_token);
  }

  @Transactional
  public void updatePassword(UpdatePasswordForm form, Member member) throws NoSuchAlgorithmException {
    PasswordEncrypt passwordEncrypt = new PasswordEncrypt();
    member.setPassword(passwordEncrypt.encrypt(form.getUpdatePassword()));
    memberRepository.save(member);
  }


  @Transactional
  public Long updateMember(MemberUpdateForm form, MultipartFile file) throws IOException {

    Member member =findById(form.getId());

    File originFile = member.getFile();
    byte[] files = fileService.ImgSave(file);

    if(!file.isEmpty()){
      originFile.updateImgPath(originFile, files);
    }

    member.updateMember(form, member);


    return member.getId();
  }


}
