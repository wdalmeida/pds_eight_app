package controller;

import org.apache.log4j.Logger;
import org.dmg.pmml.mining.MiningModel;
import org.jpmml.evaluator.ModelEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;
import service.FraudulentTransferLearning;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Map;

@Controller
public class FraudulentTransferController {

    private static Logger logger = Logger.getLogger(FraudulentTransferController.class);

    @Autowired
    FraudulentTransferLearning fraudulentTransferLearning;

    ModelEvaluator<MiningModel> modelEvaluator;

    @PostConstruct
    public void init() {
        try {
            modelEvaluator = fraudulentTransferLearning.initPmml();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/compute", method = RequestMethod.POST)
    public ResponseEntity<Map> test(@RequestBody Integer idTransferDetails) {
        Map result = fraudulentTransferLearning.evaluate(modelEvaluator, idTransferDetails.intValue());
        logger.info(result);
        return new ResponseEntity(result,HttpStatus.OK);
    }
}
