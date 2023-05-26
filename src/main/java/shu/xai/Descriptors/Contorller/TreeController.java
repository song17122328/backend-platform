package shu.xai.Descriptors.Contorller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shu.xai.Descriptors.Entity.DescriptorInfo;
import shu.xai.Descriptors.Service.DescriptorInfoService;
import shu.xai.Descriptors.Service.TreeNodeService;
import shu.xai.Descriptors.Service.TreeStructService;
import shu.xai.Descriptors.Entity.TreeStruct;
import shu.xai.Descriptors.Vo.AddTreeNode;
import shu.xai.Descriptors.Vo.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/Tree")
public class TreeController {

    //   自动装配
    @Autowired
    private TreeNodeService treeNodeService;

    @Autowired
    private DescriptorInfoService descriptorInfoService;

    @Autowired
    private TreeStructService treeStructService;

    @GetMapping("/{type}")
    public TreeNode getDescriptorTreeByType(@PathVariable String type){
        return treeNodeService. getDescriptorTree(type,null);
    }

    @GetMapping("/{type}/{nodeName}")
    public TreeNode getDescriptorTreeByNode(@PathVariable String type,@PathVariable String nodeName){
        return treeNodeService.getDescriptorTree(type,nodeName);
    }

// 通过父节点ID增加一个孩子,
    @PutMapping()
    public String AddChildByFatherNode(@RequestBody AddTreeNode addTreeNode){

//        System.out.println(addTreeNode);
//        1、找到父节点
        TreeStruct father = treeStructService.findById(addTreeNode.getParentId());
//        获取孩子数组
        ArrayList<String> childrenName=father.getChildrenName();
//        获取其孩子数组
        ArrayList<String> childrenId=father.getChildrenId();
//        1、若孩子数组为空
        if(childrenName==null){
            childrenName=new ArrayList<String>();
        }
        //
        if(childrenId==null){
            childrenId=new ArrayList<String>();
        }
//        2、若已有此孩子
        for (String childName:childrenName) {
            if (Objects.equals(childName, addTreeNode.getNodeName())) {
                return "已有此孩子节点";
            }
        }
//        若无此结点则增加
        //        描述符结构表增加数据 1、增加新节点；2、修改父节点孩子数组
//        1、增加新节点
        TreeStruct child = new TreeStruct(addTreeNode);
        String childNodeName = child.getNodeName();
        String childId = child.getId();

//        插入数据库
        treeStructService.upsertByObj(child);


        childrenName.add(childNodeName);
        childrenId.add(childId);
        father.setChildrenName(childrenName);
        father.setChildrenId(childrenId);
//        更新父节点
        treeStructService.upsertByObj(father);
//        System.out.println(father);
//        System.out.println(new TreeStruct(addTreeNode));
        //        描述符信息表增加数据
        descriptorInfoService.upsertByObj(new DescriptorInfo(addTreeNode));
        return "添加成功";
    }

//    通过新的信息修改节点
    @PostMapping()
    public String EditByNode(@RequestBody AddTreeNode addTreeNode){
//      获取需要的数据
        String newName = addTreeNode.getNodeName();
        String oldName = addTreeNode.getOldName();
        //      修改信息关系：1、修改自身信息结点
        descriptorInfoService.updateByNodeName(new DescriptorInfo(addTreeNode),oldName);

//        修改自身结构结构结点:先查询、更新节点名、通过对象修改
        TreeStruct byId = treeStructService.findById(addTreeNode.getId());
        byId.setNodeName(addTreeNode.getNodeName());
        treeStructService.upsertByObj(byId);

//      修改结构关系：
//  1、找到父节点；    2、修改父节点的孩子数组  3、修改父节点
//      4、构造自身查询对象      5、查询自身结点    6、修改自身


        String FatherId=treeStructService.findById(addTreeNode.getId()).getParentId();
//        1、查询父节点
        TreeStruct father = treeStructService.findById(FatherId);
//        2、修改其孩子数组
        System.out.println(father);
        ArrayList<String> childrenName=father.getChildrenName();
        for (int i=0;i<childrenName.size();i++) {
            if (Objects.equals(childrenName.get(i), oldName)) {
                childrenName.set(i, newName);
            }
        }
        father.setChildrenName(childrenName);
//        3、修改父节点
        treeStructService.upsertByObj(father);

        return "修改成功";
    }

