package com.dormitory.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class MemberDTO implements OAuth2User {
    private String userid;
    private String password;
    private String username;
    private String birthdate;
    private String gender;
    private String telno;
    private String email;
    private Date regdate;
    private Timestamp lastlogindate;
    private Timestamp lastlogoutdate;
    private Timestamp lastpwdate;
    private int pwCheck;
    private String role;
    private String domitory_code;
    private String authkey;
    private String social;

    private String token;





    // 소셜로그인용
    private Map<String, Object> attribute;
    private Collection<? extends GrantedAuthority> authorities;
    private String name;

    @Override
    public Map<String, Object> getAttributes() {
        return this.attribute;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return this.authorities;
    }

    @Override
    public String getName() {
        return this.name;
    }


}
