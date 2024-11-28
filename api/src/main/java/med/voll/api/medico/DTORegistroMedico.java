package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.Especialidad;
import med.voll.api.dto.DTODireccion;

public record DTORegistroMedico(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")//VALIDA EL PATRON DEL DNI, EN ESTE CASO LO QUE ESTA EN COMILLAS DICE QUE SEAN DE 4 A 6 DIGITOS
        String dni,
        @NotNull
        Especialidad especialidad,
        @NotNull
        @Valid
        DTODireccion direccion) {
}
