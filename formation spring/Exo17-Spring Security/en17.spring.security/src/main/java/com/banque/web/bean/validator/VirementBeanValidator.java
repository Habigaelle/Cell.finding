package com.banque.web.bean.validator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.banque.web.bean.VirementBean;

/**
 * Validateur pour virement. <br/>
 */
public class VirementBeanValidator implements Validator {
	private static final Logger LOG = LogManager.getLogger(VirementBeanValidator.class);

	@Override
	public boolean supports(Class<?> pArg0) {
		return VirementBean.class == pArg0;
	}

	@Override
	public void validate(Object aBean, Errors errors) {
		VirementBean vb = (VirementBean) aBean;
		if (vb.getMontant() == null) {
			VirementBeanValidator.LOG.warn("Validation error " + this.getClass().getName() + " pour montant");
			errors.rejectValue("montant", "error.montant.empty");
		}

		if (vb.getCptDstId() == null) {
			VirementBeanValidator.LOG.warn("Validation error " + this.getClass().getName() + " pour cptDstId");
			errors.rejectValue("cptDstId", "error.cptdstid.empty");
		}

		if (vb.getCptSrcId() == null) {
			VirementBeanValidator.LOG.warn("Validation error " + this.getClass().getName() + " pour cptSrcId");
			errors.rejectValue("cptSrcId", "error.cptsrcid.empty");
		}

		double lcMntant = -1d;
		if (vb.getMontant() != null) {
			try {
				lcMntant = Double.parseDouble(vb.getMontant());
			} catch (Exception e) {
				VirementBeanValidator.LOG.warn("Validation error " + this.getClass().getName() + " pour montant", e);
				errors.rejectValue("montant", "error.montant.notanumber");
			}
			if (lcMntant <= 0) {
				VirementBeanValidator.LOG.warn("Validation error " + this.getClass().getName() + " pour montant");
				errors.rejectValue("montant", "error.montant.notpositive");
			}
		}

		int idsrc = -1;
		if (vb.getCptSrcId() != null) {
			try {
				idsrc = Integer.parseInt(vb.getCptSrcId());
			} catch (Exception e) {
				VirementBeanValidator.LOG.warn("Validation error " + this.getClass().getName() + " pour cptSrcId", e);
				errors.rejectValue("cptSrcId", "error.cptsrcid.notanumber");
			}
			if (idsrc < 0) {
				VirementBeanValidator.LOG.warn("Validation error " + this.getClass().getName() + " pour cptSrcId");
				errors.rejectValue("cptSrcId", "error.cptsrcid.notanumber");
			}
		}
		int iddst = -1;
		if (vb.getCptDstId() != null) {
			try {
				iddst = Integer.parseInt(vb.getCptDstId());
			} catch (Exception e) {
				VirementBeanValidator.LOG.warn("Validation error " + this.getClass().getName() + " pour cptDstId", e);
				errors.rejectValue("cptDstId", "error.cptdstid.notanumber");
			}
			if (iddst < 0) {
				VirementBeanValidator.LOG.warn("Validation error " + this.getClass().getName() + " pour cptDstId");
				errors.rejectValue("cptDstId", "error.cptsrcid.notanumber");
			}
		}

		if (iddst != -1 && idsrc != -1) {
			if (idsrc == iddst) {
				VirementBeanValidator.LOG.warn("Validation error pour cptDstId et cptSrcId");
				errors.rejectValue("cptDstId", "error.cptsid.same");
			}
		}
	}

}
