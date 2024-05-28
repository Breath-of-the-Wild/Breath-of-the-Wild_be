package com.breath_of_the_wild_be.service.memberService;

import com.breath_of_the_wild_be.common.Role;
import com.breath_of_the_wild_be.common.exception.MemberException;
import com.breath_of_the_wild_be.common.exception.ResourceNotFoundException;
import com.breath_of_the_wild_be.dto.request.member.MemberLoginDto;
import com.breath_of_the_wild_be.dto.request.member.MemberRegisterDto;
import com.breath_of_the_wild_be.dto.request.member.MemberUpdateDto;
import com.breath_of_the_wild_be.dto.response.member.MemberResponseDto;
import com.breath_of_the_wild_be.dto.response.member.MemberTokenDto;
import com.breath_of_the_wild_be.domain.Member;
import com.breath_of_the_wild_be.domain.RefreshToken;
import com.breath_of_the_wild_be.repository.RefreshTokenRepository;
import com.breath_of_the_wild_be.repository.memberRepository.MemberRepository;
import com.breath_of_the_wild_be.security.jwt.CustomUserDetailsService;
import com.breath_of_the_wild_be.security.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final PasswordEncoder encoder;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public HttpStatus checkIdDuplicate(String email) {
        isExistUserEmail(email);
        return HttpStatus.OK;
    }

    @Override
    public MemberResponseDto register(MemberRegisterDto registerDto) {
        isExistUserEmail(registerDto.getEmail());
        checkPassword(registerDto.getPassword(), registerDto.getPasswordCheck());

        // 패스워드 암호화
        String encodePwd = encoder.encode(registerDto.getPassword());
        registerDto.setPassword(encodePwd);

        Member saveMember = memberRepository.save(
                MemberRegisterDto.ofEntity(registerDto));

        return MemberResponseDto.fromEntity(saveMember);
    }

    public MemberTokenDto login(MemberLoginDto loginDto) {
        authenticate(loginDto.getEmail(), loginDto.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());
        String email = loginDto.getEmail();
        String username = memberRepository.findUsernameByEmail(email);
        checkEncodePassword(loginDto.getPassword(), userDetails.getPassword());

        String token = jwtTokenUtil.generateToken(userDetails);
        String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);

        // 리프레시 토큰을 데이터베이스에 저장
        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setToken(refreshToken);
        refreshTokenEntity.setUsername(username);
        refreshTokenEntity.setExpirationDate(new Date(System.currentTimeMillis() + jwtTokenUtil.getRefreshTokenExpirationTime()));
//        refreshTokenRepository.save(refreshTokenEntity);

        return MemberTokenDto.fromEntity(userDetails, token, refreshToken, username);
    }

    @Override
    public MemberResponseDto check(Member member, String password) {
        Member checkMember = (Member) userDetailsService.loadUserByUsername(member.getEmail());
        checkEncodePassword(password, checkMember.getPassword());
        return MemberResponseDto.fromEntity(checkMember);
    }

    @Override
    public MemberResponseDto update(Member member, MemberUpdateDto updateDto) {
        checkPassword(updateDto.getPassword(), updateDto.getPasswordCheck());
        String encodePwd = encoder.encode(updateDto.getPassword());
        Member updateMember = memberRepository.findByEmail(member.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Email", member.getEmail())
        );
        updateMember.update(encodePwd, updateDto.getUsername());
        return MemberResponseDto.fromEntity(updateMember);
    }

    @Override
    public void adminregister() {
        // 관리자 계정 정보
        String adminEmail = "admin@admin.com";
        String adminPassword = "admin";
        String adminName = "관리자";
        String birth = "0000-00-00";

        // 이미 존재하는 이메일인지 확인
        isExistUserEmail(adminEmail);

        // 패스워드 확인
        checkPassword(adminPassword, adminPassword);

        // 패스워드 암호화
        String encodedPwd = encoder.encode(adminPassword);

        // 관리자 Member 객체 생성
        Member adminMember = Member.builder()
                .email(adminEmail)
                .password(encodedPwd)
                .username(adminName)
                .roles(Role.ADMIN)
                .birth(birth)
                .build();

        // Member 저장
        memberRepository.save(adminMember);
    }

    /**
     * 사용자 인증
     * @param email
     * @param pwd
     */
    private void authenticate(String email, String pwd) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, pwd));
        } catch (DisabledException e) {
            throw new MemberException("인증되지 않은 아이디입니다.", HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            throw new MemberException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 아이디(이메일) 중복 체크
     * @param email
     */
    private void isExistUserEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new MemberException("이미 사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 비밀번호와 비밀번호 확인이 같은지 체크
     * @param password
     * @param passwordCheck
     */
    private void checkPassword(String password, String passwordCheck) {
        if (!password.equals(passwordCheck)) {
            throw new MemberException("패스워드 불일치", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 사용자가 입력한 비번과 DB에 저장된 비번이 같은지 체크 : 인코딩 확인
     * @param rawPassword
     * @param encodedPassword
     */
    private void checkEncodePassword(String rawPassword, String encodedPassword) {
        if (!encoder.matches(rawPassword, encodedPassword)) {
            throw new MemberException("패스워드 불일치", HttpStatus.BAD_REQUEST);
        }
    }
}
