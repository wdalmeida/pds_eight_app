package serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.TransferDto;
import model.TransferModel;
import org.apache.kafka.common.serialization.Serializer;

import java.text.SimpleDateFormat;
import java.util.Map;


public class TransferSerializer implements Serializer<TransferDto> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String arg0, TransferDto transferDto) {

        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        try {
            retVal = objectMapper.writeValueAsString(transferDto).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override public void close() {

    }
}
