package com.lmtAPI.lmt.Repository;

import com.lmtAPI.lmt.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(@Param("email") String email);
    User findByUserIdFirebase(@Param("userIdFirebase") String userIdFirebase);
}
