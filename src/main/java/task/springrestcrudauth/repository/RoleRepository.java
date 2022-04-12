package task.springrestcrudauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.springrestcrudauth.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
