package cn.lhsearch.rcs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.lhsearch.rcs.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByEmail(String email);

  Boolean existsByEmail(String email);
}

