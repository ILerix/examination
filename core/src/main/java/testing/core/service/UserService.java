package testing.core.service;


import testing.core.entity.User;

import java.util.UUID;


public interface UserService {

  User getById(UUID id);

  User save(User user);

}
