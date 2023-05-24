package shu.xai.Descriptors.Service;

import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import shu.xai.Descriptors.Dao.MlDataDao;
import shu.xai.Descriptors.Query.MlDataQuery;
import shu.xai.Descriptors.Entity.MlData;

import java.util.List;


@Service
public class MlDataService {

    @Autowired
    private MlDataDao mlDataDao;

    /**
     * 分页查询
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param data 传输过来的数据
     * @return 分页结果
     */
    public Page<MlData> findByPage(int pageNum, int pageSize, MlDataQuery data) {
        return mlDataDao.findByPage(pageNum, pageSize,data);
    }

    /**
     * 通过id查询
     * @return 查询对象
     */
    public List<MlData> findAll(){
        return mlDataDao.findAll();
    }

    public MlData findById(ObjectId id){
        return mlDataDao.findById(id);
    }

    /**
     * 通过id删除
     * @param id 接收id
     * @return deleteCount，删除记录的条数
     */
    public DeleteResult removeById(ObjectId id){
        return mlDataDao.removeById(id);
    }

    /**
     * 通过对象修改
     * @param data 需要修改的对象数据
     * @return 修改后的对象数据
     */
    public MlData upsertByObj(MlData data){
        System.out.println("通过对象修改被调用了");
        System.out.println(mlDataDao.upsertByObj(data));
        return mlDataDao.upsertByObj(data);
    }
}
