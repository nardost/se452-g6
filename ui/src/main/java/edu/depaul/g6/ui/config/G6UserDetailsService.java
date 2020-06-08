package edu.depaul.g6.ui.config;

import edu.depaul.g6.accounts.domain.Account;
import edu.depaul.g6.accounts.domain.Subscriber;
import edu.depaul.g6.accounts.repository.AccountRepository;
import edu.depaul.g6.accounts.repository.SubscriberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Christian Kleinvehn
 */
@Service
@Slf4j
public class G6UserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final SubscriberRepository subscriberRepository;

    @Autowired
    public G6UserDetailsService(
            AccountRepository aRepo,
            SubscriberRepository sRepo) {
        this.accountRepository = aRepo;
        this.subscriberRepository = sRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        Account account = accountRepository.findByEmail(username);
        if (account == null) throw new UsernameNotFoundException(username);

        if (account.getRole().name().equals("ROLE_USER")) {
            // we have more info. we can hand over to a user principal
            Subscriber subscriber = subscriberRepository.findById(account.getId()).get();
            return new G6UserPrincipal(account, subscriber);
        }
        // ADMIN, should have more verification here in reality
        return new G6AdminPrincipal(account);
    }
}
