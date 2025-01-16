package uit.ssy.resumegenerator.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Author : Min Myat Thu Kha
 * Created At : 15/01/2025, Jan , 18:50
 * Project Name : ResumeGenerator
 **/

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class RegistrationRequest {

    private final String userName;
    private final String email;
    private final String password;
}