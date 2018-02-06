package service;

import org.apache.log4j.Logger;
import org.dmg.pmml.FieldName;
import org.dmg.pmml.mining.MiningModel;
import org.jpmml.evaluator.FieldValue;
import org.jpmml.evaluator.InputField;
import org.jpmml.evaluator.ModelEvaluator;
import org.jpmml.evaluator.mining.MiningModelEvaluator;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.dmg.pmml.PMML;
import org.jpmml.model.ImportFilter;
import org.jpmml.model.JAXBUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.transform.sax.SAXSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class FraudulentTransferLearning {

    private static Logger logger = Logger.getLogger(FraudulentTransferLearning.class);

    @Value("classpath:fraudulentTransaction.pmml")
    private Resource pmmlResource;

    public void initPmml() throws IOException, JAXBException, SAXException {
        InputStream inputStream = pmmlResource.getInputStream();
        InputSource source = new InputSource(inputStream);
        SAXSource transformedSource = ImportFilter.apply(source);

        PMML pmml = JAXBUtil.unmarshalPMML(transformedSource);
        ModelEvaluator<MiningModel> modelEvaluator = new MiningModelEvaluator(pmml);

        for (InputField inputField : modelEvaluator.getActiveFields()) {
            logger.info("field name of model: " + inputField);
        }

        Map<FieldName, String> arguments = new LinkedHashMap<FieldName, String>();
        arguments.put(new FieldName("step"), "1");
        arguments.put(new FieldName("type"), "5");
        arguments.put(new FieldName("amount"), "1277212.77");
        arguments.put(new FieldName("oldbalanceOrg"), "1277212.77");
        arguments.put(new FieldName("newbalanceOrig"), "0");
        arguments.put(new FieldName("oldbalanceDest"), "0");
        arguments.put(new FieldName("newbalanceDest"), "0");

        modelEvaluator.verify();

        Map<FieldName, ?> results = modelEvaluator.evaluate(arguments);

        logger.info("results of prediction" + results.toString());

        logger.info(results.get(new FieldName("Predicted_isFraud")));

    }
}
