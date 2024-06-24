package personal.projects.TopShop.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import personal.projects.TopShop.dao.RoleRepository;
import personal.projects.TopShop.dao.SupportRepository;
import personal.projects.TopShop.domaine.support.CreateSupportRequest;
import personal.projects.TopShop.domaine.support.SupportVo;
import personal.projects.TopShop.domaine.support.UpdateSupportRequest;
import personal.projects.TopShop.service.exception.BusinessException;
import personal.projects.TopShop.service.models.Role;
import personal.projects.TopShop.service.models.Support;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class SupportServiceImpl implements ISupportService{

    private SupportRepository supportRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;

    @Override
    public List<SupportVo> getAllSupports() {

        return supportRepository.findAll()
                .stream()
                .map(
                        support -> modelMapper.map(support, SupportVo.class)
                )
                .toList();

    }

    @Override
    public String createSupport(CreateSupportRequest request) {

        supportRepository.findByUsername(request.getUsername())
                .ifPresent(
                        (support) -> {
                            throw new BusinessException(
                                    String.format(
                                            "Support with username %s already exists",
                                            request.getUsername()
                                    )
                            );
                        }
                );

        Support supportNew = modelMapper.map(request, Support.class);

        supportNew.setAccountNonExpired(true);
        supportNew.setEnabled(true);
        supportNew.setAccountNonLocked(true);
        supportNew.setCredentialsNonExpired(true);

        Role supportRole = roleRepository.findByAuthority("ROLE_SUPPORT");
        supportNew.setAuthorities(List.of(supportRole));

        supportRepository.save(supportNew);

        return String.format(
                "Support %s was registered successfully",
                supportNew.getUsername()
        );

    }

    @Override
    public SupportVo getSupportByIdentity(String identity) {
        return null;
    }

    @Override
    public String deleteSupportByIdentity(String identity) {
        return null;
    }

    @Override
    public String updateSupport(String identity, UpdateSupportRequest request) {
        return null;
    }
}
