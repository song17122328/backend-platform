package shu.xai.Descriptors.Contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shu.xai.Descriptors.Service.TreeNodeService;
import shu.xai.Descriptors.Vo.TreeNode;
import shu.xai.Descriptors.Service.function.FusionTreeAlgorithm;

import java.util.ArrayList;

@RestController
@RequestMapping("/FusionTree")
public class FusionTreeController {

    @Autowired
    TreeNodeService treeNodeService;

    @Autowired
    FusionTreeAlgorithm fusionTreeAlgorithm;

    //    传入两棵树的种类,构建出一颗融合树
    @PostMapping()
        public TreeNode getFusionTree(@RequestBody ArrayList<String> Types) {
        int size = Types.size();
        ArrayList<TreeNode> Trees = new ArrayList<>();
        for (String type : Types) {
            Trees.add(treeNodeService.getDescriptorTree(type, null));
        }
        if (size==0){ return null;}
        if (size==1){ return Trees.get(0);}
        TreeNode fusion=fusionTreeAlgorithm.GetFusionTree(Trees.get(0),Trees.get(1));
        if (size>2){
            for (int i=2;i<size;i++){
                fusion=fusionTreeAlgorithm.GetFusionTree(fusion, Trees.get(i));
            }
        }
        return fusion;
    }
}
