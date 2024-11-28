package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.DTODireccion;

public record DTOActualizarMedico(@NotNull Long id,
                                  String nombre,
                                  String dni,
                                  DTODireccion direccion) {
}
