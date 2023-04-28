package shu.xai.Descriptors.Contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shu.xai.Descriptors.Service.TreeNodeService;
import shu.xai.Descriptors.Vo.TreeNode;
import shu.xai.Descriptors.Service.function.FusionTreeAlgorithm;

@RestController
@RequestMapping("/FusionTree")
public class FusionTreeController {

    @Autowired
    TreeNodeService treeNodeService;

    @Autowired
    FusionTreeAlgorithm fusionTreeAlgorithm;

    //    传入两棵树的种类,构建出一颗融合树
    @GetMapping("/{TypeA}/{TypeB}")
    public TreeNode getFusionTree(@PathVariable String TypeA, @PathVariable String TypeB) {
        TreeNode TreeA = treeNodeService.getDescriptorTree(TypeA, null);
        TreeNode TreeB = treeNodeService.getDescriptorTree(TypeB, null);
//        System.out.println(TreeA);
//        System.out.println(TreeB);
        return fusionTreeAlgorithm.GetFusionTree(TreeA,TreeB);
    }
}
