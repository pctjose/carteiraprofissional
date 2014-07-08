package org.apm.carteiraprofissional.view.validator;

import java.util.Date;
import java.util.Map;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class FormacaoValidator extends  AbstractValidator {

	public void validate(ValidationContext ctx) {
		Map<String,Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());
		validateYear(ctx, (Integer)beanProps.get("ano").getValue());
	}
	
	private void validateYear(ValidationContext ctx, int year) {        
        if(year>new Date().getYear() || year<1900){
        	 this.addInvalidMessage(ctx, "ano", "O ano introduzido é inválido!");
        }
    }

}
