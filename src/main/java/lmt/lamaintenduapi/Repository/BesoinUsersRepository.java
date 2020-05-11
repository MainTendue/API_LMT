package lmt.lamaintenduapi.Repository;

import lmt.lamaintenduapi.model.BesoinUsers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(maxAge = 3600)
public interface BesoinUsersRepository extends CrudRepository<BesoinUsers, Integer> {
}
