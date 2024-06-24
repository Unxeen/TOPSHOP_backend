package personal.projects.TopShop.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import personal.projects.TopShop.dao.*;
import personal.projects.TopShop.domaine.client.ClientVo;
import personal.projects.TopShop.domaine.client.CreateClientRequest;
import personal.projects.TopShop.domaine.client.UpdateClientRequest;
import personal.projects.TopShop.domaine.user.TokenVo;
import personal.projects.TopShop.jwt.JwtUtils;
import personal.projects.TopShop.service.exception.BusinessException;
import personal.projects.TopShop.service.models.Client;
import personal.projects.TopShop.service.models.ProductImage;
import personal.projects.TopShop.service.models.Role;
import personal.projects.TopShop.service.models.UserImage;

import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class ClientServiceImpl implements IClientService{

    private ClientRepository clientRepository;
    private UserImageRepository userImageRepository;
    private UserDetailsService userDetailsService;
    private JwtUtils jwtUtils;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<ClientVo> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(
                        client -> modelMapper.map(client, ClientVo.class)
                )
                .toList();
    }

    @Override
    public String createClient(CreateClientRequest request) {

        clientRepository.findByUsername(request.getUsername())
                .ifPresent(
                        (client) -> {
                            throw new BusinessException(
                                    String.format(
                                            "Client with username %s already exists",
                                            request.getUsername()
                                    )
                            );
                        }
                );

        Client clientNew = modelMapper.map(request, Client.class);
        clientNew.setPassword(passwordEncoder.encode(request.getPassword()));
        clientNew.setAccountNonExpired(true);
        clientNew.setEnabled(true);
        clientNew.setAccountNonLocked(true);
        clientNew.setCredentialsNonExpired(true);

        Role clientRole = roleRepository.findByAuthority("ROLE_CLIENT");
        clientNew.setAuthorities(List.of(clientRole));

        clientRepository.save(clientNew);

        return String.format(
                "Client %s was registered successfully",
                clientNew.getUsername()
        );
    }


    @Override
    public ClientVo getClientByUsername(String username) {

        Client client = clientRepository.findByUsername(username)
                .orElseThrow(
                        () -> new BusinessException(
                                String.format(
                                        "No client with username %s exists",
                                        username
                                )
                        )
                );


        ClientVo clientVo = modelMapper.map(
                client,
                ClientVo.class
        );

//        clientVo.getImage().setImageData(Base64.getEncoder().encodeToString(client.getImage().getImageData()));

//        System.out.println(Arrays.toString(client.getImage().getImageData()));
//        System.out.println("--------------------------------------------------");
//        System.out.println(clientVo.getImage().getImageData());

        return clientVo;
    }


    @Override
    public ClientVo getClientByIdentity(String identity) {

        Client client = clientRepository.findByIdentityRef(identity)
                .orElseThrow(
                        () -> new BusinessException(
                                String.format(
                                        "No client with identityRef %s exists",
                                        identity
                                )
                        )
                );

        return modelMapper.map(client, ClientVo.class);
    }

    @Override
    public String deleteClientByIdentity(String identity) {

        Client client = clientRepository.findByIdentityRef(identity)
                .orElseThrow(
                        () -> new BusinessException(
                                String.format(
                                        "No client with identityRef %s exists",
                                        identity
                                )
                        )
                );
        clientRepository.delete(client);

        return String.format(
                "Client %s deleted successfully",
                client.getUsername()
        );
    }

    @Override
    public TokenVo updateClient(String username, UpdateClientRequest request) throws IOException {

        //Check existence
        Client client = clientRepository.findByUsername(username)
                .orElseThrow(
                        () -> new BusinessException(
                                String.format(
                                        "No client with username %s exists",
                                        username
                                )
                        )
                );




        // Update values if they exist in request
        if (request.getUsername() != null)
            client.setUsername(request.getUsername());
        if (request.getFirstname() != null)
            client.setFirstname(request.getFirstname());
        if (request.getLastname() != null)
            client.setLastname(request.getLastname());
        if (request.getEmail() != null)
            client.setEmail(request.getEmail());
        if (request.getPostalAddress() != null)
            client.setPostalAddress(request.getPostalAddress());


        // Update image if exist
        Optional<UserImage> imageExist = userImageRepository.getByUsername(username);
        if (request.getImage() != null){

            if (imageExist.isPresent()){
                UserImage imageReal = imageExist.get();
                imageReal.setImageData(request.getImage().getBytes());
                imageReal.setName(request.getImage().getOriginalFilename());
                imageReal.setType(request.getImage().getContentType());
                imageReal.setUsername(client.getUsername());
                UserImage imageNew = userImageRepository.save(imageReal);
                client.setImage(imageNew);
            } else {
                UserImage image = userImageRepository.save(UserImage.builder()
                        .type(request.getImage().getContentType())
                        .name(request.getImage().getOriginalFilename())
                        .imageData(request.getImage().getBytes())
                        .username(client.getUsername())
                        .build());
                client.setImage(image);
            }
        } else if (request.getImageReset()){

            if (imageExist.isPresent()){
                UserImage imageReal = imageExist.get();
                imageReal.setName("");
                imageReal.setType("");
                imageReal.setImageData(null);
                imageReal.setUsername(client.getUsername());
                UserImage imageNew = userImageRepository.save(imageReal);
                client.setImage(imageNew);
            }
        }

        clientRepository.save(client);


        // Refresh authentication
        UserDetails userDetails = userDetailsService.loadUserByUsername(client.getUsername());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        Object details = authentication.getDetails();
        authentication.setDetails(details);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        // Regenerate JWT Token
        String newJwt = jwtUtils.generateJwtToken(authentication);



        return TokenVo.builder()
                .jwtToken(newJwt)
                .roles(authorities.stream().map(GrantedAuthority::getAuthority).toList())
                .username(userDetails.getUsername())
                .build();
    }
}
