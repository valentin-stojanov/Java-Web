package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.user.UserRegisterDto;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.mapper.UserMapper;
import bg.softuni.mobilele.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void registerAndLogin(UserRegisterDto userRegisterDto) {
        UserEntity newUser = this.userMapper.userDtoToUserEntity(userRegisterDto);
        newUser.setPassword(this.passwordEncoder.encode(userRegisterDto.getPassword()));


        newUser = this.userRepository.save(newUser);
        login(newUser);

    }


    public void login(UserEntity userEntity) {
        //todo
    }

}
