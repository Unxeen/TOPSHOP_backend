package personal.projects.TopShop.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import personal.projects.TopShop.dao.*;
import personal.projects.TopShop.domaine.user.PermissionVo;
import personal.projects.TopShop.domaine.user.RoleVo;
import personal.projects.TopShop.domaine.user.UserVo;
import personal.projects.TopShop.service.models.Permission;
import personal.projects.TopShop.service.models.Role;
import personal.projects.TopShop.service.models.User;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements IUserService{

    private RoleRepository roleRepository;
    private PermissionRepository permissionRepository;
    private UserRepository userRepository;
    private ClientRepository clientRepository;
    private SupportRepository supportRepository;
    private ModelMapper modelMapper;

    @Override
    public RoleVo getRoleByAuthority(String authority) {
        return modelMapper.map(
                roleRepository.findByAuthority(authority),
                RoleVo.class
        );
    }

    @Override
    public PermissionVo getPermissionByAuthority(String authority) {
        return modelMapper.map(
                permissionRepository.findByAuthority(authority),
                PermissionVo.class
        );
    }

    @Override
    public RoleVo saveRole(RoleVo roleVo) {
        Role role = modelMapper.map(roleVo, Role.class);
        role.setAuthorities(
                roleVo.getAuthorities()
                        .stream()
                        .map(
                                permissionVo -> permissionRepository.findByAuthority(permissionVo.getAuthority())
                        )
                        .toList()
        );

        return modelMapper.map(roleRepository.save(role), RoleVo.class);
    }

    @Override
    public PermissionVo savePermission(PermissionVo permissionVo) {
        Permission permission = modelMapper.map(
                permissionVo,
                Permission.class
        );

        return modelMapper.map(
                permissionRepository.save(permission),
                PermissionVo.class
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        System.out.println("inside load user");
//        User user =userRepository.findByUsername(username);
//        System.out.println(user.getImage());
        UserVo userVo = modelMapper.map(
                userRepository.findByUsername(username),
                UserVo.class
        );

//        System.out.println("After model mapping user");

        List<RoleVo> authorities = new ArrayList<>();
        userVo.getAuthorities().forEach((role) -> {
            role.getAuthorities().forEach((permission) -> {
                authorities.add(RoleVo.builder()
                        .authority(permission.getAuthority())
                        .build());
            });
        });
        userVo.getAuthorities().addAll(authorities);

        return userVo;
    }
}
