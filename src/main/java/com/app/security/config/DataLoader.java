package com.app.security.config;

import com.app.entity.AppUser;
import com.app.entity.AppUserRole;
import com.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {


    private  UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String pass = bCryptPasswordEncoder.encode("pass");

        String pass2 = bCryptPasswordEncoder.encode("pass2");

        userRepository.save(new AppUser("NedStark", "stark50", "stark@gmail.com", pass, AppUserRole.ADMIN));

        userRepository.save(new AppUser("Targaryen", "dragon1", "dragon@gmail.com", pass2, AppUserRole.USER));
    }

}
