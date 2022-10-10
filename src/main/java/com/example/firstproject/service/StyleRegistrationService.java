package com.example.firstproject.service;

import com.example.firstproject.aop.MailComponents;
import com.example.firstproject.dto.MemberDto;
import com.example.firstproject.entity.Member;
import com.example.firstproject.entity.Style;
import com.example.firstproject.entity.StyleRegistration;
import com.example.firstproject.exception.MemberException;
import com.example.firstproject.repository.MemberRepository;
import com.example.firstproject.repository.StyleRegistrationRepository;
import com.example.firstproject.repository.StyleRepository;
import com.example.firstproject.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import static com.example.firstproject.type.ErrorCode.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class StyleRegistrationService {
    private final StyleRegistrationRepository styleRegistrationRepository;
    private final StyleRepository styleRepository;

    // 스타일 등록
    public void registration(Member member, List<String> styleList) throws NoSuchAlgorithmException {
        if(styleList == null){
            throw new MemberException(STYLE_EMPTY);
        }

        for(String styleName: styleList){
            Optional<Style> optionalStyle = styleRepository.findByStyleName(styleName);
            if(!optionalStyle.isPresent()){
                throw new MemberException(STYLE_EMPTY);
            }

            styleRegistrationRepository.save(StyleRegistration.builder()
                            .member(member)
                            .email(member.getEmail())
                            .style(optionalStyle.get())
                            .styleName(optionalStyle.get().getStyleName())
                            .build());
        }
    }
}