package ApplicationWeb.applicationweb.services;

import ApplicationWeb.applicationweb.model.NotificationModel;
import ApplicationWeb.applicationweb.model.Result_DecModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class Result_DecServiceImpl implements Result_DecService {

    @Autowired
    private Result_DecRepository result_decRepository;

    @Override
    public List<Result_DecModel> findAll() {
        return (List<Result_DecModel>) this.result_decRepository.findAll();
    }

}
