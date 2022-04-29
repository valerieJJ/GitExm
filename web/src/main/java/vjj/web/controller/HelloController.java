package vjj.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vjj.web.service.ExecuteService;
import vjj.web.service.HelloService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private HelloService service;

    @Autowired
    public ExecuteService executeService;

//    private int cnt = 0;
    private ThreadLocal<Integer> cnt = new ThreadLocal<Integer>();

    @RequestMapping(value = "/work/{jobname}", method = RequestMethod.GET)
    public String work(@PathVariable("jobname") String joobname, HttpServletRequest request, HttpServletResponse response) {
        executeService.execute(joobname);
        return "done";
    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();

        if(cnt.get()==null){
            cnt.set(1);
        }else{
            int count = cnt.get();
            count++;
            cnt.set(count);
        }

        Cookie[] cookies = request.getCookies();
        boolean f = true;
        for(Cookie c:cookies){
            if(c.getName().equals("session-id")){
                log.info("cookie existed");
                f = false;
                break;
            }
        }
        if (f){
            Cookie cookie = new Cookie("session-id", session.getId());
            response.addCookie(cookie);
        }

        log.info("sessionID = "+session.getId());
        log.info("threadID = "+Thread.currentThread().getId()+", cnt = "+cnt.get());

        return service.hello();
    }
}
