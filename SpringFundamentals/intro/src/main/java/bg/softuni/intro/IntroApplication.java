package bg.softuni.intro;

import bg.softuni.intro.ioc.Animal;
import bg.softuni.intro.ioc.Dog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class IntroApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntroApplication.class, args);
//		Animal dog = applicationContext.getBean(Dog.class);
//		System.out.println();
	}

}
