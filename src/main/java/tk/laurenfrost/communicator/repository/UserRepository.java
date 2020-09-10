package tk.laurenfrost.communicator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.laurenfrost.communicator.entity.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, String> {
}
