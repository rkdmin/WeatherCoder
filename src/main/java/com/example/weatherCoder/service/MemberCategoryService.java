package com.example.weatherCoder.service;

import com.example.weatherCoder.dto.CategoryParam;
import com.example.weatherCoder.dto.SeasonInfo;
import com.example.weatherCoder.entity.Category;
import com.example.weatherCoder.entity.Member;
import com.example.weatherCoder.entity.MemberCategory;
import com.example.weatherCoder.exception.CategoryException;
import com.example.weatherCoder.repository.CategoryRepository;
import com.example.weatherCoder.repository.MemberCategoryRepository;
import com.example.weatherCoder.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.weatherCoder.type.ErrorCode.*;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberCategoryService {
    private final CategoryRepository categoryRepository;
    private final MemberCategoryRepository memberCategoryRepository;
    private final MemberRepository memberRepository;

    // 멤버-카테고리 등록
    public void registration(String username, CategoryParam.Request request){
        // 에러처리
        validationRegistration(request);

        List<Category> categoryList = new ArrayList<>();// 불러올 카테고리 리스트
        List<SeasonInfo> seasonList = request.getSeasonList();// request데이터

        // 카테고리 찾기
        for(SeasonInfo season: seasonList){
            for(String name: season.getNameList()){
                if(name != null && name.length() > 0){
                    Optional<Category> optionalCategory
                            = categoryRepository.findBySeasonAndName(season.getSeasonName(), name);

                    // 알맞은 카테고리 없음
                    if(!optionalCategory.isPresent()){
                        throw new CategoryException(CATEGORY_EMPTY);
                    }

                    categoryList.add(optionalCategory.get());
                }
            }
        }

        // 회원 찾기
        Optional<Member> optionalMember =
                memberRepository.findByUsername(username);
        if(!optionalMember.isPresent()){
            throw new CategoryException(MEMBER_EMPTY);
        }

        // 회원의 카테고리 초기화
        memberCategoryRepository.deleteAllByMember_Username(username);

        // 회원-카테고리 테이블 insert
        for(Category category: categoryList){
            memberCategoryRepository.save(MemberCategory.builder()
                    .member(optionalMember.get())
                    .category(category)
                    .build());
        }


    }

    private void validationRegistration(CategoryParam.Request request) {
        // 리스트가 비어있음
        if(CollectionUtils.isEmpty(request.getSeasonList())){
            throw new CategoryException(CATEGORY_EMPTY);
        }
    }
}