package med.voll.api.paciente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.DTODireccion;

public record DTORegistroPaciente(@NotBlank
                                  String nombre,
                                  @NotBlank
                                  @Email
                                  String email,
                                  @NotBlank
                                  String telefono,
                                  @NotBlank
                                  String dni,
                                  @NotNull @Valid DTODireccion direccion) {
}
