package com.milver.web.rest;

import com.milver.service.dto.RecipeDto;
import com.milver.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/recipes")
public class RecipeResource {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity getRecipes(Pageable pageable) {
        Page<RecipeDto> page = recipeService.findAllEntities(pageable);
        return new ResponseEntity<Object>(page,null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getRecipe(@PathVariable Long id) {
        RecipeDto recipeDto = recipeService.findEntity(id);
        return new ResponseEntity<>(recipeDto, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getRecipesByUser(@PathVariable Long id, Pageable pageable) {
        Page<RecipeDto> page = recipeService.findRecipesByUser(id, pageable);
        return new ResponseEntity<Object>(page,null, HttpStatus.OK);
    }

    @GetMapping("/search/{search}")
    public ResponseEntity getRecipesBySearch(@PathVariable String search, Pageable pageable) {
        Page<RecipeDto> page = recipeService.findAllRecipesByCriteria(search, pageable);
        return new ResponseEntity<Object>(page,null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createRecipe(@Valid RecipeDto recipeDto) {
        RecipeDto result = recipeService.save(recipeDto);
        return ResponseEntity.created(URI.create("/v1/recipes/" + result.getId())).body(result);
    }

    @PutMapping
    public ResponseEntity updateRecipe(@Valid RecipeDto recipeDto) {
        RecipeDto result = recipeService.update(recipeDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity deleteRecipe(Long id) {
        recipeService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }
}
