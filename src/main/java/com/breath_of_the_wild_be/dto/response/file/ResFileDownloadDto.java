package com.breath_of_the_wild_be.dto.response.file;

import com.breath_of_the_wild_be.domain.FileEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResFileDownloadDto {

    private String filename;
    private String fileType;
    private byte[] content;

    @Builder
    public ResFileDownloadDto(String filename, String fileType, byte[] content) {
        this.filename = filename;
        this.fileType = fileType;
        this.content = content;
    }

    public static ResFileDownloadDto fromFileResource(FileEntity file, String contentType, byte[] content) {
        return ResFileDownloadDto.builder()
                .filename(file.getOriginFileName())
                .fileType(contentType)
                .content(content)
                .build();
    }
}

