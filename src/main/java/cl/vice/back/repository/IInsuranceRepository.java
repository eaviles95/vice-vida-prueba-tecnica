package cl.vice.back.repository;

import cl.vice.back.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInsuranceRepository extends JpaRepository<Insurance, Integer> {
}
