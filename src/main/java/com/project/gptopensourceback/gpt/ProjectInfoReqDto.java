package com.project.gptopensourceback.gpt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInfoReqDto {
    private String name;
    private String owner;
    private String description;
    private String url;
}
