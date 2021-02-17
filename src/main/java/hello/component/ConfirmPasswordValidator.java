package hello.component;

import hello.model.User;
import hello.service.SiteUserDetails;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import hello.model.User;
import hello.service.SiteUserDetails;
import lombok.SneakyThrows;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.EnglishSequenceData;
import org.passay.IllegalSequenceRule;
import org.passay.LengthRule;
import org.passay.MessageResolver;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.PropertiesMessageResolver;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.RuleResultDetail;
import org.passay.WhitespaceRule;
import org.springframework.validation.BindingResult;
import org.thymeleaf.messageresolver.IMessageResolver;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Valid;
import javax.validation.Validation;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, String> {

    private SiteUserDetails userDetails = new SiteUserDetails();

    @Override
    public void initialize(final ConfirmPassword constraintAnnotation) {

    }

    @SneakyThrows
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/main/resources/passay.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MessageResolver resolver = new PropertiesMessageResolver(props);

        PasswordValidator validator = new PasswordValidator(resolver, Arrays.asList(

                new LengthRule(8, 16),

                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                new CharacterRule(EnglishCharacterData.LowerCase, 1),


                // at least one digit character

                new CharacterRule(EnglishCharacterData.Digit, 1),


                // at least one symbol (special character)

                new CharacterRule(EnglishCharacterData.Special, 1),


                // no whitespace

                new WhitespaceRule(),


                // rejects passwords that contain a sequence of >= 5 characters alphabetical  (e.g. abcdef)

                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),


                // rejects passwords that contain a sequence of >= 5 characters numerical   (e.g. 12345)

                new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false)

        ));


        RuleResult result = validator.validate(new PasswordData(password));


        if (result.isValid()) {

            return true;

        }


        List<String> messages = validator.getMessages(result);
        String newLine = System.getProperty("line.separator");
        String messageTemplate = String.join(newLine, messages);

        context.buildConstraintViolationWithTemplate( messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}

