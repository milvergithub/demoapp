package com.milver.service.mapper;

import com.milver.domain.Recipe;
import com.milver.service.dto.RecipeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {})
public interface RecipeMapper {

    @Mapping(source = "recipe.user.id", target = "userId")
    RecipeDto recipeToRecipeDto(Recipe recipe);

    Recipe recipeDtoToRecipe(RecipeDto recipeDto);

    void updateRecipeFromDto(RecipeDto recipeDto, @MappingTarget Recipe recipe);

}
