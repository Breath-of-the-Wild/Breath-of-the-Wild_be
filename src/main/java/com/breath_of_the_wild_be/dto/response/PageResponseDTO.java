package com.breath_of_the_wild_be.dto.response;
import com.breath_of_the_wild_be.dto.request.PageRequestDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// PageResponseDTO는 나중에 다른 타입의 DTO들을 이용할 수 있도록 제네릭 타입으로 작성
// PageResponseDTO는 DTO의 리스트와 전체 데이터의 수를 지정하면 페이지 처리에
// 필요한 번호나 이전/다음에 대한 처리를 함

@Data
public class PageResponseDTO<E> {  //TodoDTO

    private List<E> dtoList;

    private List<Integer> pageNumList;  // 페이지 처리를 위해 필요한 번호

    private PageRequestDTO pageRequestDTO;

    private boolean prev, next;

    private int totalCount, prevPage, nextPage, totalPage, current;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long totalCount) {

        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = (int)totalCount;

        int end =   (int)(Math.ceil( pageRequestDTO.getPage() / 10.0 )) *  10;

        int start = end - 9;

        int last =  (int)(Math.ceil((totalCount/(double)pageRequestDTO.getSize())));

        end =  end > last ? last: end;

        this.prev = start > 1;


        this.next =  totalCount > end * pageRequestDTO.getSize();

        this.pageNumList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());

        if(prev) {
            this.prevPage = start -1;
        }

        if(next) {
            this.nextPage = end + 1;
        }

        this.totalPage = this.pageNumList.size();

        this.current = pageRequestDTO.getPage();

    }
}
