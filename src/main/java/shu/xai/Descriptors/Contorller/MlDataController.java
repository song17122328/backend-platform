package shu.xai.Descriptors.Contorller;

import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;
import org.springframework.data.domain.*;
import shu.xai.Descriptors.Query.MlDataQuery;
import shu.xai.Descriptors.Entity.MlData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shu.xai.Descriptors.Service.MlDataService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/MlData")
public class MlDataController {

    //   自动装配
    @Autowired
    private MlDataService mlDataService;


    //    基于分页查询的模糊查询 基于模糊查询的分页查询：逻辑：先模糊查询，再包装对象，分页显示。
    @GetMapping("")
    public List<MlData> GetAll(){
        return mlDataService.findAll();
    }


    //    基于分页查询的模糊查询 基于模糊查询的分页查询：逻辑：先模糊查询，再包装对象，分页显示。
    @GetMapping("{PageNum}/{PageSize}")
    public Page<MlData> GetAll(@PathVariable int PageNum,@PathVariable int PageSize,
                                   MlDataQuery data){
        System.out.println("查询函数被调用了");
        System.out.println(PageNum);
        System.out.println(PageSize);
        System.out.println(data);
        Page<MlData> page=mlDataService.findByPage(PageNum,PageSize,data);
        System.out.println(page.toString());
        // 如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (PageNum > page.getTotalPages()) {
            return mlDataService.findByPage((int) page.getTotalPages(), PageSize, data);
        }
        return mlDataService.findByPage(PageNum,PageSize,data);
    }

    //通过id查询
    @PostMapping()
    public MlData GetById(@RequestBody ObjectId Id){
        System.out.println("通过id查询被调用了");
        return mlDataService.findById(Id);
    }
    //    通过Id删除
    @PostMapping("/delete")
    public DeleteResult DeleteById(@RequestBody ObjectId Id){
        System.out.println("通过id删除被调用了");
        return mlDataService.removeById(Id);
    }

    //    通过对象修改
    @PutMapping()
    public MlData Upsert(@RequestBody MlData data){
        System.out.println("通过对象修改被调用了");
        return mlDataService.upsertByObj(data);
    }

}
