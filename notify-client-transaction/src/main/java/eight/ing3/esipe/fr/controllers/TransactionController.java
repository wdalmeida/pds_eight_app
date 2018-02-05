package eight.ing3.esipe.fr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("transactions")
public class TransactionController {

    @RequestMapping(value = "list")
    public String getTransactions(){
        System.out.println("COUCOU");
        return "fezfz";
    }

}
