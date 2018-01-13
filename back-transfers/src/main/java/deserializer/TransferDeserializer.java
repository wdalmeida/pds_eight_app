package deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.TransferModel;
import org.apache.kafka.common.serialization.Deserializer;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by Tom on 06/01/2018.
 */
public class TransferDeserializer implements Deserializer<TransferModel> {

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {

    }

    @Override
    public TransferModel deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        TransferModel transfer = null;
        try {
            transfer = mapper.readValue(arg1, TransferModel.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return transfer;
    }

}
