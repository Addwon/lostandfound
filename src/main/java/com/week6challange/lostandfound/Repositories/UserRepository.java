package com.week6challange.lostandfound.Repositories;

import com.week6challange.lostandfound.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(long id);

    Long countByEmail(String email);
    Long countByUsername(String username);
    User findDistinctByRoles(String role);

}
