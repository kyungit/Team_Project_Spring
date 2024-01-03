package com.dormitory.service;

import com.dormitory.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class OAuth2UserDetailsServiceImpl extends DefaultOAuth2UserService {
    private final PasswordEncoder pwdEncoder;
    private final MemberService service;

    @SneakyThrows
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        String provider  = userRequest.getClientRegistration().getRegistrationId();
        String username = "";
        String userid = "";

        OAuth2User oAuth2User = super.loadUser(userRequest);


        if (provider.equals("naver")) {  // 네이버 로그인인 경우
            Map<String, Object> attributes = oAuth2User.getAttributes();
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            username = response.get("id").toString();
            userid = (String) response.get("email");
        } else if (provider.equals("google")) {  // 구글 로그인인 경우
            username = oAuth2User.getAttribute("sub");
            userid = oAuth2User.getAttribute("email");
        } else if (provider.equals("kakao")) { //카카오 로그인인 경우
            Map<String, Object> attributes = oAuth2User.getAttributes();
            Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");
            System.out.println(response);
            username = response.get("profile").toString();
            userid = response.get("email").toString();
        }



        log.info("provider = {}", provider);
        log.info("providerId = {}", username);
        log.info("userid = {}", userid);


        oAuth2User.getAttributes().forEach((k,v ) ->  {
            log.info(k + ":" + v);
        });


        MemberDTO member = new MemberDTO();
        member.setUserid(userid);
        member.setUsername(username);

        MemberDTO savedMember = saveSocialMember(userid, provider);  // 변경된 부분

        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(savedMember.getRole());
        grantedAuthorities.add(grantedAuthority);

        savedMember.setAttribute(oAuth2User.getAttributes());
        savedMember.setAuthorities(grantedAuthorities);
        savedMember.setUsername(savedMember.getUsername());


        if(service.getMemberInfo(savedMember.getUserid())==null){
            service.memberSave(savedMember);
        }





        return oAuth2User;  // 변경된 부분
    }

    private MemberDTO saveSocialMember(String userid, String provider) throws Exception {

        //구글 회원 계정으로 로그인 한 회원의 경우 사이트 운영에 필요한 최소한의 정보를
        //가공해서 tbl_member에 입력해야 함.

//		Optional<MemberEntity> result = memberRepository.findById(email);
        Optional<MemberDTO> result = Optional.ofNullable(service.getMemberInfo(userid));
        if(result.isPresent()) {
            return result.get();
        }
//
//        MemberDTO member = MemberDTO.builder()
//                .user_id(userid)
//                .user_nm("구글회원")
//                .password(pwdEncoder.encode("12345"))
//                .role("USER")
//                .regdate(LocalDate.now())
//                .fromSocial("Y")
//                .build();

        //String provider = (String) session.getAttribute("provider");  // 로그인 공급자를 세션에서 참조
        String username = "";
        if (provider.equals("naver")) {
            username = "네이버회원";
        } else if (provider.equals("google")) {
            username = "구글회원";
        } else if (provider.equals("kakao")) {
            username = "카카오회원";
        }
//        Cookie[] cookie = request.getCookies();

        System.out.println("소셜로그인 유저네임 = " + username);

        MemberDTO member = new MemberDTO();
        member.setUserid(userid);
        member.setPassword(pwdEncoder.encode("12345"));
        member.setUsername(username);
        member.setRole("USER");
        member.setSocial("Y");
//        member.setToken(cookie[0].toString());
        System.out.println("member의 유저네임 = " + member.getUsername());



        return member;
    }

}