package org.example.gudilin.callservicepay.Model;

import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class logging {
    private Logger logger = Logger.getLogger(logging.class.getName()+" 55555555");

    public void getInfo(String str){
        logger.info(str);
    }
}
