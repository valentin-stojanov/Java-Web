package bg.softuni.intro.ioc;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PostConstruct;

public class Dog implements Animal, BeanNameAware, DisposableBean {

    private boolean superDog;

    public Dog(boolean superDog){
        this.superDog = superDog;
    }

    public Dog(){
        this(false);
    }

    @Override
    public void makeNoise() {
        if (this.superDog) {
            System.out.println("super Bark super bark");
        } else  {
            System.out.println("Bark bark");
        }
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("The name of this Dog is: " + name);
    }

    @PostConstruct
    public void afterInit() {
        System.out.println("Dog is ready to bite!");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("The Dog is about to die....bye!");
    }
}
