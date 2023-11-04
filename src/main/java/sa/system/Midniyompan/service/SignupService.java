package sa.system.Midniyompan.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sa.system.Midniyompan.entity.Account;
import sa.system.Midniyompan.model.SignupRequest;
import sa.system.Midniyompan.repository.AccountRepository;


@Service
public class SignupService {


    @Autowired
    private AccountRepository repository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public boolean isUsernameAvailable(String username) {
        return repository.findByUsername(username) == null;
    }


    public void createManger(SignupRequest user) {
        Account record = modelMapper.map(user, Account.class);
        record.setRole("ROLE_MANAGER");


        String hashedPassword = passwordEncoder.encode(user.getPassword());
        record.setPassword(hashedPassword);


        repository.save(record);
    }


    public Account getUser(String username) {
        return repository.findByUsername(username);
    }
}

