package com.project.gptopensourceback.github;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchResultData {
    private int totalCount;
    private List<RepositoryItem> items;
}
