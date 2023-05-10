package shu.xai.Descriptors.Service;

import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import shu.xai.Descriptors.Dao.TreeStructDao;
import shu.xai.Descriptors.Entity.TreeStruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class TreeStructService {

    @Autowired
    private TreeStructDao treeStructDao;

    /**
     * 分页查询
     *
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @param info 传输过来的数据
     * @return 分页结果
     */
    public Page<TreeStruct> findByPage(int pageNum, int pageSize, TreeStruct info) {
        return treeStructDao.findByPage(pageNum, pageSize,info);
    }

    /**
     * 根据对象查询
     *
     * @param info 传输过来的数据
     * @return 查询结果哦
     */
    public List<TreeStruct> findByObj(TreeStruct info) {
        return treeStructDao.findByObj(info);
    }

    /**
     * 通过id查询
     * @param id 接收id
     * @return 查询对象
     */

    public TreeStruct findById(String id){
        return treeStructDao.findById(id);
    }

    /**
     * 通过id删除
     * @param id 接收id
     * @return deleteCount，删除记录的条数
     */
    public DeleteResult removeById(String id){
        return treeStructDao.removeById(id);
    }

    /**
     * 通过对象修改
     * @param struct 需要修改的对象数据
     * @return 修改后的对象数据
     */
    public TreeStruct upsertByObj(TreeStruct struct){
//        System.out.println("通过对象修改被调用了");
//        System.out.println(treeStructDao.upsertByObj(struct));
        return treeStructDao.upsertByObj(struct);
    }


    /**
     *  通过Type查询结点
     * @param type 类型
     * @return 查询列表
     */
    public List<TreeStruct> findByTreeType(String type){
        return treeStructDao.findByTreeType(type);
    }

    /**
     *  通过Type和NodeName查询结点
     * @param type 类型 ，
     * @param NodeName 节点名
     * @return 查询列表
     */
    public TreeStruct findByTypeAndNodeName(String type,String NodeName){
        return treeStructDao.findByTypeAndNodeName(type,NodeName);
    }


    public List<String> findAllDistinctTreeTypes() {
        return treeStructDao.findAllDistinctTreeTypes();
    }
}
