package med.voll.api.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.Especialidad;

import java.time.LocalDateTime;

public record DTOConsultaReserva(Long idMedico,
                                 @NotNull
                                 Long idPaciente,
                                 @NotNull
                                 @Future//ESTA ANOTACION SIGNIFICA QUE LA FECHA NO PUEDE SER ANTERIOR A LA FECHA ACTUAL
                                 LocalDateTime fecha,
                                 Especialidad especialidad) {
}
