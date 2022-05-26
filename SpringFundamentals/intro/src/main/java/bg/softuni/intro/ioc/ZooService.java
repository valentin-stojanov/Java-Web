package bg.softuni.intro.ioc;

import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZooService {



//    private final Animal animal;
//
//    public ZooService(Animal animal) {
//        this.animal = animal;
//    }
//
//    public void doWork() {
//        animal.makeNoise();
//    }

    private List<Animal> animals;

    public ZooService(List<Animal> animals){
        this.animals = animals;
    }

    public void doWork() {
        animals.stream()
                .forEach(Animal::makeNoise);
    }
}
