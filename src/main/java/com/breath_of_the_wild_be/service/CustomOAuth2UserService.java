package com.breath_of_the_wild_be.service;

import com.breath_of_the_wild_be.common.Role;
import com.breath_of_the_wild_be.domain.Member;
import com.breath_of_the_wild_be.repository.memberRepository.MemberRepository;
import com.breath_of_the_wild_be.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        String email = getEmailFromOAuth2User(oAuth2User, registrationId);
        String username = getUsernameFromOAuth2User(oAuth2User, registrationId);

        Optional<Member> memberOptional = memberRepository.findByEmail(email);

        Member member;
        if (memberOptional.isPresent()) {
            member = memberOptional.get();
        } else {
            member = Member.builder()
                    .email(email)
                    .password("")
                    .username(username)
                    .roles(Role.USER)
                    .birth("")
                    .build();
            memberRepository.save(member);
        }

        UserDetails userDetails = User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_" + member.getRoles().name())))
                .build();

        String jwtToken = jwtTokenUtil.generateToken(userDetails);

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")), oAuth2User.getAttributes(), userNameAttributeName);
    }

    private String getEmailFromOAuth2User(OAuth2User oAuth2User, String registrationId) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        if ("kakao".equals(registrationId)) {
            // Return a random email value
            return "random-" + UUID.randomUUID().toString() + "@kakao.com";
        } else if ("naver".equals(registrationId)) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            return (String) response.get("email");
        } else {
            return (String) attributes.get("email");
        }
    }

    private String getUsernameFromOAuth2User(OAuth2User oAuth2User, String registrationId) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        if ("kakao".equals(registrationId)) {
            Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
            return (String) properties.get("nickname");
        } else if ("naver".equals(registrationId)) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            return (String) response.get("name");
        } else {
            return (String) attributes.get("name");
        }
    }
}
