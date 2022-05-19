package com.security.core.service.impl;

import com.security.core.domain.Member;
import com.security.core.repository.UserRepository;
import com.security.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public void createUser(Member member) {
        member.setDefaultRole();
        userRepository.save(member);
    }
}
