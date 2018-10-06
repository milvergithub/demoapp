package com.milver.service;

import com.milver.service.dto.RecipeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecipeService extends GenericService<RecipeDto, Long> {

    Page<RecipeDto> findRecipesByUser(Long id, Pageable pageable);
    Page<RecipeDto> findAllRecipesByCriteria(String search, Pageable pageable);
}
