package my.project.hibernate;

import javax.transaction.UserTransaction;

import org.hibernate.engine.transaction.internal.jta.JtaTransactionFactory;

/**
 * A Hibernate {@link JTATransactionFactory} implementation that avoids using JNDI for lookups. You must set the
 * {@link UserTransaction} on this bean with #setUserTransaction(UserTransaction)} before using it.
 */
public class LocalJtaTransactionFactory extends JtaTransactionFactory {

  private static volatile UserTransaction userTransaction;
  
  public synchronized void setUserTransaction(UserTransaction userTransaction) {
    LocalJtaTransactionFactory.userTransaction = userTransaction;
  }

  protected UserTransaction getUserTransaction() {
    return userTransaction;
  }

}