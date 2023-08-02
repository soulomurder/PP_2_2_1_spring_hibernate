package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
      UserService userService = context.getBean(UserService.class);

      PlatformTransactionManager transactionManager = context.getBean(PlatformTransactionManager.class);
      TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

      try {
         userService.add(new User("max", "maxov", "gmail.com"));
         userService.editEmail(1L, "new_email@gmail.com");

         transactionManager.commit(transactionStatus);
      } catch (Exception ex) {
         transactionManager.rollback(transactionStatus);
      }

      context.close();
   }
}
