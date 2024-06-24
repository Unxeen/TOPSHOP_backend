package personal.projects.TopShop.config;

import lombok.AllArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import personal.projects.TopShop.common.CommonTools;
import org.modelmapper.ModelMapper;
import personal.projects.TopShop.domaine.user.UserImageVo;
import personal.projects.TopShop.service.exception.BusinessException;
import personal.projects.TopShop.service.models.UserImage;

import java.text.ParseException;
import java.util.Base64;
import java.util.Date;

@Configuration
@AllArgsConstructor
public class ModelMapperConfig {

    private CommonTools tools;

    @Bean
    public ModelMapper modelMapper(){

        ModelMapper model = new ModelMapper();

        model.getConfiguration().
                setMatchingStrategy(MatchingStrategies.LOOSE).
                setFieldMatchingEnabled(true).
                setSkipNullEnabled(true).
                setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        Converter<String, Date> stringToDateConverter = new AbstractConverter<>() {
            @Override
            protected Date convert(String string) {

                try {

                    return tools.stringToDate(string);

                } catch (ParseException e) {

                    throw new BusinessException(
                            String.format(
                                    "the date %s doesn't respect the format %s ",
                                    string,
                                    tools.getDateFormat()
                            )
                    );

                }
            }
        };

        Converter<Date, String> dateToStringConverter = new AbstractConverter<>() {
            @Override
            protected String convert(Date date) {
                return tools.dateToString(date);
            }
        };


        Converter<UserImage, UserImageVo> baseImageToDisplayImageConverter = new AbstractConverter<UserImage, UserImageVo>() {
            @Override
            protected UserImageVo convert(UserImage userImage) {

                if (userImage != null){
                    String name = userImage.getName() != null ? userImage.getName()  : "";
                    String type = userImage.getType() != null ? userImage.getType()  : "";
                    String imgData = userImage.getImageData() != null ? Base64.getEncoder().encodeToString(userImage.getImageData())  : null;
                    return UserImageVo.builder()
                            .id(userImage.getId())
                            .name(name)
                            .type(type)
                            .imageData(imgData)
                            .build();
                }
                return null;
            }
        };


        model.addConverter(stringToDateConverter);
        model.addConverter(dateToStringConverter);
        model.addConverter(baseImageToDisplayImageConverter);

        return model;
    }
}
