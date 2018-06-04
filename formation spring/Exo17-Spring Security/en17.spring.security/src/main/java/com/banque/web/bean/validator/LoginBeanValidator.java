package com.banque.web.bean.validator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.banque.web.bean.LoginBean;

/**
 * Login bean validator.
 */
public class LoginBeanValidator implements Validator {
	private static final Logger LOG = LogManager.getLogger(LoginBeanValidator.class);

	/**
	 * Constructeur de l'objet.
	 */
	public LoginBeanValidator() {
		super();
	}

	@Override
	public boolean supports(Class<?> pArg0) {
		return LoginBean.class == pArg0;
	}

	@Override
	public void validate(Object aLoginBean, Errors errors) {
		LoginBean lb = (LoginBean) aLoginBean;
		// Il est aussi possible d'utiliser
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "error.user.empty");

		if (lb.getPassword() == null) {
			LoginBeanValidator.LOG.warn("Validation error " + this.getClass().getName() + " pour password");
			errors.rejectValue("password", "error.password.empty");
		}
	}

}
