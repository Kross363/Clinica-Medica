package med.voll.api.consulta;

import java.time.LocalDateTime;

public record DTODetalleConsulta(Long id,
                                 Long idMedico,
                                 Long idPaciente,
                                 LocalDateTime fecha) {
    //CONSTRUCTOR CREADO PARA EL METODO reservar DE ReservaDeConsultas
    public DTODetalleConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(),consulta.getPaciente().getId(),consulta.getFecha());
    }
}
