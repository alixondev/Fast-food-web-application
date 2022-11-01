package com.example.appauthservice.component;

import com.example.appauthservice.repository.RoleRepository;
import com.example.appauthservice.repository.UserRepository;
import com.example.appdbservice.entity.enums.LanguageEnum;
import com.example.appdbservice.entity.enums.Permissions;
import com.example.appdbservice.entity.users.Role;
import com.example.appdbservice.entity.users.User;
import com.example.appdbservice.utils.AppConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;


    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            createSuperUser();
            createUserRoler();
        }
    }

    public void createSuperUser() {
        Role adminRole = new Role(
                AppConstant.ADMIN_ROLE,
                Set.of(Permissions.values())
        );
        roleRepository.save(adminRole);

        User adminUser = new User(
                "Admin",
                "Admin",
                "+998900050161",
                LanguageEnum.UZ,
                adminRole
        );
        adminUser.setEnabled(true);
        userRepository.save(adminUser);
    }


    private void createUserRoler() {
        Role role = new Role(
                AppConstant.USER_ROLE,
                Set.of(Permissions.VIEW_PRODUCT,
                        Permissions.VIEW_SALE
                ));
        roleRepository.save(role);
    }
}
