package eu.ensup.user.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Getter
public class SignupRequest {
    private String username;
    private String password;
}
