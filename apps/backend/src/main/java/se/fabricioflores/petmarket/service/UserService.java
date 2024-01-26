package se.fabricioflores.petmarket.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.fabricioflores.petmarket.config.JwtProvider;
import se.fabricioflores.petmarket.dto.LoginCredentials;
import se.fabricioflores.petmarket.dto.RegisterCredentials;
import se.fabricioflores.petmarket.exception.InvalidLoginCredsException;
import se.fabricioflores.petmarket.exception.UserNotFoundException;
import se.fabricioflores.petmarket.exception.UsernameNotAvailableException;
import se.fabricioflores.petmarket.model.User;
import se.fabricioflores.petmarket.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtProvider jwtProvider;

  public UserService(
    UserRepository userRepository,
    PasswordEncoder passwordEncoder,
    JwtProvider jwtProvider
    ) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtProvider = jwtProvider;
  }

  /**
   * @throws UserNotFoundException
   */
  public User loadUserByUsername(String username) {
    return userRepository
    .findOneByUsername(username)
    .orElseThrow(() -> new UserNotFoundException());
  }

  /**
   * @throws UserNotFoundException
   */
  public User loadUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
  }

  /**
   * @throws UsernameNotAvailableException
   */
  public User registerUser(RegisterCredentials credentials) {
    try {
      loadUserByUsername(credentials.username());
      throw new UsernameNotAvailableException();

    } catch(UserNotFoundException e) {
      User newUser = new User();

      newUser.setUsername(credentials.username().toLowerCase());
      newUser.setPassword(passwordEncoder.encode(credentials.password()));
      newUser.setFirstname(credentials.firstname());
      newUser.setLastname(credentials.lastname());
      newUser.setEmail(credentials.email());
      newUser.setPhone(credentials.phone());

      return userRepository.save(newUser);
    }
  }

  /**
   * Returns authentication token
   * @throws UserNotFoundException
   * @throws InvalidLoginCredsException
   */
  public String login(LoginCredentials credentials) {
    User user = loadUserByUsername(credentials.username());
    if(!passwordEncoder.matches(credentials.password(), user.getPassword())) throw new InvalidLoginCredsException();
    return jwtProvider.generateToken(user);
  }

}
