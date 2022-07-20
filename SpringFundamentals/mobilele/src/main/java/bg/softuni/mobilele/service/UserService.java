package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.user.UserRegisterDto;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.mapper.UserMapper;
import bg.softuni.mobilele.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EmailService emailService;

    private final UserDetailsService userDetailsService;

    public UserService(PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       UserMapper userMapper,
                       EmailService emailService, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.emailService = emailService;
        this.userDetailsService = userDetailsService;
    }

    public void registerAndLogin(UserRegisterDto userRegisterDto) {
        UserEntity newUser = this.userMapper.userDtoToUserEntity(userRegisterDto);
        newUser.setPassword(this.passwordEncoder.encode(userRegisterDto.getPassword()));


        newUser = this.userRepository.save(newUser);
        login(newUser);
        this.emailService.sendRegistrationEmail(newUser.getEmail(),
                newUser.getFirstName() + " " + newUser.getLastName());

    }


    public void login(UserEntity userEntity) {
        UserDetails userDetails = this.userDetailsService
                .loadUserByUsername(userEntity.getEmail());

        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder
                .getContext()
                .setAuthentication(auth);
    }

}
