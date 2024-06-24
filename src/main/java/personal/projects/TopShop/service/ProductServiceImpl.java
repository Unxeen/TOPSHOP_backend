package personal.projects.TopShop.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import personal.projects.TopShop.dao.*;
import personal.projects.TopShop.domaine.product.CreateProductRequest;
import personal.projects.TopShop.domaine.product.ProductVo;
import personal.projects.TopShop.domaine.product.SearchProductsRequest;
import personal.projects.TopShop.domaine.product.UpdateProductRequest;
import personal.projects.TopShop.service.exception.BusinessException;
import personal.projects.TopShop.service.models.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements IProductService{

    private ProductRepository productRepository;
    private ClientRepository clientRepository;
    private ProductImageRepository productImageRepository;
    private CategoryRepository categoryRepository;
    private DailyViewRepository dailyViewRepository;
    private ModelMapper modelMapper;



    @Override
    public List<ProductVo> searchProducts(SearchProductsRequest request) {

//        String label = request.getLabel() != null ? request.getLabel() : null;
        Double price1 = request.getPrice1() != null ? request.getPrice1() : 0d;
        Double price2 = request.getPrice2() != null ? request.getPrice2() : 999999999d;
//        String category = request.getCategory() != null ? request.getCategory() : null;
        int index = request.getIndex() != null ? request.getIndex() : 0;
        int size = request.getSize() != null ? request.getSize() : 999999999;


        if(request.getLabel() != null && request.getCategory() == null){

            return productRepository.findAllByLabelContainsIgnoreCaseAndPriceBetween(
                    request.getLabel(),
                    price1,
                    price2,
                    PageRequest.of(
                            index,
                            size,
                            Sort.Direction.ASC,
                            "price")
                    )
                    .stream()
                    .map(product -> modelMapper.map(product, ProductVo.class)
                    )
                    .toList();
        } else if (request.getLabel() == null && request.getCategory() != null) {

            return productRepository.findAllByPriceBetweenAndCategory_Name(
                    price1,
                    price2,
                    request.getCategory(),
                    PageRequest.of(
                            index,
                            size,
                            Sort.Direction.ASC,
                            "price")
                    )
                    .stream()
                    .map(product -> modelMapper.map(product, ProductVo.class)
                    )
                    .toList();

        } else if (request.getLabel() != null && request.getCategory() != null) {

            return productRepository.findAllByLabelContainsIgnoreCaseAndPriceBetweenAndCategory_Name(
                    request.getLabel(),
                    price1,
                    price2,
                    request.getCategory(),
                    PageRequest.of(
                            index,
                            size,
                            Sort.Direction.ASC,
                            "price")
                    )
                    .stream()
                    .map(product -> modelMapper.map(product, ProductVo.class)
                    )
                    .toList();
        }

        return productRepository.findAllByPriceBetween(
                price1,
                price2,
                PageRequest.of(
                        index,
                        size,
                        Sort.Direction.ASC,
                        "price")
                )
                .stream()
                .map(product -> modelMapper.map(product, ProductVo.class)
                )
                .toList();

    }








    @Override
    public ProductVo findById(Long id) {

        return modelMapper.map(
                productRepository.findById(id).orElseThrow(
                        () -> new BusinessException(
                                String.format(
                                        "No product with id %d exists",
                                        id)
                        )
                ),

                ProductVo.class
        );
    }







    @Override
    public ProductVo findByLabel(String label) {

        Product productExist = productRepository.findByLabelIgnoreCase(label).orElseThrow(
                () -> new BusinessException(String.format(
                        "No product with label %s exists",
                        label)
                )
        );
        return modelMapper.map(productExist, ProductVo.class);
    }







    @Override
    public String addView(Long id) {


//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_YEAR, -2);
//        Date today = calendar.getTime();
//        Date today = new Date();
        LocalDate today = LocalDate.now();


        Product product = productRepository.findById(id).orElseThrow(
                () -> new BusinessException(String.format("No product with id:%d exists", id))
        );

        Optional<DailyView> optionalDailyView = dailyViewRepository.findByProduct_IdAndViewDate(id, today);

        if (optionalDailyView.isPresent()){
            DailyView dailyViewExist = optionalDailyView.get();
            dailyViewExist.setViewCount(dailyViewExist.getViewCount() + 1);
            dailyViewRepository.save(dailyViewExist);
        } else {
            DailyView dailyView = DailyView.builder()
                    .viewCount(1)
                    .viewDate(today)
                    .product(product)
                    .build();
            dailyViewRepository.save(dailyView);
        }

        return "Added 1 view";

    }













    @Override
    public String save(CreateProductRequest request) throws IOException {

//        System.out.println(request);
        Client client = clientRepository.findByUsername(
                request.getUsername()).orElseThrow(
                        () -> new BusinessException(
                                String.format(
                                        "No user with username %s exists",
                                        request.getUsername()
                                )
                        )
        );

        Category category = categoryRepository.findByName(
                request.getCat()).orElseThrow(
                        () -> new BusinessException(
                                String.format(
                                        "No %s category exists",
                                        request.getCat()
                                )
                        )
        );

        Product product = modelMapper.map(request, Product.class);

        if (request.getImage() != null){
//            byte[] compressedImage = commonTools.compressImage(request.getImage().getBytes());
            ProductImage image = productImageRepository.save(ProductImage.builder()
                    .type(request.getImage().getContentType())
                    .name(request.getImage().getOriginalFilename())
                    .imageData(Base64.getEncoder().encodeToString(request.getImage().getBytes()))
                    .build());
            product.setImage(image);
        }


        product.setCategory(category);
        product.setOwner(client);


        productRepository.save(product);


        return "Product listed successfully";
    }

    @Override
    public List<ProductVo> getAllByUsername(String username) {

        return productRepository.findAllByOwner_Username(username)
                .stream()
                .map(
                        p -> modelMapper.map(p, ProductVo.class)
                )
                .toList();
    }


    @Override
    public String delete(Long id) {

        Product productExist = productRepository.findById(id).orElseThrow(
                () -> new BusinessException(String.format(
                        "No product with id %s exists",
                        id)
                )
        );
        productRepository.delete(productExist);

        return "Product deleted successfully";
    }







    @Override
    public ProductVo update(Long id, UpdateProductRequest request) {

        Product productExist = productRepository.findById(id).orElseThrow(
                () -> new BusinessException(String.format(
                        "No product with id %s exists",
                        id)
                )
        );

        Category category = categoryRepository.findByName(
                request.getCat()).orElseThrow(
                () -> new BusinessException(
                        String.format(
                                "No %s category exists",
                                request.getCat()
                        )
                )
        );

        productExist.setCategory(category);
        productExist.setPrice(request.getPrice());
        productExist.setLabel(request.getLabel());
        productExist.setDescription(request.getDescription());
        productExist.setStock(request.getStock());



        return modelMapper.map(
                productRepository.save(productExist),
                ProductVo.class
        );
    }
}
