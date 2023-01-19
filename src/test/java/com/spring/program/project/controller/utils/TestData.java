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

    User adminPerson = User.builder()
            .username("admin")
            .password("$2a$10$XZKIi39Xwq3miyHh9RPCCe7XbGnTKLum9fl2wnu/EDzJOO2BCIc/S")
            .firstName("admin")
            .lastName("admin")
            .email("admin@gmail.com")
            .birthday(LocalDate.parse("2003-03-03"))
            .role(admin)
            .build();

    User userPerson = User.builder()
            .username("user")
            .password("$2a$10$Sb5nlxDf6Elc5Mh87oO9ZuCM59i4avAUdil.8JghomROi2Q8NyTxK")
            .firstName("user")
            .lastName("user")
            .email("user@gmail.com")
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
