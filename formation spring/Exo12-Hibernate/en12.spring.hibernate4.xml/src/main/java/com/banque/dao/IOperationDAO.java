package com.banque.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IOperationEntity;

/**
 * Interface representant l'operation DAO
 */
public interface IOperationDAO extends IDAO<IOperationEntity> {

	/**
	 * Selectionne les operations en fonction des criteres donnes.
	 *
	 * @param unCompteId
	 *            un compte id
	 * @param unDebut
	 *            une date de debut
	 * @param uneFin
	 *            une date de fin
	 * @param pCreditDebit
	 *            si vrai trouvera les credit, si faux trouvera les debits, si
	 *            null trouvera les deux
	 * @param pSession
	 *            session hibernate
	 * @return la liste des opertaions repondant aux criteres
	 * @throws ExceptionDao
	 *             si un probleme survient
	 *
	 */
	public abstract List<IOperationEntity> selectCriteria(int unCompteId, Date unDebut, Date uneFin,
			Boolean pCreditDebit, Session pSession) throws ExceptionDao;

}