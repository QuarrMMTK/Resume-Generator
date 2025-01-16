package uit.ssy.resumegenerator.registration;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Author : Min Myat Thu Kha
 * Created At : 15/01/2025, Jan , 18:47
 * Project Name : ResumeGenerator
 **/
@RestController
@RequestMapping(path = "/api/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirm(token);
    }
}
