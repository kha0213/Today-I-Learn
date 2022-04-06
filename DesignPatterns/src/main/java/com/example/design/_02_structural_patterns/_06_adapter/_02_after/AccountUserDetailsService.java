package com.example.design._02_structural_patterns._06_adapter._02_after;

import com.example.design._02_structural_patterns._06_adapter._02_after.security.UserDetails;
import com.example.design._02_structural_patterns._06_adapter._02_after.security.UserDetailsService;

public class AccountUserDetailsService implements UserDetailsService {

    private AccountService accountService;

    public AccountUserDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUser(String username) {
        return new AccountUserDetails(accountService.findAccountByUsername(username));
    }
}
