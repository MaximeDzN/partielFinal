package eu.ensup.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponse {
    String token;
}
