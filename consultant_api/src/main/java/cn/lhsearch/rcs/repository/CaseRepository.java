package cn.lhsearch.rcs.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.lhsearch.rcs.entity.*;


public interface CaseRepository extends JpaRepository<Case, Long> {
  List<Case> findByPublished(boolean published);

  List<Case> findByCandidateNameContaining(String candidateName);
}
