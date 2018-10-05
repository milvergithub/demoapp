package com.milver.service.mapper;

import com.milver.domain.Recipe;
import com.milver.service.dto.RecipeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface RecipeMapper {

    RecipeDto recipeToRecipeDto(Recipe recipe);

    Recipe recipeDtoToRecipe(RecipeDto recipeDto);
}
