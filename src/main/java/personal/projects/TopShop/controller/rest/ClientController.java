package personal.projects.TopShop.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import personal.projects.TopShop.domaine.client.ClientVo;
import personal.projects.TopShop.domaine.client.UpdateClientRequest;
import personal.projects.TopShop.domaine.user.TokenVo;
import personal.projects.TopShop.service.IClientService;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/client")
@PreAuthorize("hasAnyRole('CLIENT', 'ADMIN', 'SUPPORT')")
public class ClientController {

    private IClientService clientService;


    @GetMapping("/{username}")
    public ResponseEntity<ClientVo> getClientByUsername(@PathVariable String username){

        return new ResponseEntity<>(
                clientService.getClientByUsername(username),
                HttpStatus.OK
        );
    }



    @PutMapping("/update/{username}")
    public ResponseEntity<TokenVo> updateClient(@PathVariable String username,
                                                @ModelAttribute UpdateClientRequest request) throws IOException {

        return new ResponseEntity<>(
                clientService.updateClient(username, request),
                HttpStatus.OK
        );

    }

}
