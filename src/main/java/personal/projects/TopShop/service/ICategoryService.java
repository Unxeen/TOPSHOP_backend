package personal.projects.TopShop.service;

import personal.projects.TopShop.domaine.category.CategoryVo;

import java.util.List;

public interface ICategoryService {
    String saveCategory(String category);
    List<CategoryVo> getAll();
    CategoryVo getByName(String name);
}
