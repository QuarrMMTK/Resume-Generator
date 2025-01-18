package uit.ssy.resumegenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uit.ssy.resumegenerator.models.Education;
import uit.ssy.resumegenerator.models.Job;
import uit.ssy.resumegenerator.models.UserProfile;
import uit.ssy.resumegenerator.repository.UserProfileRepository;

import java.security.Principal;
import java.util.Optional;

/**
 * Author : Min Myat Thu Kha
 * Created At : 15/01/2025, Jan , 18:39
 * Project Name : ResumeGenerator
 **/
@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    private boolean resourceExists(String path) {
        Resource resource = resourceLoader.getResource("classpath:templates/" + path + ".html");
        return resource.exists();
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/edit")
    public String edit(Principal principal,
                       Model model,
                       @RequestParam(required = false) String add) {
        String userName = principal.getName();
        UserProfile userProfile = getUserProfile(userName);
        if (add != null) {
            switch (add) {
                case "job" -> userProfile.getJobs().add(new Job());
                case "education" -> userProfile.getEducations().add(new Education());
                case "skill" -> userProfile.getSkills().add("");
            }
        }

        model.addAttribute("userProfile", userProfile);
        return "profile-edit";
    }


    @PostMapping("/edit")
    public String postEdit(Principal principal, @ModelAttribute UserProfile userProfile) {
        String userName = principal.getName();
        UserProfile savedUserProfile = getUserProfile(userName);

        userProfile.setId(savedUserProfile.getId());
        userProfile.setUserName(userName);
        userProfileRepository.save(userProfile);
        return "redirect:/edit";
    }

    @GetMapping("/delete")
    public String delete(
            Principal principal,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) int index) {
        String userName = principal.getName();
        UserProfile userProfile = getUserProfile(userName);

        switch (type) {
            case "job" -> userProfile.getJobs().remove(index);
            case "education" -> userProfile.getEducations().remove(index);
            case "skill" -> userProfile.getSkills().remove(index);
        }

        userProfileRepository.save(userProfile);
        return "redirect:/edit";
    }

    @GetMapping("/view/{userName}")
    public String view(
            @PathVariable("userName") String userName,
            Model model,
            Principal principal) {
        if (principal != null && !"".equals(principal.getName())) {
            boolean currentUsersProfile = principal.getName().equals(userName);
            model.addAttribute("currentUsersProfile", currentUsersProfile);
        }
        UserProfile userProfile = getUserProfile(userName);
        model.addAttribute("userProfile", userProfile);
        String themePath = "profile-templates/" + userProfile.getTheme() + "/index";
        if (!resourceExists(themePath)) {
            themePath = "profile-templates/default/index";
        }
        return themePath;

    }

    private UserProfile getUserProfile(String userName) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userName));
        return userProfileOptional.get();
    }
}
