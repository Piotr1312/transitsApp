package transitsApp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import transitsApp.model.Transit;

@Repository
public interface TransitRepository extends JpaRepository<Transit, Long> {
    List<Transit> findFirst10ByOrderByIdDesc();

    @Query("SELECT t FROM Transit t WHERE t.date=:date")
    List<Transit> findByDate(@Param("date") LocalDate date);

    @Query("SELECT t FROM Transit t WHERE t.date BETWEEN :startDate AND :endDate ORDER BY t.date DESC")
    List<Transit> findByDateBetween(@Param("startDate") LocalDate startDate,
                                    @Param("endDate") LocalDate endDate);
}
