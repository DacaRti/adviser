package com.spring.program.project.controller.utils;

import com.spring.program.project.entity.Role;
import com.spring.program.project.entity.Tip;
import com.spring.program.project.entity.User;

import java.time.LocalDate;

/**
 * @author DacaP
 * @version 1.0
 */
public interface TestData {

    Role admin = Role.builder().name("admin").build();
    Role user = Role.builder().name("user").build();

    User adminUser = User.builder()
            .username("admin")
            .password("$2a$10$XZKIi39Xwq3miyHh9RPCCe7XbGnTKLum9fl2wnu/EDzJOO2BCIc/S")
            .firstName("admin")
            .lastName("admin")
            .email("admin@gmail.com")
            .birthday(LocalDate.parse("2003-03-03"))
            .role(admin)
            .build();

    User firstUser = User.builder()
            .username("user")
            .password("$2a$10$Sb5nlxDf6Elc5Mh87oO9ZuCM59i4avAUdil.8JghomROi2Q8NyTxK")
            .firstName("user")
            .lastName("user")
            .email("user@gmail.com")
            .birthday(LocalDate.parse("2003-03-03"))
            .role(user)
            .build();

    User secondUser = User.builder()
            .username("user2")
            .password("$2a$10$Sb5nlxDf6Elc5Mh87oO9ZuCM59i4avAUdil.8JghomROi2Q8NyTxK")
            .firstName("user2")
            .lastName("user2")
            .email("user2@gmail.com")
            .birthday(LocalDate.parse("2003-03-03"))
            .role(user)
            .build();

    User userForDelete = User.builder()
            .username("deleteUser")
            .password("deleteUser")
            .firstName("deleteUser")
            .lastName("deleteUser")
            .email("deleteUser@gmail.com")
            .birthday(LocalDate.parse("2003-03-03"))
            .role(user)
            .build();

    User updateUser = User.builder()
            .id(3L)
            .username("updateUser")
            .password("updateUser")
            .firstName("updateUser")
            .lastName("updateUser")
            .email("updateUser@gmail.com")
            .birthday(LocalDate.parse("2003-03-03"))
            .role(user)
            .build();


    User createUser = User.builder()
            .username("userForCreate")
            .password("userForCreate")
            .firstName("userForCreate")
            .lastName("userForCreate")
            .email("userForCreate@gmail.com")
            .birthday(LocalDate.parse("2003-03-03"))
            .role(user)
            .build();

    Tip firstTip = Tip.builder()
            .advice("be happy")
            .build();

    Tip secondTip = Tip.builder()
            .advice("be strong")
            .build();
}
