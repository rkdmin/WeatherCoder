package com.example.weatherCoder.service;

import com.example.weatherCoder.dto.StyleParam.Request;
import com.example.weatherCoder.entity.Member;
import com.example.weatherCoder.entity.Style;
import com.example.weatherCoder.entity.MemberStyle;
import com.example.weatherCoder.exception.MemberException;
import com.example.weatherCoder.repository.MemberRepository;
import com.example.weatherCoder.repository.MemberStyleRepository;
import com.example.weatherCoder.repository.StyleRepository;
import com.example.weatherCoder.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

import static com.example.weatherCoder.type.ErrorCode.STYLE_EMPTY;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberStyleService {
    private final MemberStyleRepository memberStyleRepository;
    private final StyleRepository styleRepository;
    private final MemberRepository memberRepository;

    // 스타일 등록
    public void registration(Member member, List<String> styleList) {
        if(CollectionUtils.isEmpty(styleList)){
            throw new MemberException(STYLE_EMPTY);
        }

        for(String styleName: styleList){
            Optional<Style> optionalStyle = styleRepository.findByStyleName(styleName);
            if(!optionalStyle.isPresent()){
                throw new MemberException(STYLE_EMPTY);
            }

            memberStyleRepository.save(MemberStyle.builder()
                            .member(member)
                            .style(optionalStyle.get())
                            .build());
        }
    }

    public void update(String username, Request request) {
        Optional<Member> optionalMember = memberRepository.findByUsername(username);
        if(optionalMember.isEmpty()){
            throw new MemberException(ErrorCode.MEMBER_EMPTY);
        }
        if(request.getStyleList().size() == 0){
            throw new MemberException(STYLE_EMPTY);
        }

        // 스타일 먼저 초기화
        memberStyleRepository.deleteAllByMember_Username(username);

        for(String styleName: request.getStyleList()){
            Optional<Style> optionalStyle = styleRepository.findByStyleName(styleName);
            if(!optionalStyle.isPresent()){
                throw new MemberException(STYLE_EMPTY);
            }

            memberStyleRepository.save(MemberStyle.builder()
                .member(optionalMember.get())
                .style(optionalStyle.get())
                .build());
        }
    }
}