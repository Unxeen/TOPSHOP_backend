package personal.projects.TopShop.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Component
@Data
public class CommonTools {

    @Value("${graphql.date.format}")
    private String dateFormat;

    public String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }

    public Date stringToDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        System.out.println(formatter.parse(date) + "inside string to date tools");
        return formatter.parse(date);
    }

}
