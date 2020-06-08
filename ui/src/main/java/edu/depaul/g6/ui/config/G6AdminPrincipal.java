package edu.depaul.g6.ui.config;

import edu.depaul.g6.accounts.domain.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class G6AdminPrincipal implements UserDetails {

    private final Account account;

    G6AdminPrincipal(Account account) { this.account = account; }


    /**
     *
     * @return A set which contains the mutually-exclusive role of the account.
     * @author Christian Kleinvehn
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(account.getRole().toString()));
    }


    @Override
    public String getPassword() { return account.getPassword(); }
    @Override
    public String getUsername() { return account.getEmail(); }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}
