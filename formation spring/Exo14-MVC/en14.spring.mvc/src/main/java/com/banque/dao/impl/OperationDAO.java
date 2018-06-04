package com.banque.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.banque.dao.IOperationDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IOperationEntity;

/**
 * Gestion des operations.
 */
@Repository
public class OperationDAO extends AbstractDAO<IOperationEntity> implements IOperationDAO {
	private static final Logger LOG = LogManager.getLogger(OperationDAO.class);

	/**
	 * Constructeur de l'objet.
	 */
	public OperationDAO() {
		super();
	}

	@Override
	protected Class<IOperationEntity> getEntityClass() {
		return IOperationEntity.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IOperationEntity> selectCriteria(int unCompteId, Date unDebut, Date uneFin, Boolean pCreditDebit)
			throws ExceptionDao {

		List<IOperationEntity> result = new ArrayList<IOperationEntity>();
		OperationDAO.LOG.debug(
				"selectCriteria sur " + this.getClass().getName() + "avec unCompteId=" + String.valueOf(unCompteId)
						+ " et unDebut=" + unDebut + " et uneFin=" + uneFin + " et pCreditDebit=" + pCreditDebit);
		try {
			Session pSession = this.getSessionFactory().getCurrentSession();
			StringBuilder request = new StringBuilder();
			request.append("select entity from ").append(this.getEntityClassName());
			request.append(" as entity where entity.compteId=:compteId");
			Map<String, Object> gaps = new HashMap<String, Object>();
			gaps.put("compteId", Integer.valueOf(unCompteId));
			if (unDebut != null && uneFin == null) {
				request.append(" and entity.date >= :dateDebut");
				gaps.put("dateDebut", unDebut);
			}

			if (uneFin != null && unDebut == null) {
				request.append(" and entity.date <= :dateFin");
				gaps.put("dateFin", uneFin);
			}

			if (uneFin != null && unDebut != null) {
				request.append(" and entity.date between :dateDebut and :dateFin");
				gaps.put("dateDebut", unDebut);
				gaps.put("dateFin", uneFin);
			}

			if (pCreditDebit != null) {
				if (pCreditDebit.booleanValue()) {
					request.append(" and entity.montant >= 0");
				} else {
					request.append(" and entity.montant <= 0");
				}
			}

			request.append(" order by entity.date DESC");

			OperationDAO.LOG.debug("Requete OQL: " + request.toString());

			Query queryObject = pSession.createQuery(request.toString());
			for (Map.Entry<String, Object> unGap : gaps.entrySet()) {
				queryObject.setParameter(unGap.getKey(), unGap.getValue());
			}

			result.addAll(queryObject.list());
		} catch (Exception e) {
			throw new ExceptionDao(e);
		}
		return result;
	}
}