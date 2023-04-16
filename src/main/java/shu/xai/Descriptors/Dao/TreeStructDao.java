package shu.xai.Descriptors.Dao;


import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import shu.xai.Descriptors.Entity.DescriptorInfo;
import shu.xai.Descriptors.Entity.TreeStruct;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Pageable;
import javax.annotation.Resource;
import java.util.List;

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
        if (struct.getRootName() != null && !struct.getRootName().isEmpty()) {
            criteria.and("RootName").regex(struct.getRootName());
        }
        if (struct.getType() != null && !struct.getType().isEmpty()) {
            criteria.and("Type").regex(struct.getType());
        }
    //        LevelHierarchy为Integer类型,is 为精确查找；regex为模糊查找
        if (struct.getLevelHierarchy() != null) {
            System.out.println(struct.getLevelHierarchy());
            criteria.and("LevelHierarchy").is(struct.getLevelHierarchy());
        }
        if (struct.getChildArray() != null && !struct.getChildArray().isEmpty()) {
            criteria.and("ChildArray").is(struct.getChildArray().get(0));
        }

        System.out.println("分页模糊查询的Dao被调用了");
        System.out.println(criteria);
//        SQL语句插入到查询中
        query.addCriteria(criteria);
//        统计模糊查询数量
        long count = mongoTemplate.count(query, TreeStruct.class);
//        展开分页查询,确保重新查询的时候，pageNum-1不为0（无一条数据时候，pageNum为0，pageNum-1<0）
        Pageable pageable = PageRequest.of(Math.max(pageNum - 1, 0), pageSize);
//        加入分页查询配置
        query.with(pageable);
        System.out.println(query);
        List<TreeStruct> list = mongoTemplate.find(query, TreeStruct.class);
        System.out.println(list);
//        返回查询结果
        return PageableExecutionUtils.getPage(list, pageable, () -> count);
    }

//    根据类型查询
public List<TreeStruct> findByType(String type) {
        // 新建查询对象
    Query query = new Query();
//        新建SQL对象
    Criteria criteria = new Criteria();
//        编写SQL条件语句
    if (type != null && !type.isEmpty()) {
        criteria.and("Type").is(type);
    }
    query.addCriteria(criteria);
    System.out.println("通过类型精确匹配findByType被调用了");
    System.out.println(query);
    return mongoTemplate.find(query, TreeStruct.class);
}

    //    根据节点名查询结点
    public TreeStruct findByNodeName(String name) {
        Query query = new Query();
//        新建SQL对象
        Criteria criteria = new Criteria();
//        编写SQL条件语句
        if (name != null && !name.isEmpty()) {
            criteria.and("NodeName").is(name);
        }
        query.addCriteria(criteria);
        return mongoTemplate.find(query, TreeStruct.class).get(0);
    }

//    根据ID查询
    public TreeStruct findById(ObjectId id) {
        TreeStruct descriptorInfo = mongoTemplate.findById(id,TreeStruct.class);
        System.out.println("根据id查询的Dao被调用了,id为:"+id);
//        System.out.println(descriptorInfo);
        return descriptorInfo;
    }

    //    根据ID删除
    public DeleteResult removeById(ObjectId id) {
        //构建查询条件
        Query query = new Query(Criteria.where("_id").is(id));
        DeleteResult result = mongoTemplate.remove(query, TreeStruct.class); //删除方法
        long count = result.getDeletedCount();
        System.out.println(count);
        System.out.println("根据id删除的Dao被调用了,id为:"+id);
        return result;
    }


/*修改——已有数据对象的情况下：save() 方法
    db.collection.save ( obj )
    obj 代表需要更新的对象，如果集合内部已经存在一个与 obj 相同的“_id”的记录，
    Mongodb 会把 obj 对象替换为集合内已存在的记录；如果不存在，则会插入 obj 对象。
*/
public TreeStruct upsertByObj(TreeStruct descriptorInfo) {
    System.out.println(descriptorInfo.getId());
    return  mongoTemplate.save(descriptorInfo);
    }
}
