package med.voll.api.medico;

import med.voll.api.dto.DTODireccion;

public record DTORespuestaMedico(Long id, String nombre, String especialidad, String email, String telefono, String dni,
                                 DTODireccion direccion) {
}
