package com.ikenvanter.envanter.security;

import com.ikenvanter.envanter.entity.Kullanici;
import com.ikenvanter.envanter.repo.KullaniciRepo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class KullaniciDetayService implements UserDetailsService {

    @Autowired
    private KullaniciRepo kullaniciRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Kullanici kullanici = kullaniciRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + username));
        return new org.springframework.security.core.userdetails.User(kullanici.getUsername(), kullanici.getPassword(), new ArrayList<>());
    }
}
