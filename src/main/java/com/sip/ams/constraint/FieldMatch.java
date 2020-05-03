package com.sip.ams.constraint;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {
	String message() default "{constraints.field-match}";
    Class < ? > [] groups() default {};
    Class < ? extends Payload > [] payload() default {};
    String first();
    String second();

    @Target({
        TYPE,
        ANNOTATION_TYPE
    })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        FieldMatch[] value();
    }
	
    /**
     * @author Ala
     * When you are building forms you may come across a requirement to validate/compare if different fields inside 
     * a form are equal to another field in the same form like password and/or email fields. In this example we build a
     *  simple form where we have a password and a confirmPassword field. We need to make sure the user has entered 
     *  the correct password twice before submitting the request.
     *au moment q'on on enregistre un compte (email / confirmer mail, mot de passe/ confirmer mot de passe) cette annotation 
     * permet de comparer les deux champs si ils sont identiques 
     */
	
	
	
	
	
}
