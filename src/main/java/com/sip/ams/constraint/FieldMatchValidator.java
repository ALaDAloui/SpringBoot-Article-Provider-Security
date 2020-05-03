package com.sip.ams.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
	private String firstFieldName;
    private String secondFieldName;
    
    
    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);
            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception ignore) {}
        return true;
    }
    
    
    /**
     * @author Ala
     * When you are building forms you may come across a requirement to validate/compare if different fields inside 
     * a form are equal to another field in the same form like password and/or email fields. In this example we build a
     *  simple form where we have a password and a confirmPassword field. We need to make sure the user has entered 
     *  the correct password twice before submitting the request. 
     * au moment q'on on enregistre un compte (email / confirmer mail, mot de passe/ confirmer mot de passe) cette annotation 
     * permet de comparer les deux champs si ils sont identiques  
     */
    
}
