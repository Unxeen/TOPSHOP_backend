package personal.projects.TopShop.controller.auth;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.projects.TopShop.domaine.client.CreateClientRequest;
import personal.projects.TopShop.domaine.user.TokenVo;
import personal.projects.TopShop.domaine.user.UserRequest;
import personal.projects.TopShop.jwt.JwtUtils;
import personal.projects.TopShop.service.IClientService;
import personal.projects.TopShop.service.ISupportService;
import personal.projects.TopShop.service.IUserService;
import personal.projects.TopShop.service.exception.BusinessException;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private IUserService userService;
    private IClientService clientService;
    private ModelMapper modelMapper;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<TokenVo> login(@RequestBody UserRequest request){

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
        } catch (AuthenticationException e) {
            throw new BusinessException("Incorrect username or password");
        }

        String jwtToken = jwtUtils.generateJwtToken(authentication);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        return new ResponseEntity<>(
                TokenVo.builder()
                        .jwtToken(jwtToken)
                        .roles(authorities.stream().map(GrantedAuthority::getAuthority).toList())
                        .username(request.username())
                        .build(),

                HttpStatus.OK
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody CreateClientRequest request){
        return new ResponseEntity<>(
                clientService.createClient(request),
                HttpStatus.CREATED
        );
    }

}
