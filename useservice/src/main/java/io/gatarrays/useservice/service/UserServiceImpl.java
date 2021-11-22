package io.gatarrays.useservice.service;

import io.gatarrays.useservice.entites.Role;
import io.gatarrays.useservice.entites.User;
import io.gatarrays.useservice.repository.RoleRepository;
import io.gatarrays.useservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public User saveUser(User user) {
        log.info("salvando novos usuarios {} no banco de dados",user.getName());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("salvando novos cargos {} no banco de dados",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
      log.info("adicionando cargos {} no usuario {}",roleName,username);
        User user= userRepository.findByUserName(username);
      Role role= roleRepository.findByName(roleName);
      user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("buscando usuario por username {}",username);
        User user=userRepository.findByUserName(username);
        return user;
    }

    @Override
    public List<User> getUsers() {
        log.info("listando todos os usuarios");
        return userRepository.findAll();
    }
}
