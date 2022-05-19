package com.security.core.security.handler;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 인증 실패 핸들러. onAuth...메서드는 실패했을 경우므로 파라미터에 AuthenticationException이 있음
 */
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorMessage = "Invalid UserName Or Password";

        if(exception instanceof BadCredentialsException){
            errorMessage = "Invalid UserName Or Password";
        } else if(exception instanceof InsufficientAuthenticationException){
            errorMessage = "Invalid Secret Key !";
        }

        setDefaultFailureUrl("/login?error=ture&exception="+ exception.getMessage());
        super.onAuthenticationFailure(request,response,exception);
    }
}
