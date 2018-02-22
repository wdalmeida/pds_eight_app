package eight.ing3.esipe.fr.rssfeed.service;

import eight.ing3.esipe.fr.rssfeed.bean.RSSFeed;

import java.util.List;

public interface IRssService {
    RSSFeed getOne(int id);

    List<RSSFeed> getAllFromBEM();

    List<RSSFeed> getAllFromBFM();

}
