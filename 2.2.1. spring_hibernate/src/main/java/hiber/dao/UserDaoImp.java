package hiber.dao;

import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   @Autowired
   private EntityManager em;

   @Transactional
   @Override
   public void add(User user) {
      em.persist(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      Query query = em.createQuery("from User", User.class);
      return (List<User>) query.getResultList();
   }

   @Transactional
   @Override
   public void editEmail(Long id, String newEmail) {
      em.find(User.class, id).setEmail(newEmail);
   }

   @Transactional
   @Override
   public void drop(Long id) {
      em.remove(em.find(User.class, id));
   }
}
