package personal.projects.TopShop.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import personal.projects.TopShop.dao.ClientRepository;
import personal.projects.TopShop.dao.LineProductRepository;
import personal.projects.TopShop.dao.ProductRepository;
import personal.projects.TopShop.domaine.cart.CreateLineProductRequest;
import personal.projects.TopShop.domaine.cart.LineProductVo;
import personal.projects.TopShop.domaine.cart.UpdateLineProductRequest;
import personal.projects.TopShop.service.exception.BusinessException;
import personal.projects.TopShop.service.models.Client;
import personal.projects.TopShop.service.models.LineProduct;
import personal.projects.TopShop.service.models.Product;

import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class LineProductServiceImpl implements ILineProductService{

    private LineProductRepository lineProductRepository;
    private ClientRepository clientRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Override
    public List<LineProductVo> getAllByUsername(String username) {

        return lineProductRepository.findAllByClient_Username(username)
                .stream()
                .map(
                        lineProduct -> modelMapper.map(lineProduct, LineProductVo.class)
                )
                .toList();
    }




    @Override
    public LineProductVo getById(Long id) {

        return modelMapper.map(
                lineProductRepository.findById(id).orElseThrow(
                        () -> new BusinessException(String.format("No lp with id:%s exists", id))
                ),
                LineProductVo.class
        );
    }





    @Override
    public String saveLP(CreateLineProductRequest request) {

        lineProductRepository.findByProduct_IdAndClient_Username(request.getProductId(), request.getUsername())
                .ifPresent(
                        p -> {
                            throw new BusinessException("Item already in cart");
                        }
                );



        Client clientExist = clientRepository.findByUsername(request.getUsername())
                .orElseThrow(
                        () -> new BusinessException(String.format(
                                "No client with username: %s exists",
                                request.getUsername()
                        )
                        )
                );


        Product productExists = productRepository.findById(request.getProductId())
                .orElseThrow(
                        () -> new BusinessException("No such product exists")
                );

        System.out.println(productExists.getStock());
        System.out.println(request.getQuantity());

        if (request.getQuantity() > productExists.getStock()){
            throw new BusinessException("Stock is less than selected quantity");
        }


        lineProductRepository.save(LineProduct.builder()
                        .product(productExists)
                        .quantity(request.getQuantity())
                        .client(clientExist)
                        .totalPrice(productExists.getPrice() * request.getQuantity())
                        .build());

        return "LP saved successfully";
    }


    @Override
    public String updateLP(UpdateLineProductRequest request, Long id) {

        LineProduct lpExists = lineProductRepository.findById(id).orElseThrow(
                () -> new BusinessException("No such item exists")
        );

        Product productExists = productRepository.findById(lpExists.getProduct().getId()).get();

        if (request.getQuantity() > productExists.getStock()){
            throw new BusinessException("Stock is less than selected quantity");
        }

        Double unitPrice = lpExists.getProduct().getPrice();

        lpExists.setQuantity(request.getQuantity());
        lpExists.setTotalPrice(request.getQuantity() * unitPrice);

        lineProductRepository.save(lpExists);

        return "LP updated successfully";
    }

    @Override
    public String deleteLP(Long id) {

        lineProductRepository.findById(id).orElseThrow(
                () -> new BusinessException("No such item exists")
        );

        lineProductRepository.deleteById(id);

        return "LP deleted successfully";
    }
}
