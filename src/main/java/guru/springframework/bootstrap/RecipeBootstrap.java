package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipe());
    }

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private List<Recipe> getRecipe() {

        List<Recipe> recipes = new ArrayList<>(2);

        // Get UOM
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByUom("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UoM Case #1 not found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByUom("Tablespoon");
        if(!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UoM case #2 not Found!");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByUom("Teaspoon");
        if(!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UoM Case #3 not Found!");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByUom("Dash");
        if(!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UoM Case #4 not Found!");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByUom("Pint");
        if(!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UoM Case #5 not Found!");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByUom("Cup");
        if(!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UoM Case #6 not Found!");
        }

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupUomOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Case #1 not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Case #2 not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.easy);
        guacRecipe.setDirections("This is how it's done, guac");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("This is the guac notes");
        guacRecipe.setNotes(guacNotes);
    //    guacNotes.setRecipe(guacRecipe);
    //    guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("Kosler salt", new BigDecimal(5), teaSpoonUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("Fresh lime or lemon joice", new BigDecimal(2), tableSpoonUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom, guacRecipe));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(5), eachUom, guacRecipe));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);

        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
        tacosRecipe.setPrepTime(9);
        tacosRecipe.setCookTime(20);
        tacosRecipe.setDifficulty(Difficulty.moderate);
        tacosRecipe.setDirections("This is how it's done, tacos");

        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("This is the tacos notes");
        tacosRecipe.setNotes(tacosNotes);
    //    tacosNotes.setRecipe(tacosRecipe);
    //    tacosRecipe.setNotes(tacosNotes);


        tacosRecipe.addIngredient(new Ingredient("Anco Chili powder", new BigDecimal(2), tableSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Clove of Garlic Chopped", new BigDecimal(1), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zset", new BigDecimal(1), tableSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("freshly-squeezed orange juice", new BigDecimal(3), tableSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Olive-oil", new BigDecimal(2), tableSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tableSpoonUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("packed baby aruglua", new BigDecimal(3), cupsUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocodas", new BigDecimal(2), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("radish thinly sliced", new BigDecimal(4), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatos halved", new BigDecimal(4), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("red onions, thinly sliced", new BigDecimal(25), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupsUom, tacosRecipe));
        tacosRecipe.addIngredient(new Ingredient("Lime, cut into wedges", new BigDecimal(4), eachUom, tacosRecipe));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);
        return recipes;

    }
}
