package med.voll.api.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.DTODireccion;

public record DTOActualizarPaciente(
        @NotNull
        Long id,
        String nombre,
        String telefono,
        DTODireccion direccion) {
}
