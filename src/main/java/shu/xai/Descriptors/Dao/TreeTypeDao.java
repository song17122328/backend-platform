package shu.xai.Descriptors.Dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import shu.xai.Descriptors.Entity.TreeType;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class TreeTypeDao {

    @Resource
    private MongoTemplate mongoTemplate;

    public List<TreeType>findAll(){
        return mongoTemplate.findAll(TreeType.class);
    }
}
