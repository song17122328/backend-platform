package shu.xai.Descriptors.Service;

import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import shu.xai.Descriptors.Dao.DescriptorInfoDao;
import shu.xai.Descriptors.Entity.DescriptorInfo;


@Service
public class DescriptorInfoService {

    @Autowired
    private DescriptorInfoDao descriptorInfoDao;

    /**
     * 分页查询
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param info 传输过来的数据
     * @return 分页结果
     */
    public Page<DescriptorInfo> findByPage(int pageNum, int pageSize, DescriptorInfo info) {
        return descriptorInfoDao.findByPage(pageNum, pageSize,info);
    }

    /**
     * 通过id查询
     * @param id 接收id
     * @return 查询对象
     */

    public DescriptorInfo findById(ObjectId id){
        return descriptorInfoDao.findById(id);
    }

    /**
     * 通过id删除
     * @param id 接收id
     * @return deleteCount，删除记录的条数
     */
    public DeleteResult removeById(ObjectId id){
        return descriptorInfoDao.removeById(id);
    }

    /**
     * 通过对象修改
     * @param Info 需要修改的对象数据
     * @return 修改后的对象数据
     */
    public DescriptorInfo upsertByObj(DescriptorInfo Info){
        System.out.println("通过对象修改被调用了");
        System.out.println(descriptorInfoDao.upsertByObj(Info));
        return descriptorInfoDao.upsertByObj(Info);
    }

    public DescriptorInfo updateByNodeName(DescriptorInfo descriptorInfo,String OldName) {
        return descriptorInfoDao.updateByNodeName(descriptorInfo,OldName);
    }
}
