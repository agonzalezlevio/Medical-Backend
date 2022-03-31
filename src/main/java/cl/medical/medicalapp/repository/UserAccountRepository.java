package cl.medical.medicalapp.repository;

import cl.medical.medicalapp.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    UserAccount findOneByUsername(String username);

}
