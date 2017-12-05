package controller;

import model.TransferModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import parser.XMLParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import org.apache.log4j.Logger;

import javax.validation.Valid;

@Controller
public class MainController {

    private static Logger logger = Logger.getLogger(MainController.class);

    @RequestMapping(value="/submit", method={RequestMethod.GET})
    public ModelAndView getTransferForm() {
        ModelAndView mav = new ModelAndView("createTransferForm");
        mav.addObject("transferModel", new TransferModel());
        logger.info("Transfer form displayed...");
        return mav;
    }

    @RequestMapping(value="/submit", method={RequestMethod.POST})
    public ModelAndView submitTransfer(@Valid @ModelAttribute("transferModel") TransferModel transferModel, BindingResult bindingResult) {

        logger.info(transferModel.toString());

        String sendingIBAN = transferModel.getSendingIBAN();
        double amount = transferModel.getAmount();
        String beneficiaryIban = transferModel.getBeneficiaryIban();
        LocalDate valueDate = transferModel.getValueDate();
        String wording = transferModel.getWording();

        ModelAndView mav = new ModelAndView("response");

        try {

            //url of externalBank api
            URL url = new URL("http://int.eight.inside.esiag.info:9191/externalBank/submit/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/xml");
            OutputStream os = conn.getOutputStream();
            byte[] xmlFile = XMLParser.convertToSCTFormat(sendingIBAN,amount,beneficiaryIban,valueDate,wording);
            os.write(xmlFile);
            os.flush();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_BAD_GATEWAY) {
                mav.addObject("message", "La connexion a echoue (erreur 502)");
                throw new RuntimeException("Connection failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    logger.info(output);
                }
                mav.addObject("message", "Virement correctement envoyé");

            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            return new ModelAndView("reponse").addObject("message", "La requête a echouee");
        } catch (IOException e) {
            return new ModelAndView("reponse").addObject("message", "La requête a echouee");
        }
        return mav;


    }
}
