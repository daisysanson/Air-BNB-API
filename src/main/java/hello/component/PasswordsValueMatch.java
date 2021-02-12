//package hello.component;
//
//
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.*;
//
//import static java.lang.annotation.ElementType.*;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//
//@Target({ TYPE, ANNOTATION_TYPE })
//@Retention(RUNTIME)
//@Constraint(validatedBy = PasswordsValueMatch.class)
//@Documented
//public @interface PasswordsValueMatch {
//
//    String message() default "Fields values don't match!";
//
//    Class<?>[] groups() default { };
//
//    Class<? extends Payload>[] payload() default { };
//
//    String field();
//
//    String fieldMatch();
//    @Target({ ElementType.TYPE })
//    @Retention(RetentionPolicy.RUNTIME)
//    @interface List {
//        PasswordsValueMatch[] value();
//    }
//
//    }
//
//
