package shu.xai.Descriptors.Dao;


import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import shu.xai.Descriptors.Entity.MlData;
import shu.xai.Descriptors.Query.MlDataQuery;
import javax.annotation.Resource;
import java.util.List;

@Repository
public class MlDataDao {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 条件分页查询
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param queryData 查询信息
     * @return 分页结果
     */
    public Page<MlData> findByPage(int pageNum, int pageSize, MlDataQuery queryData) {
//        新建查询对象
        Query query = new Query();
//        新建SQL对象
        Criteria criteria = new Criteria();
//        编写SQL条件语句
//        ICSD为Integer类型,is 为精确查找；regex为模糊查找
        if (queryData.getICSD() != null) {
            criteria.and("ICSD").is(queryData.getICSD());
        }
        if (queryData.getChemicalFormula() != null && !queryData.getChemicalFormula().isEmpty()) {
            System.out.println(queryData.getChemicalFormula());
            criteria.and("ChemicalFormula").regex(queryData.getChemicalFormula());
        }

//      激活能BVSE_EnergyBarrier设置查询区间,gt为大于条件,gt>下限
        Criteria gt = null;
        Criteria lt = null;
        if (queryData.getLowerLimitEnergy() != null) {
            gt = Criteria.where("BVSEEnergyBarrier").gte(queryData.getLowerLimitEnergy());
        }
//      激活能BVSE_EnergyBarrier设置查询区间,lt小于上限值
        if (queryData.getUpperLimitMaxEnergy() != null) {
            lt = Criteria.where("BVSEEnergyBarrier").lte(queryData.getUpperLimitMaxEnergy());
        }
        if (gt != null && lt != null) {
            criteria.andOperator(gt, lt);
        } else if (gt != null) {
            criteria.andOperator(gt);
        } else if (lt != null) {
            criteria.andOperator(lt);
        }
        //        SQL语句插入到查询中
        query.addCriteria(criteria);
        System.out.println(MlData.class.getSimpleName() + "分页模糊查询的Dao被调用了");

        System.out.println(criteria);
//        统计模糊查询数量
        long count = mongoTemplate.count(query, MlData.class);
//        展开分页查询,确保重新查询的时候，pageNum-1不为0（无一条数据时候，pageNum为0，pageNum-1<0）
        Pageable pageable = PageRequest.of(Math.max(pageNum - 1, 0), pageSize);
//        加入分页查询配置
        query.with(pageable);
        System.out.println(query);
        List<MlData> list = mongoTemplate.find(query, MlData.class);
        System.out.println(list);
//        返回查询结果
        return PageableExecutionUtils.getPage(list, pageable, () -> count);
    }

//    根据ID查询
    public MlData findById(ObjectId id) {
        MlData mlData = mongoTemplate.findById(id,MlData.class);
        System.out.println("根据id查询的Dao被调用了,id为:"+id);
//        System.out.println(mlData);
        return mlData;
    }

    //    根据ID查询
    public DeleteResult removeById(ObjectId id) {
        //构建查询条件
        Query query = new Query(Criteria.where("_id").is(id));
        DeleteResult result = mongoTemplate.remove(query, MlData.class); //删除方法
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
public MlData upsertByObj(MlData mlData) {
    System.out.println(mlData.getId());
    return  mongoTemplate.save(mlData);
    }
}
