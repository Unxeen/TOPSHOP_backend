package personal.projects.TopShop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import personal.projects.TopShop.common.CommonTools;
import personal.projects.TopShop.dao.ProductImageRepository;
import personal.projects.TopShop.service.models.ProductImage;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements IImageService{

    private ProductImageRepository productImageRepository;
    private CommonTools commonTools;

    @Override
    public byte[] getImageByName(String name) {
        ProductImage image = productImageRepository.getByName(name).get();

        return null;
    }
}
