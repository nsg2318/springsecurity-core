package com.security.core.security.service;

import com.security.core.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * UserDetailsService 를 구현한 CustomUserDetailsService 를 만드는 과정에서 필수로 구현해야하는 loadUserByUsername 메서드의 return 타입인 UserDetails 를 만들기 위함.
 * UserDetails 인터페이스의 구현체 User 를 상속받아 Member 객체에서 정보를 가져오기 위해 만듦
 */
public class MemberContext extends User {

    @Getter
    private final Member member;

    public MemberContext(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
        this.member = member;
    }
}