    /**
     * 删除结点但保留孩子
     * @param addTreeNode
     * @return
     */
    @DeleteMapping()
    public String DeleteNode(@RequestBody AddTreeNode addTreeNode){
//          1、找到其父节点
//          2、把所有孩子结点移到其父节点上
//          3、删除当前子树
//操作如下

//        查询当前结点
        TreeStruct treeStruct = treeStructService.findById(addTreeNode.getId());
//        让所有孩子指向父节点
        if (treeStruct.getChildrenName()!=null){
            for (String childId:treeStruct.getChildrenId()){
                TreeStruct child = treeStructService.findById(childId);
                child.setParentId(treeStruct.getParentId());
                treeStructService.upsertByObj(child);
            }
        }
//        查询寻父节点
        String FatherID=treeStruct.getParentId();
        TreeStruct father = treeStructService.findById(FatherID);
//      让父节点指向所有孩子
        ArrayList<String> childrenId = father.getChildrenId();
        ArrayList<String> childrenName = father.getChildrenName();
//        2、找到所有孩子
        List<AddTreeNode> childrenNode = addTreeNode.getChildren();
        if(childrenNode!=null){
            for (AddTreeNode child:childrenNode)
            {
                childrenName.add(child.getNodeName());
                childrenId.add(child.getId());
            }
        }
        //        移除父元素里面的自身
        childrenName.remove(addTreeNode.getNodeName());
        childrenId.remove(addTreeNode.getId());
        father.setChildrenName(childrenName);
        father.setChildrenId(childrenId);
//        更新父节点
        treeStructService.upsertByObj(father);

//       删除自身节点
        treeStructService.removeById(treeStruct.getId());
        return father.getNodeName();

    }

    /**
     * 删除以当前结点为根节点的子树
     *   1、找到父节点
     *   2、删除父节点孩子数组中该节点
     *   3、递归删除该结点及该节点所有孩子结点
     * @param addTreeNode
     * @return
     */
    @DeleteMapping("/All")
    public String DeleteTree(@RequestBody AddTreeNode addTreeNode){

        TreeStruct treeStruct = treeStructService.findById(addTreeNode.getId());
        if(treeStruct==null){
            return "数据库无此数据，不需要删除";
        }
        String parentId = treeStruct.getParentId();
        if (parentId==null || parentId.length()==0){
//            说明是根节点，不需要修改其父亲
            //        4、递归删除该结点及该节点所有孩子结点
            DeleteRecursion(addTreeNode.getId());
            return "删除成功";
        }
//        查询到父节点
        TreeStruct father = treeStructService.findById(parentId);
//        2、修改其孩子数组
        ArrayList<String> childrenId=father.getChildrenId();
        ArrayList<String> childrenName = father.getChildrenName();
        childrenName.remove(treeStruct.getNodeName());
        childrenId.remove(treeStruct.getId());
        father.setChildrenId(childrenId);
        father.setChildrenName(childrenName);
//        更新父节点
        treeStructService.upsertByObj(father);

//        4、递归删除该结点及该节点所有孩子结点
        DeleteRecursion(addTreeNode.getId());
        return father.getNodeName();
    }

//    递归删除子树
    public void DeleteRecursion(String id){
        //构建孩子结点，递归
        TreeStruct Node = treeStructService.findById(id);
        System.out.println("node为:"+Node+"，id为："+id);
        ArrayList<String> childrenId = Node.getChildrenId();
        if (childrenId!=null && childrenId.size() >0) {
            for (String child:childrenId){
//               找到到该完整节点
                DeleteRecursion(child);
            }
        }
//        没有孩子则删除当前结点
        treeStructService.removeById(Node.getId());
    }
}
