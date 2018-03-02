package com.week6challange.lostandfound.Repositories;

import com.week6challange.lostandfound.Model.Role;
import com.week6challange.lostandfound.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByRole(String role);
    Role findByUsers(User user);
    //List<Role> findByUsers(User user);
}
