package cn.lhsearch.rcs.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.lhsearch.rcs.entity.*;


public interface UserRepository extends JpaRepository<User, String> {
  List<Case> findByWeChatOpenId(String weChatOpenId);
}
