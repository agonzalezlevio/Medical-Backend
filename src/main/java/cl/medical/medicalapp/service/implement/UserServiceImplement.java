package cl.medical.medicalapp.service.implement;

import cl.medical.medicalapp.entity.UserEntity;
import cl.medical.medicalapp.exception.NotFoundException;
import cl.medical.medicalapp.repository.IUserRepository;
import cl.medical.medicalapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserServiceImplement(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity findById(Integer id) {
        Optional<UserEntity> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        return optionalUser.get();
    }

    @Override
    @Transactional
    public UserEntity save(UserEntity user) {
        return this.userRepository.save(user);
    }

    @Override
    @Transactional
    public UserEntity update(Integer id, UserEntity user) {
        Optional<UserEntity> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        UserEntity userUpdated = optionalUser.get();
        userUpdated.setUsername(user.getUsername());
        userUpdated.setPassword(user.getPassword());
        return this.userRepository.save(userUpdated);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Optional<UserEntity> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("exception.entityId.text.notFound");
        }
        this.userRepository.deleteById(id);
    }


}
