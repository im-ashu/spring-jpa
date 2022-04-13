package com.learning.security.jpa.service;

import com.learning.security.jpa.model.MyUserDetails;
import com.learning.security.jpa.model.User;
import com.learning.security.jpa.model.UserDetailsImpl;
import com.learning.security.jpa.repository.UserRepository;
import java.util.Optional;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public MyUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<MyUserDetails> optUser = findUserByUsername(username);
    optUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    UserDetailsImpl userDetails = new UserDetailsImpl();
    UserBuilder userBuilder = null;
    MyUserDetails myUserDetails = optUser.get();
    userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
    userBuilder.password(myUserDetails.getPassword());
    userBuilder.roles(myUserDetails.getRoles());
    return userDetails;
  }

  private Optional<MyUserDetails> findUserByUsername(String username) {

    Optional<User> optUser = userRepository.findByUserName(username);
    if (optUser.isPresent()) {
      String[] roles = optUser.get().getRoles().split(",");
      MyUserDetails myUserDetails = new MyUserDetails(username, optUser.get().getPassword(), roles);
      return Optional.<MyUserDetails>ofNullable(myUserDetails);
    }
    return Optional.empty();
  }
}
