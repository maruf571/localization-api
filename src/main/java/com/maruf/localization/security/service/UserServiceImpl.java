package com.maruf.localization.security.service;

import com.maruf.localization.security.entity.RoleName;
import com.maruf.localization.security.entity.User;
import com.maruf.localization.security.repository.RoleRepository;
import com.maruf.localization.security.repository.UserRepository;
import com.maruf.localization.tenant.entity.Tenant;
import com.maruf.localization.tenant.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final TenantRepository tenantRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for the email " + email));
    }

    @Override
    @Transactional
    public User signUp(User user) {

        //check valid email
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher =  pattern.matcher(user.getEmail());
        if(!matcher.matches()){
            throw new EntityNotFoundException("Invalid email");
        }

        //check is email exist on our db
        Optional<User> found = userRepository.findByEmail(user.getEmail());
        if(found.isPresent()){
            throw new EntityNotFoundException("Email already exist");
        }

        //check empty password
        if(user.getPassword() == null || user.getPassword().isEmpty()){
            throw new EntityNotFoundException("Password is blank");
        }

        //create tenant
        Tenant tenant = new Tenant();
        tenant.setName(user.getFirstName() + " " + user.getLastName());
        tenantRepository.save(tenant);

        //encrypt password
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        //set admin role
        user.setRoles(roleRepository.findRolesByName(RoleName.ROLE_ADMIN));

        //create user
        user.setTenant(tenant.getId());
        return userRepository.save(user);
    }
}
