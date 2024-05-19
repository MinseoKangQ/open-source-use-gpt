package com.project.gptopensourceback.github;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RepositoryItem {
    private String name;
    private String owner;
    private String description;
    private String url;
    private int stars;
}
