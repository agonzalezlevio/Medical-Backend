package cl.medical.medicalapp.service.implement;

import cl.medical.medicalapp.exception.NotFoundException;
import cl.medical.medicalapp.model.UserAccount;
import cl.medical.medicalapp.repository.UserAccountRepository;
import cl.medical.medicalapp.service.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImplement implements IUserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountServiceImplement(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public List<UserAccount> findAll() {
        return this.userAccountRepository.findAll();
    }

    @Override
    public UserAccount findById(Integer id) {
        Optional<UserAccount> optionalUserAccount = this.userAccountRepository.findById(id);
        if (optionalUserAccount.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        return optionalUserAccount.get();
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        return this.userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount update(Integer id, UserAccount userAccount) {
        Optional<UserAccount> optionalUserAccount = this.userAccountRepository.findById(id);
        if (optionalUserAccount.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        UserAccount userAccountUpdated = optionalUserAccount.get();
        userAccountUpdated.setUsername(userAccount.getUsername());
        userAccountUpdated.setPassword(userAccount.getPassword());
        return this.userAccountRepository.save(userAccountUpdated);
    }

    @Override
    public void deleteById(Integer id) {
        Optional<UserAccount> optionalUserAccount = this.userAccountRepository.findById(id);
        if (optionalUserAccount.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        this.userAccountRepository.deleteById(id);
    }


}
