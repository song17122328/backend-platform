package shu.xai.Descriptors.Contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shu.xai.Descriptors.Service.TreeNodeService;
import shu.xai.Descriptors.Service.TreeStructService;
import shu.xai.Descriptors.Entity.TreeStruct;
import shu.xai.Descriptors.Vo.TreeNode;

import java.util.List;

@RestController
@RequestMapping("/Tree")
public class TreeController {

    //   自动装配
    @Autowired
    private TreeNodeService treeNodeService;

    @GetMapping("/{type}")
    public TreeNode getDescriptorTreeByType(@PathVariable String type){
        return treeNodeService.getDescriptorTree(type,null);
    }

    @GetMapping("/{type}/{nodeName}")
    public TreeNode getDescriptorTreeByNode(@PathVariable String type,@PathVariable String nodeName){
        return treeNodeService.getDescriptorTree(type,nodeName);
    }
}
