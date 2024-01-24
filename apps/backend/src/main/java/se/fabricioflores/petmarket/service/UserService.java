package se.fabricioflores.petmarket.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.fabricioflores.petmarket.dto.RegisterCredentials;
import se.fabricioflores.petmarket.exception.UserNotFoundException;
import se.fabricioflores.petmarket.exception.UsernameNotAvailable;
import se.fabricioflores.petmarket.model.User;
import se.fabricioflores.petmarket.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User loadUserByUsername(String username) throws UserNotFoundException {
    return userRepository
    .findOneByUsername(username)
    .orElseThrow(() -> new UserNotFoundException());
  }

  public User registerUser(RegisterCredentials credentials) throws UsernameNotAvailable {
    try {
      loadUserByUsername(credentials.username());
      throw new UsernameNotAvailable();

    } catch(UserNotFoundException e) {
      User newUser = new User();

      newUser.setUsername(credentials.username());
      newUser.setPassword(passwordEncoder.encode(credentials.password()));
      newUser.setFirstname(credentials.firstname());
      newUser.setLastname(credentials.lastname());
      newUser.setEmail(credentials.email());
      newUser.setPhone(credentials.phone());

      return userRepository.save(newUser);
    }
  }

}
