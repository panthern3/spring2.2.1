package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car();
      car1.setModel("Granta");
      car1.setSeries(1);

      // Проверяем, существует ли машина в базе данных
      User existingUser = userService.findByCarModelAndSeries(car1.getModel(), car1.getSeries());
      if (existingUser != null) {
         System.out.println("User with car " + car1.getModel() + " series " + car1.getSeries() + " already exists:");
         System.out.println("Name: " + existingUser.getFirstName());
      } else {
         // Машина не существует, создаем нового пользователя
         User user1 = new User();
         user1.setFirstName("Selm Doe");
         user1.setEmail("john.doe@example.com");
         user1.setCar(car1);

         userService.add(user1);
      }


      context.close();
   }
}
