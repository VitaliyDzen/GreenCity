package greencity.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuccessSignInDto {
    private Long id;
    private String accessToken;
    private String refreshToken;
    private String firstName;
    private boolean ownRegistrations;
}
