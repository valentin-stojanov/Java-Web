package bg.softuni.intro.ioc;

import javax.annotation.PostConstruct;

public class Cat implements Animal{
    @Override
    public void makeNoise() {
        System.out.println("Meow meow");
    }

    @PostConstruct
    public void afterInit() {
        System.out.println("Cat is ready to bite!");
    }
}
