package shu.xai.Descriptors.Contorller;

import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shu.xai.Descriptors.Entity.TreeStruct;
import shu.xai.Descriptors.Service.TreeStructService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/TreeStruct")
public class TreeStructController {

    //   自动装配
    @Autowired
    private TreeStructService treeStructService;


    //    基于分页查询的模糊查询 基于模糊查询的分页查询：逻辑：先模糊查询，再包装对象，分页显示。
    @GetMapping("{PageNum}/{PageSize}")
    public Page<TreeStruct> GetAll(@PathVariable int PageNum,@PathVariable int PageSize,
                                       TreeStruct struct){

        Page<TreeStruct> page=treeStructService.findByPage(PageNum,PageSize,struct);
//        System.out.println(page.toString());
        // 如果当前页码值大于总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (PageNum > page.getTotalPages()) {
            return treeStructService.findByPage((int) page.getTotalPages(), PageSize, struct);
        }
        return treeStructService.findByPage(PageNum,PageSize,struct);
    }

    @GetMapping("/types")
    public List<String> getAllTreeTypes() {
        List<String> types = treeStructService.findAllDistinctTreeTypes();
        return types;
    }

    //通过id查询
    @PostMapping()
    public TreeStruct GetById(@RequestBody Map<String, String> id){
        String Id=id.get("id");
//        System.out.println("通过id查询被调用了,接收的id是"+Id);
        return treeStructService.findById(Id);
    }
    //通过Id删除
    @PostMapping("/delete")
    public DeleteResult DeleteById(@RequestBody Map<String, String> id){
        String Id=id.get("id");
//        System.out.println("通过id删除被调用了");
        return treeStructService.removeById(Id);
    }

    //    通过对象修改
    @PutMapping()
    public TreeStruct Upsert(@RequestBody TreeStruct info){
//        System.out.println("通过对象修改被调用了");
        return treeStructService.upsertByObj(info);
    }


}
