package com.gestion.customerservice.security;

import com.gestion.customerservice.entities.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Customer customer) {
        this.username = customer.getUsername(); // Assuming your Customer entity has a `username` field
        this.password = customer.getPassword(); // Assuming your Customer entity has a `password` field

        // Adding authorities based on the role of the customer
        List<GrantedAuthority> auths = new ArrayList<>();
        if (customer.getRole() != null) { // Assuming Customer has a `role` field
            auths.add(new SimpleGrantedAuthority(customer.getRole().name())); // Assuming `role` is an enum
        }
        this.authorities = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
