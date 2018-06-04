package com.banque.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.banque.dao.IOperationDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.impl.OperationEntity;

/**
 * Gestion des operations.
 */
@Repository
public class OperationDAO extends AbstractDAO<OperationEntity> implements IOperationDAO {
	private static final Logger LOG = LogManager.getLogger(OperationDAO.class);

	/**
	 * Constructeur de l'objet.
	 */
	public OperationDAO() {
		super();
	}

	@Override
	public List<OperationEntity> selectCriteria(int unCompteId, java.util.Date unDebut, java.util.Date uneFin,
			Boolean pCreditDebit) throws ExceptionDao {
		OperationDAO.LOG.debug(
				"selectCriteria sur " + this.getClass().getName() + " avec unCompteId=" + String.valueOf(unCompteId)
						+ " et unDebut=" + unDebut + " et uneFin=" + uneFin + " et pCreditDebit=" + pCreditDebit);
		Map<String, Object> gaps = new HashMap<String, Object>();
		StringBuilder request = new StringBuilder("SELECT OBJECT(op) FROM OperationEntity op WHERE op.compte.id=:id");
		gaps.put("id", Integer.valueOf(unCompteId));

		if (pCreditDebit != null) {
			if (pCreditDebit.booleanValue()) {
				request.append(" and op.montant >= 0");
			} else {
				request.append(" and op.montant <= 0");
			}
		}

		if (unDebut != null && uneFin == null) {
			request.append(" and op.date >= :dateDebut");
			gaps.put("dateDebut", unDebut);
		}

		if (uneFin != null && unDebut == null) {
			request.append(" and op.date <= :dateFin");
			gaps.put("dateFin", uneFin);
		}

		if (uneFin != null && unDebut != null) {
			request.append(" and op.date between :dateDebut and :dateFin");
			gaps.put("dateDebut", unDebut);
			gaps.put("dateFin", uneFin);
		}

		request.append(" order by op.date desc");

		OperationDAO.LOG.debug("Requete JPQL: " + request.toString());

		TypedQuery<OperationEntity> query = super.getEntityManager().createQuery(request.toString(),
				OperationEntity.class);
		for (Map.Entry<String, Object> eGaps : gaps.entrySet()) {
			query.setParameter(eGaps.getKey(), eGaps.getValue());
		}

		List<OperationEntity> resultat = query.getResultList();
		if (resultat == null || resultat.isEmpty()) {
			return Collections.emptyList();
		}
		return resultat;
	}

	@Override
	protected Class<OperationEntity> getEntityClass() {
		return OperationEntity.class;
	}
}