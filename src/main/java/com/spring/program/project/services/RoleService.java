package com.spring.program.project.services;

import com.spring.program.project.entity.Role;
import com.spring.program.project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author DacaP
 * @version 1.0
 */
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleDao) {
        this.roleRepository = roleDao;
    }

    public void create(Role role) {
        roleRepository.save(role);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> findById(long id) {
        return roleRepository.findById(id);
    }

    public void update(Role role) {
        roleRepository.save(role);
    }

    public void remove(long id) {
        roleRepository.deleteById(id);
    }

    public Role findByName(String name) {
        Role role = null;
        Optional<Role> optional = roleRepository.findByName(name);
        if (optional.isPresent()) {
            role = optional.get();
        }
        return role;
    }
}
