package shu.xai.Descriptors.Dao;


import com.mongodb.client.result.DeleteResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import shu.xai.Descriptors.Entity.TreeStruct;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TreeStructDao {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 条件分页查询
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param struct 查询信息
     * @return 分页结果
     */
    public Page<TreeStruct> findByPage(int pageNum, int pageSize, TreeStruct struct) {
//        新建查询对象
        Query query = new Query();
//        新建SQL对象
        Criteria criteria = new Criteria();
//        编写SQL条件语句
        if (struct.getNodeName() != null && !struct.getNodeName().isEmpty()) {
            criteria.and("NodeName").regex(struct.getNodeName());
        }

        if (struct.getTreeType() != null && !struct.getTreeType().isEmpty()) {
            criteria.and("TreeType").regex(struct.getTreeType());
        }

        if (struct.getChildrenName() != null && !struct.getChildrenName().isEmpty()) {
            criteria.and("ChildrenName").is(struct.getChildrenName().get(0));
        }

//        SQL语句插入到查询中
        query.addCriteria(criteria);
//        统计模糊查询数量
        long count = mongoTemplate.count(query, TreeStruct.class);
//        展开分页查询,确保重新查询的时候，pageNum-1不为0（无一条数据时候，pageNum为0，pageNum-1<0）
        Pageable pageable = PageRequest.of(Math.max(pageNum - 1, 0), pageSize);
//        加入分页查询配置
        query.with(pageable);
        List<TreeStruct> list = mongoTemplate.find(query, TreeStruct.class);
//        返回查询结果
        return PageableExecutionUtils.getPage(list, pageable, () -> count);
    }

    public List<TreeStruct> findByObj(TreeStruct struct) {
//        新建查询对象
        Query query = new Query();
//        新建SQL对象
        Criteria criteria = new Criteria();
//        编写SQL条件语句
        if (struct.getNodeName() != null && !struct.getNodeName().isEmpty()) {
            criteria.and("NodeName").regex(struct.getNodeName());
        }

        if (struct.getTreeType() != null && !struct.getTreeType().isEmpty()) {
            criteria.and("TreeType").regex(struct.getTreeType());
        }

        if (struct.getChildrenName() != null && !struct.getChildrenName().isEmpty()) {
            criteria.and("ChildrenName").is(struct.getChildrenName().get(0));
        }

//        SQL语句插入到查询中
        query.addCriteria(criteria);
        return mongoTemplate.find(query, TreeStruct.class);
    }

//    根据类型查询
public List<TreeStruct> findByTreeType(String type) {
        // 新建查询对象
    Query query = new Query();
//        新建SQL对象
    Criteria criteria = new Criteria();
//        编写SQL条件语句
    if (type != null && !type.isEmpty()) {
        criteria.and("TreeType").is(type);
    }
    query.addCriteria(criteria);

    return mongoTemplate.find(query, TreeStruct.class);
}

    //    根据节点名查询结点
    public TreeStruct findByTypeAndNodeName(String type,String name) {
        Query query = new Query();
//        新建SQL对象
        Criteria criteria = new Criteria();
//        编写SQL条件语句
        if (name != null && !name.isEmpty()) {
            criteria.and("NodeName").is(name);
        }
        if (type != null && !type.isEmpty()) {
            criteria.and("TreeType").is(type);
        }
        query.addCriteria(criteria);
        return mongoTemplate.find(query, TreeStruct.class).get(0);
    }

//    根据ID查询
    public TreeStruct findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, TreeStruct.class);
    }



    //    根据ID删除

    public DeleteResult removeById(String id) {
        //构建查询条件
        Query query = new Query(Criteria.where("_id").is(id));
        DeleteResult result = mongoTemplate.remove(query, TreeStruct.class); //删除方法
        long count = result.getDeletedCount();

        return result;
    }


    /*修改——已有数据对象的情况下：save() 方法
        db.collection.save ( obj )
        obj 代表需要更新的对象，如果集合内部已经存在一个与 obj 相同的“_id”的记录，
        Mongodb 会把 obj 对象替换为集合内已存在的记录；如果不存在，则会插入 obj 对象。
    */
    public TreeStruct upsertByObj(TreeStruct treeStruct) {
        return  mongoTemplate.save(treeStruct);
        }




    public List<String> findAllDistinctTreeTypes() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("TreeType"),
                Aggregation.project().andExclude("_id").and("TreeType").previousOperation()
        );
        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "TreeStruct", Document.class);
        List<String> treeTypes = results.getMappedResults().stream().map(doc -> doc.getString("TreeType")).collect(Collectors.toList());
        return treeTypes;
    }
}

