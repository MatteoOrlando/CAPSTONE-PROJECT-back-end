package MatteoOrlando.CapStone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import MatteoOrlando.CapStone.dto.CategoryDTO;
import MatteoOrlando.CapStone.exceptions.BadRequestException;
import MatteoOrlando.CapStone.exceptions.NotFoundException;
import MatteoOrlando.CapStone.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/name/{name}")
    public CategoryDTO getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createCategory(@RequestBody @Validated CategoryDTO categoryDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid category data provided.");
        }
        return categoryService.createCategory(categoryDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody @Validated CategoryDTO categoryDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid category data provided.");
        }
        return categoryService.updateCategory(id, categoryDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}

