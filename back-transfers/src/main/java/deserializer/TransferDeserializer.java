package deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.TransferDto;
import model.TransferModel;
import org.apache.kafka.common.serialization.Deserializer;

import java.text.SimpleDateFormat;
import java.util.Map;

public class TransferDeserializer implements Deserializer<TransferDto> {

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {

    }

    @Override
    public TransferDto deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        TransferDto transfer = null;
        try {
            transfer = mapper.readValue(arg1, TransferDto.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return transfer;
    }

}
