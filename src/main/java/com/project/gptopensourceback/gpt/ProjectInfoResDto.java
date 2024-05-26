package com.project.gptopensourceback.gpt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectInfoResDto {
    private String projectIntroduction;
    private String projectUsefulWhere;
}
