package cl.medical.medicalapp.repository;

import cl.medical.medicalapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findOneByUsername(String username);

}
