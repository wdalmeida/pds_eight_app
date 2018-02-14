package service;

import entity.TransferDetailsEntity;
import org.apache.log4j.Logger;
import org.dmg.pmml.FieldName;
import org.dmg.pmml.mining.MiningModel;
import org.jpmml.evaluator.InputField;
import org.jpmml.evaluator.ModelEvaluator;
import org.jpmml.evaluator.mining.MiningModelEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.dmg.pmml.PMML;
import org.jpmml.model.ImportFilter;
import org.jpmml.model.JAXBUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;
import repository.TransferDetailsRepository;

import javax.xml.bind.JAXBException;
import javax.xml.transform.sax.SAXSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class FraudulentTransferLearning {

    private static Logger logger = Logger.getLogger(FraudulentTransferLearning.class);

    @Value("classpath:fraudulentTransaction.pmml")
    private Resource pmmlResource;

    @Autowired
    private TransferDetailsRepository transferDetailsRepository;

    public ModelEvaluator<MiningModel> initPmml() throws IOException, JAXBException, SAXException {
        InputStream inputStream = pmmlResource.getInputStream();
        InputSource source = new InputSource(inputStream);
        SAXSource transformedSource = ImportFilter.apply(source);

        PMML pmml = JAXBUtil.unmarshalPMML(transformedSource);
        ModelEvaluator<MiningModel> modelEvaluator = new MiningModelEvaluator(pmml);

        for (InputField inputField : modelEvaluator.getActiveFields()) {
            logger.info("field name of model: " + inputField);
        }

        return modelEvaluator;
    }

    public Map<String,?> evaluate(ModelEvaluator<MiningModel> modelEvaluator, int idTransferDetails) {

        TransferDetailsEntity transferDetailsEntity = transferDetailsRepository.findOne(idTransferDetails);

        Map<FieldName, String> arguments = new LinkedHashMap<FieldName, String>();
        arguments.put(new FieldName("step"), String.valueOf(transferDetailsEntity.getIdtransferdetails()));
        arguments.put(new FieldName("type"), "5");//always type 5 for transfer
        arguments.put(new FieldName("amount"), String.valueOf(transferDetailsEntity.getTransferEntity().getTransactionEntity().getAmount()));
        arguments.put(new FieldName("oldbalanceOrg"), String.valueOf(transferDetailsEntity.getOldBalanceOrg()));
        arguments.put(new FieldName("newbalanceOrig"), String.valueOf(transferDetailsEntity.getNewbalanceorig()));
        arguments.put(new FieldName("oldbalanceDest"), "0");//always 0 : we cannot have this information
        arguments.put(new FieldName("newbalanceDest"), "0");//always 0 : we cannot have this information

        modelEvaluator.verify();


        Map<FieldName, ?> results = modelEvaluator.evaluate(arguments);

        logger.info("results of prediction" + results.toString());
        logger.info("fraudulent probability: " + results.get(new FieldName("Probability_1")));
        Double probability = Double.parseDouble(results.get(new FieldName("Probability_1")).toString());
        Double percent = probability*100;

        Map response = new LinkedHashMap<>();
        response.put("percent",percent);
        response.put("transfer",transferDetailsEntity);
        return response;
    }
}
