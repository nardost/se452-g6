package edu.depaul.g6.ui.config;

import java.util.Collections;

import edu.depaul.g6.accounts.domain.Subscriber;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import edu.depaul.g6.accounts.domain.Account;


@Getter
@Setter
public class G6UserPrincipal extends User {
    private final String  accountId;
    private final String  firstName;
    private final String  lastName;
    private final String  lastFourCcDigits;
    private final String  streetAddress;
    private final String  unit;
    private final String  city;
    private final String  state;
    private final Integer zipCode;
    private final String  serviceType;


    public G6UserPrincipal(Account account, Subscriber subscriber) {
        super(account.getEmail(),    // username
              account.getPassword(), // password
              Collections.singleton(new SimpleGrantedAuthority(account.getRole().toString()))); // roles (authorities)

        this.accountId        = account.getId();
        this.firstName        = subscriber.getFirstName();
        this.lastName         = subscriber.getLastName(); final String cc = subscriber.getCreditCardNumber();
        this.lastFourCcDigits = cc.substring(cc.length() - 4);
        this.streetAddress    = subscriber.getStreetAddress();
        this.unit             = subscriber.getUnit();
        this.city             = subscriber.getCity();
        this.state            = subscriber.getState();
        this.zipCode          = subscriber.getZipCode();
        this.serviceType      = subscriber.getServiceType();
    }


    // we don't use any of this logic currently:
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}
