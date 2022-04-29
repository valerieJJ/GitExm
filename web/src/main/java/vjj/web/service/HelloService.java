package vjj.web.service;

import org.springframework.stereotype.Service;
import vjj.web.entity.Worker;

@Service
public class HelloService {

    public String hello(){
        return "on the line";
    }

    public void work(){
    }
}
