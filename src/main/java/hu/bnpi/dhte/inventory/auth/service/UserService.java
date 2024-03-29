package hu.bnpi.dhte.inventory.auth.service;

import hu.bnpi.dhte.inventory.auth.dtos.InventoryAppRoleDetails;
import hu.bnpi.dhte.inventory.auth.dtos.InventoryAppUserDetails;
import hu.bnpi.dhte.inventory.auth.exceptions.RoleNotFoundException;
import hu.bnpi.dhte.inventory.auth.exceptions.UserNotFoundException;
import hu.bnpi.dhte.inventory.auth.mappers.InventoryAppRoleMapper;
import hu.bnpi.dhte.inventory.auth.mappers.InventoryAppUserMapper;
import hu.bnpi.dhte.inventory.auth.model.InventoryAppRole;
import hu.bnpi.dhte.inventory.auth.model.InventoryAppUser;
import hu.bnpi.dhte.inventory.auth.repository.RoleRepository;
import hu.bnpi.dhte.inventory.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private InventoryAppUserMapper userMapper;

    private InventoryAppRoleMapper roleMapper;

    public InventoryAppUserDetails saveUser(InventoryAppUser user) {
        userRepository.save(user);
        log.info("Saving new user to the database: {}", user.getUsername());
        return userMapper.toInventoryAppUserDetails(user);
    }

    public InventoryAppRoleDetails saveRole(InventoryAppRole role) {
        roleRepository.save(role);
        log.info("Saving new role to the database: {}", role.getName());
        return roleMapper.toInventoryAppRoleDetails(role);
    }

    public InventoryAppUserDetails addRoleToUser(String username, String roleName) {
        InventoryAppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        InventoryAppRole role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException(roleName));
        user.addRole(role);
        log.info("Adding new role, {} to user {}", role.getName(), user.getUsername());
        return userMapper.toInventoryAppUserDetails(user);
    }

    public InventoryAppUserDetails getUser(String username) {
        log.info("Fetching user {}", username);
        InventoryAppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        return userMapper.toInventoryAppUserDetails(user);
    }

    public List<InventoryAppUserDetails> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll().stream()
                .map(user -> userMapper.toInventoryAppUserDetails(user))
                .toList();
    }
}
