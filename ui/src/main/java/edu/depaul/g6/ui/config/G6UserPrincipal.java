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
    private String firstName;
    private String lastName;
    private String lastFourCcDigits;
    private String streetAddress;
    private String unit;
    private String city;
    private String state;
    private Integer zipCode;
    private String serviceType;


    public G6UserPrincipal(Account account, Subscriber subscriber) {
        super(account.getEmail(),
              account.getPassword(),
              Collections.singleton(new SimpleGrantedAuthority(account.getRole().toString())));

        this.firstName        = subscriber.getFirstName();
        this.lastName         = subscriber.getLastName(); final String cc = subscriber.getCreditCardNumber();
        this.lastFourCcDigits = cc.substring(cc.length() - 5);
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
