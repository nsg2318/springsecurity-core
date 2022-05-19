package com.security.core.security.provider;

import com.security.core.security.service.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * UserDetails 를 이용해서(인스턴스는 MemberContext) 추가적인 검증을 위한 클래스
 */
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    // 검증용 메서드 authentication 객체는 AuthenticationManager 로부터 전달받는 클래스
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //요청에서 가져온 값
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        //userDetailsService에서 가져온 값(Custom 으로 구현)
        MemberContext memberContext = (MemberContext) userDetailsService.loadUserByUsername(username);

        //패스워드 불일치시 인증 실패
        if (!passwordEncoder.matches(password, memberContext.getPassword())) {
            throw new BadCredentialsException("BadCredentialsException");
        }

        //+++ ... 설계 정책에 따라 구성이 달라질 수 있음

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberContext.getMember(), null, memberContext.getAuthorities());
        return authenticationToken;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        //토큰이 현재 파라미터로 전달된 클래스의 타입과 일치할 때, CustomAuthenticationProvider 가 인증을 처리하도록 조건을 주는 메서드
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
