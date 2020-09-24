/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fortaleza.ce.gov.br.acesso.validators;

/**
 *
 * @author everton
 */
import fortaleza.ce.gov.br.acesso.annotations.EqualFields;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;


public class EqualFieldsValidator implements ConstraintValidator<EqualFields, Object> {

	private String baseValue;
	private String matchValue;

        @Override
	public void initialize(EqualFields constraintAnnotation) {
		this.baseValue = constraintAnnotation.baseValue();
		this.matchValue = constraintAnnotation.matchValue();
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {

		/*
		 * Recupera o valor da propriedade/campo do objeto que está envolvido pelo
		 * Wrapper
		 */
		Object fieldValue = new BeanWrapperImpl(obj).getPropertyValue(baseValue);
		Object fieldMatchValue = new BeanWrapperImpl(obj).getPropertyValue(matchValue);

		if (fieldValue != null)
			return fieldValue.equals(fieldMatchValue);
		else
			return fieldMatchValue == null;

	}

}