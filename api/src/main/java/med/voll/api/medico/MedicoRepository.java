package med.voll.api.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.Especialidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico,Long> {

    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query(value = """
            SELECT m FROM Medico m
            WHERE
            m.activo=1
            AND
            m.especialidad=:especialidad
            AND m.id NOT IN(
                select c.medico.id FROM Consulta c
                WHERE
                c.fecha=:fecha
            )
            ORDER BY FUNCTION('RAND')
            LIMIT 1
            """,nativeQuery = true)
    Medico elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad especialidad, @NotNull @Future LocalDateTime fecha);

    @Query("""
            SELECT m.activo
            FROM Medico m
            WHERE
            m.id=:idMedico
            """)
    boolean findActivoById(Long idMedico);
}
