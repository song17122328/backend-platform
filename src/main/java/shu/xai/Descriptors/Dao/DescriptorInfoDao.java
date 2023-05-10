package shu.xai.Descriptors.Dao;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import shu.xai.Descriptors.Entity.DescriptorInfo;
import shu.xai.Descriptors.Entity.TreeStruct;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class DescriptorInfoDao {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 条件分页查询
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param info 查询信息
     * @return 分页结果
     */
    public Page<DescriptorInfo> findByPage(int pageNum, int pageSize, DescriptorInfo info) {
//        新建查询对象
        Query query = new Query();
//        新建SQL对象
        Criteria criteria = new Criteria();
//        编写SQL条件语句
        if (info.getNodeName() != null && !info.getNodeName().isEmpty()) {
            criteria.and("NodeName").regex(info.getNodeName());
        }
        if (info.getIntroduce() != null && !info.getIntroduce().isEmpty()) {
            criteria.and("Introduce").regex(info.getIntroduce());
        }
        if (info.getZhName() != null && !info.getZhName().isEmpty()) {
            criteria.and("ZhName").regex(info.getZhName());
        }
        if (info.getConceptHierarchy() != null && !info.getConceptHierarchy().isEmpty()) {
            criteria.and("ConceptHierarchy").regex(info.getConceptHierarchy());
        }
        if (info.getSource() != null && !info.getSource().isEmpty()) {
            criteria.and("Source").regex(info.getSource());
        }


//        SQL语句插入到查询中
        query.addCriteria(criteria);
//        统计模糊查询数量
        long count = mongoTemplate.count(query, DescriptorInfo.class);
//        展开分页查询,确保重新查询的时候，pageNum-1不为0（无一条数据时候，pageNum为0，pageNum-1<0）
        Pageable pageable = PageRequest.of(Math.max(pageNum - 1, 0), pageSize);
//        加入分页查询配置
        query.with(pageable);
        List<DescriptorInfo> list = mongoTemplate.find(query, DescriptorInfo.class);
//        返回查询结果
        return PageableExecutionUtils.getPage(list, pageable, () -> count);
    }

//    根据节点名查询结点；若查询不到则返回一个新的结点
    public DescriptorInfo findByNodeName(String name) {
        Query query = new Query();
//        新建SQL对象
        Criteria criteria = new Criteria();
//        编写SQL条件语句
        if (name != null && !name.isEmpty()) {
            criteria.and("NodeName").regex(name,"i");
        }
        query.addCriteria(criteria);
        List<DescriptorInfo> descriptorInfos = mongoTemplate.find(query, DescriptorInfo.class);
//     找到正确的对象再返回，若没有正确的对象，则返回空
        for (DescriptorInfo info:descriptorInfos){
            if (info.getNodeName().length()==name.length())
                {
                    return info;
                }
        }
        return new DescriptorInfo();
    }


//    根据ID查询
    public DescriptorInfo findById(ObjectId id) {
        DescriptorInfo descriptorInfo = mongoTemplate.findById(id,DescriptorInfo.class);

        return descriptorInfo;
    }

    //    根据ID删除
    public DeleteResult removeById(ObjectId id) {
        //构建查询条件
        Query query = new Query(Criteria.where("_id").is(id));
        DeleteResult result = mongoTemplate.remove(query, DescriptorInfo.class); //删除方法
        long count = result.getDeletedCount();

        return result;
    }


/*修改——已有数据对象的情况下：save() 方法
    db.collection.save ( obj )
    obj 代表需要更新的对象，如果集合内部已经存在一个与 obj 相同的“_id”的记录，
    Mongodb 会把 obj 对象替换为集合内已存在的记录；如果不存在，则会插入 obj 对象。
*/
public DescriptorInfo upsertByObj(DescriptorInfo descriptorInfo) {
    return  mongoTemplate.save(descriptorInfo);
    }

//    根据节点名修改
public DescriptorInfo updateByNodeName(DescriptorInfo descriptorInfo,String OldName) {
//    根据OldName查到Id
    DescriptorInfo  Info= findByNodeName(OldName);
    ObjectId id = Info.getId();
//    更新id
    descriptorInfo.setId(id);
    return  mongoTemplate.save(descriptorInfo);
}
}

