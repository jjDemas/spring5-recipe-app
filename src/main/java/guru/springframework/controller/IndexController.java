package guru.springframework.controller;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Optional;

@Controller
public class IndexController {

    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
//    private CategoryRepository categoryRepository;
//    private UnitOfMeasureRepository unitOfMeasureRepository;

//    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
//        this.categoryRepository = categoryRepository;
//        this.unitOfMeasureRepository = unitOfMeasureRepository;
//    }

    @RequestMapping({"", "/", "/index"})
//    private String getIndexPage() {
    private String getIndexPage(Model model) {

        model.addAttribute("recipes", recipeService.getRecipes());
//        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");//       Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByUom("Teaspoon");

//        System.out.println("Category Id is: " + categoryOptional.get().getId());
//        System.out.println("UnitOfMeasure Id is " + unitOfMeasureOptional.get().getId() );

//        System.out.println("In getIndexPage: Some message to say 12343335555");
        return "index";
    }


}
