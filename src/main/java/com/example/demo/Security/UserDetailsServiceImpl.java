package com.example.demo.Security;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository usersRepository;


    /** Внутрений метод фильтров Security (JWTAuthenticationFilter /attemptAuthentication)
     *  для проверки существования пользователя в БД */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity user = usersRepository.findByLogin(login);

        // Если пользователя не существует или он не подтвердил свой телефонный номер
        if(user == null){
            throw new UsernameNotFoundException(login);
        }

        //Херобора для превращения нашего листа с ролями в Collection <? extends GrantedAuthority>
        //Взял от сюда: https://www.youtube.com/watch?v=m5FAo5Oa6ag&t=3818s время 30:20
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                .collect(Collectors.toList());

        return new User(user.getLogin(), user.getPassword(), authorities);
    }



}
