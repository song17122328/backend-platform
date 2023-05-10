package shu.xai.Descriptors.Contorller;

import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;
import org.springframework.data.domain.*;
import shu.xai.Descriptors.Entity.DescriptorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shu.xai.Descriptors.Service.DescriptorInfoService;

@RestController
@RequestMapping("/DescriptorInfo")
public class DescriptorInfoController {

//   自动装配
    @Autowired
    private DescriptorInfoService descriptorInfoService;


//    基于分页查询的模糊查询 基于模糊查询的分页查询：逻辑：先模糊查询，再包装对象，分页显示。
    @GetMapping("{PageNum}/{PageSize}")
    public Page<DescriptorInfo> GetAll(@PathVariable int PageNum,@PathVariable int PageSize,
                                       DescriptorInfo Info){

        Page<DescriptorInfo> page=descriptorInfoService.findByPage(PageNum,PageSize,Info);
        // 如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (PageNum > page.getTotalPages()) {
            return descriptorInfoService.findByPage(page.getTotalPages(), PageSize, Info);
        }
        return descriptorInfoService.findByPage(PageNum,PageSize,Info);
    }

    //通过id查询
    @PostMapping()
    public DescriptorInfo GetById(@RequestBody ObjectId Id){
        System.out.println("通过id查询被调用了");
        return descriptorInfoService.findById(Id);
    }
    //    通过Id删除
    @PostMapping("/delete")
    public DeleteResult DeleteById(@RequestBody ObjectId Id){
        System.out.println("通过id删除被调用了");
        return descriptorInfoService.removeById(Id);
    }

    //    通过对象修改
    @PutMapping()
    public DescriptorInfo Upsert(@RequestBody DescriptorInfo info){
        System.out.println("通过对象修改被调用了");
        return descriptorInfoService.upsertByObj(info);
    }


}
