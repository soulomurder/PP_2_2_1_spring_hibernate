package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {

      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      List<User> users = userService.getUserByCar("Renault Logan", 4650);
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = "+user.getCar().getModel());
         System.out.println("Car series = "+user.getCar().getSeries()   );
         System.out.println();
      }


//      Car car1 = new Car("Renault Logan", 4650);
//      Car car2 = new Car("Renault Logan", 6500);
//
//      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Renault Logan", 4650)));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
//
//      List<User> users = userService.listUsers();
//      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//         System.out.println("Car model = "+user.getCar().getModel());
//         System.out.println("Car series = "+user.getCar().getSeries()   );
//         System.out.println();
//      }
//
//      context.close();
   }
}
