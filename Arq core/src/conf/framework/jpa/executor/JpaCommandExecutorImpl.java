package conf.framework.jpa.executor;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import conf.framework.jpa.core.Jpa;
import conf.framework.jpa.executor.Action;
import conf.util.BusinessException;
import conf.util.LoggerImpl;

public class JpaCommandExecutorImpl implements Executor {

	private void rollback(EntityTransaction tx) {
		tx.rollback();
	}

	private void close(EntityManager em) {
		em.close();
	}

	public Object execute(Action action) throws BusinessException {
		Object res = null;

		EntityManager em = Jpa.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
		} catch (Exception rex) {
			throw new BusinessException("Existe un problema con la BDD, es posible que no este disponible");
		}

		try {
			res = action.execute();
			LoggerImpl.log("Ejecutado command " + action.getClass().getName());
			tx.commit();
		} catch (BusinessException bex) {
			rollback(tx);
			throw bex;
		} catch (RuntimeException rex) {
			rollback(tx);
			throw rex;
		} finally {
			close(em);
		}
		return res;
	}

}
