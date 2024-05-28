package com.breath_of_the_wild_be.service.camplikeService;

import com.breath_of_the_wild_be.domain.CampLike;
import com.breath_of_the_wild_be.domain.Camping;
import com.breath_of_the_wild_be.domain.Member;
import com.breath_of_the_wild_be.dto.response.CampLikeResponseDTO;
import com.breath_of_the_wild_be.dto.request.CampLikeRequestDTO;
import com.breath_of_the_wild_be.repository.campingRepository.CampingRepository;
import com.breath_of_the_wild_be.repository.camplikeRepository.CampLikeRepository;
import com.breath_of_the_wild_be.repository.memberRepository.MemberRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampLikeServiceImpl implements CampLikeService {

    private final CampLikeRepository campLikeRepository;
    private final CampingRepository campingRepository;
    private final MemberRepository memberRepository;

    @Override
    public void likeCamping(CampLikeRequestDTO requestDto) {
        Camping camping = campingRepository.findByContentId(requestDto.getContentId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid content ID"));
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));

        if (!campLikeRepository.findByCampingAndMember(camping, member).isPresent()) {
            CampLike campLike = CampLike.builder()
                    .camping(camping)
                    .member(member)
                    .build();
            campLikeRepository.save(campLike);
        }
    }

    @Override
    public void unlikeCamping(CampLikeRequestDTO requestDto) {
        Camping camping = campingRepository.findByContentId(requestDto.getContentId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid content ID"));
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));

        campLikeRepository.findByCampingAndMember(camping, member)
                .ifPresent(campLikeRepository::delete);
    }

    @Override
    public List<CampLikeResponseDTO> getLikedCampsByUser(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));

        return campLikeRepository.findByMember(member).stream()
                .map(campLike -> {
                    Camping camping = campLike.getCamping();
                    return CampLikeResponseDTO.builder()
                            .likeid(campLike.getLikeid())
                            .contentId(camping.getContentId())
                            .email(campLike.getMember().getEmail())
                            .likedTime(campLike.getLikedTime())
                            .firstImageUrl(camping.getFirstImageUrl())
                            .facltNm(camping.getFacltNm())
                            .induty(camping.getInduty())
                            .lctCl(camping.getLctCl())
                            .addr1(camping.getAddr1())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkIfLiked(CampLikeRequestDTO requestDto) {
        Camping camping = campingRepository.findByContentId(requestDto.getContentId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid content ID"));
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));

        return campLikeRepository.findByCampingAndMember(camping, member).isPresent();
    }
}
