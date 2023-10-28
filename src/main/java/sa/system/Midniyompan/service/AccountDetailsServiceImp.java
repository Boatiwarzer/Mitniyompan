package sa.system.Midniyompan.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sa.system.Midniyompan.entity.Account;
import sa.system.Midniyompan.repository.AccountRepository;


import java.util.ArrayList;
import java.util.List;


@Service
public class AccountDetailsServiceImp implements UserDetailsService {


    @Autowired
    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {


        Account account = accountRepository.findByUsername(username);


        if (account == null) {
            throw new UsernameNotFoundException("Could not find user");
        }


        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole()));


        return new org.springframework.security.core.userdetails.User(
                account.getUsername(), account.getPassword(), authorities);
    }
}

