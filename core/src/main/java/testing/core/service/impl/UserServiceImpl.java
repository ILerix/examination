package testing.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testing.core.entity.User;
import testing.core.exception.NotFoundException;
import testing.core.repository.UserRepository;
import testing.core.service.UserService;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User getById(UUID id) {
    return userRepository.findById(id)
            .orElseThrow(() -> { throw new NotFoundException("User not found"); });
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }
}
