package com.milver.service;

import com.milver.domain.Recipe;
import com.milver.domain.User;
import com.milver.repository.RecipeRepository;
import com.milver.repository.UserRepository;
import com.milver.service.dto.RecipeDto;
import com.milver.service.mapper.RecipeMapper;
import com.milver.web.error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    private static final String ENTITY = "Recipe";

    @Override
    public Page<RecipeDto> findAllEntities(Pageable pageable) {
        return recipeRepository.findAll(pageable).map(recipe -> recipeMapper.recipeToRecipeDto(recipe));
    }

    @Override
    public RecipeDto save(RecipeDto recipeDto) {
        Optional<User> user = userRepository.findById(recipeDto.getUserId());
        if (!user.isPresent())
            throw new ResourceNotFoundException("User");
        Recipe recipe = recipeMapper.recipeDtoToRecipe(recipeDto);
        recipe.setUser(user.get());
        recipe = recipeRepository.save(recipe);
        return recipeMapper.recipeToRecipeDto(recipe);
    }

    @Override
    public RecipeDto update(RecipeDto entity) {
        if (!recipeRepository.findById(entity.getId()).isPresent())
            throw new ResourceNotFoundException(ENTITY);
        return save(entity);
    }

    @Override
    public RecipeDto findEntity(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (!recipe.isPresent())
            throw new ResourceNotFoundException(ENTITY);
        return recipeMapper.recipeToRecipeDto(recipe.get());
    }

    @Override
    public void deleteEntity(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (!recipe.isPresent())
            throw new ResourceNotFoundException(ENTITY);
        recipeRepository.delete(recipe.get());
    }

    @Override
    public Page<RecipeDto> findRecipesByUser(Long id, Pageable pageable) {
        return recipeRepository.findByUserId(id, pageable).map(recipe -> recipeMapper.recipeToRecipeDto(recipe));
    }
}
