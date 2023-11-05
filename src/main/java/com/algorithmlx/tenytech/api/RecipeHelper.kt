package com.algorithmlx.tenytech.api

import com.google.common.collect.ImmutableMap
import net.minecraft.inventory.IInventory
import net.minecraft.item.crafting.IRecipe
import net.minecraft.item.crafting.IRecipeType
import net.minecraft.item.crafting.RecipeManager
import net.minecraftforge.client.event.RecipesUpdatedEvent
import net.minecraftforge.event.AddReloadListenerEvent
import net.minecraftforge.eventbus.api.EventPriority
import net.minecraftforge.eventbus.api.SubscribeEvent

object RecipeHelper {
    private lateinit var recipeManager: RecipeManager

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun addReloadListener(evt: AddReloadListenerEvent) {
        recipeManager = evt.dataPackRegistries.recipeManager
    }
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun recipeUpdated(evt: RecipesUpdatedEvent) {
        recipeManager = evt.recipeManager
    }

    @JvmStatic
    fun getRecipeManager(): RecipeManager {
        if (recipeManager.recipes is ImmutableMap<*, *>) {
            recipeManager.recipes = HashMap(recipeManager.recipes)
            recipeManager.recipes.replaceAll { a, _ -> HashMap(recipeManager.recipes[a]) }
        }
        return recipeManager
    }

    @JvmStatic
    fun getRecipes() = this.getRecipeManager().recipes

    @JvmStatic
    fun <C: IInventory, X: IRecipe<C>> getRecipes(recipe: IRecipeType<X>) = this.getRecipeManager().byType(recipe)

    fun addRecipe(recipe: IRecipe<*>) {
        this.getRecipeManager().recipes.computeIfAbsent(recipe.type) { hashMapOf() }.put(recipe.getId(), recipe)
    }
}