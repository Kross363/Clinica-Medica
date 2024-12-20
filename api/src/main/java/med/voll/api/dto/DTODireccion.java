package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;

public record DTODireccion(
        @NotBlank
        String calle,
        @NotBlank
        String distrito,
        @NotBlank
        String ciudad,
        @NotBlank
        String numero,
        @NotBlank
        String complemento) {
}
