package personal.projects.TopShop.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import personal.projects.TopShop.dao.CategoryRepository;
import personal.projects.TopShop.domaine.category.CategoryVo;
import personal.projects.TopShop.service.exception.BusinessException;
import personal.projects.TopShop.service.models.Category;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService{

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;


    @Override
    public String saveCategory(String name) {

        categoryRepository.findByName(name)
                .ifPresent(
                        category -> {
                            throw new BusinessException(
                                    String.format(
                                            "Category %s already exists",
                                            name
                                    )
                            );
                        }
                );

        categoryRepository.save(
                Category.builder()
                        .name(name)
                        .build()
        );

        return "Category saved successfully";

    }

    @Override
    public List<CategoryVo> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(
                        category -> modelMapper.map(category, CategoryVo.class)
                )
                .toList();
    }

    @Override
    public CategoryVo getByName(String name) {
        return modelMapper.map(
                categoryRepository.findByName(name)
                        .orElseThrow(
                                ()-> new BusinessException(
                                        String.format(
                                                "Category %s doesn't exist",
                                                name
                                        )
                                )
                        ),
                CategoryVo.class);
    }
}
