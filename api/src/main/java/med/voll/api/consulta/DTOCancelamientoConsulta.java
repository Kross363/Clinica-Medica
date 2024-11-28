package med.voll.api.consulta;

import jakarta.validation.constraints.NotNull;

public record DTOCancelamientoConsulta(@NotNull
                                       Long idConsulta,
                                       @NotNull
                                       MotivoCancelamiento motivo) {
}
