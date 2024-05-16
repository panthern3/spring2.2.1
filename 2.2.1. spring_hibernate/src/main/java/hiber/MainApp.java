package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainApp {
   public static void main(String[] args) {
      AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car();
      car1.setModel("Lada2");
      car1.setSeries(1);

      User user1 = new User();
      user1.setFirstName("John Doe");
      user1.setEmail("john.doe@example.com");
      user1.setCar(car1);

      Car car2 = new Car();
      car2.setModel("Honda");
      car2.setSeries(2);

      User user2 = new User();
      user2.setFirstName("Jane Doe");
      user2.setEmail("jane.doe@example.com");
      user2.setCar(car2);

      userService.add(user1);
      userService.add(user2);

      User retrievedUser = userService.findByCarModelAndSeries("Lada2", 1);
      System.out.println("Retrieved User: " + retrievedUser.getFirstName());

      context.close();
   }
}
