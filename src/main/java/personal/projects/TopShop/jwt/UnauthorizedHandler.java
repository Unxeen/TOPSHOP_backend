package personal.projects.TopShop.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(UnauthorizedHandler.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        logger.error("Unauthorized error: {}", authException.getMessage());
        if(Objects.equals(response.getHeader("jwtStatus"), "expired")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("session expired");
            return;
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not unauthorized");


    }
}
