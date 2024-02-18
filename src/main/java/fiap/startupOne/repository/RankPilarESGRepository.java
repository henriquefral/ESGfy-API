package fiap.startupOne.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fiap.startupOne.model.RankPilarESG;

@Repository
public interface RankPilarESGRepository extends JpaRepository<RankPilarESG, Integer>{

}