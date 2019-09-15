package hibertest.singletable;

import hibertest.utils.HibernateUtil;
import hibertest.utils.InheritanceStrategyExecutable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SingleTableStrategy implements InheritanceStrategyExecutable {
    @Override
    public void execute() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(44411111);
        creditCard.setExpMonth("Jan");
        creditCard.setExpYear("2017");
        creditCard.setOwner("Bill Gates");
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccount(111222333);
        bankAccount.setBankName("Goldman Sachs");
        bankAccount.setSwift("GOLDUS33");
        bankAccount.setOwner("Donald Trump");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(HibernateUtil.InheritanceStrategy.SINGLE_TABLE);

        Session session;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction  = session.beginTransaction();
            session.persist(creditCard);
            session.persist(bankAccount);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }

        Session session1;
        Transaction transaction1 = null;
        try {
            session1 = sessionFactory.getCurrentSession();
            transaction1  = session1.beginTransaction();
            List billingDetails = session1.createQuery("select bd from hibertest.singletable.BillingDetails bd").list();
            for (int i = 0; i < billingDetails.size(); i++) {
                System.out.println(billingDetails.get(i));
            }
        } catch (Exception e) {
            transaction1.rollback();
            throw e;
        }
    }
}
