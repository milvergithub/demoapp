package com.milver.service.dto;

import javax.validation.constraints.NotNull;

public class RecipeDto {

    private Long id;

    @NotNull
    private String nameRecipe;

    @NotNull
    private String descriptionRecipe;

    @NotNull
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameRecipe() {
        return nameRecipe;
    }

    public void setNameRecipe(String nameRecipe) {
        this.nameRecipe = nameRecipe;
    }

    public String getDescriptionRecipe() {
        return descriptionRecipe;
    }

    public void setDescriptionRecipe(String descriptionRecipe) {
        this.descriptionRecipe = descriptionRecipe;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
