package uit.ssy.resumegenerator.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author : Min Myat Thu Kha
 * Created At : 15/01/2025, Jan , 18:46
 * Project Name : ResumeGenerator
 **/
@Service
public class EmailValidator implements Predicate<String> {
    private static final String regex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    @Override
    public boolean test(String s) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}