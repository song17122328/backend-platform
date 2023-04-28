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
        return treeNodeService.getDescriptorTree(type,null);
    }

    @GetMapping("/{type}/{nodeName}")
    public TreeNode getDescriptorTreeByNode(@PathVariable String type,@PathVariable String nodeName){
        return treeNodeService.getDescriptorTree(type,nodeName);
    }

// 通过父节点名字和类型增加一个孩子
    @PutMapping()
    public String AddChildByFatherNode(@RequestBody AddTreeNode addTreeNode){
//        描述符信息表增加数据
        descriptorInfoService.upsertByObj(new DescriptorInfo(addTreeNode));
//        描述符结构表增加数据 1、增加新节点；2、修改父节点孩子数组
//        1、增加新节点
        treeStructService.upsertByObj(new TreeStruct(addTreeNode));
//        2、修改父节点的孩子数组
        TreeStruct father = treeStructService.findByTypeAndNodeName(addTreeNode.getTreeType(), addTreeNode.getFatherName());
//        修改其孩子数组
        ArrayList<String> children=father.getChildArray();
//        1、若孩子数组为空
        if(children==null){
            children=new ArrayList<String>();
        }
//        2、若已有此孩子
        for (String child:children) {
            if (Objects.equals(child, addTreeNode.getNodeName())) {
                return "已有此孩子节点";
            }
        }
        children.add(addTreeNode.getNodeName());
        father.setChildArray(children);
        treeStructService.upsertByObj(father);
        return "添加成功";
    }
//    通过新的信息修改节点
    @PostMapping()
    public String EditByNode(@RequestBody AddTreeNode addTreeNode){
//      获取需要的数据
        String newName = addTreeNode.getNodeName();
        String oldName = addTreeNode.getOldName();
        String treeType = addTreeNode.getTreeType();
//      修改信息关系：1、修改自身信息结点
        descriptorInfoService.updateByNodeName(new DescriptorInfo(addTreeNode),oldName);

//      修改结构关系：
//      0、构建父节点查询对象    1、找到父节点；    2、修改父节点的孩子数组  3、修改父节点
//      4、构造自身查询对象      5、查询自身结点    6、修改自身

//        0、构建父节点查询对象
        TreeStruct findFather = new TreeStruct();
        ArrayList<String> Children=new ArrayList<String>();
        Children.add(oldName);
        findFather.setChildArray(Children);
        findFather.setType(treeType);

//        1、查询父节点
        TreeStruct father = treeStructService.findByObj(findFather).get(0);
//        2、修改其孩子数组
        ArrayList<String> children=father.getChildArray();
        for (int i=0;i<children.size();i++) {
            if (Objects.equals(children.get(i), oldName)) {
                children.set(i, newName);
            }
        }
        father.setChildArray(children);
//        3、修改父节点
        treeStructService.upsertByObj(father);


//        4、构造自身查询对象
        TreeStruct findSelf = new TreeStruct();
        findSelf.setType(treeType);
        findSelf.setNodeName(oldName);
//        5、查询自身结点
        TreeStruct self = treeStructService.findByObj(findSelf).get(0);
//        6、修改自身
        self.setNodeName(newName);
        treeStructService.upsertByObj(self);
        return "添加成功";
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
        //      获取需要的数据
        String treeType = addTreeNode.getTreeType();
        String nodeName=addTreeNode.getNodeName();
        //        1、找到父节点：构建父节点查询对象
        ArrayList<String> Children=new ArrayList<String>();
        Children.add(nodeName);
        TreeStruct findFather = new TreeStruct();
        findFather.setChildArray(Children);
        findFather.setType(treeType);
//        查询到父节点
        TreeStruct father = treeStructService.findByObj(findFather).get(0);
        Children=father.getChildArray();
//        2、找到所有孩子
        List<AddTreeNode> childrenNode = addTreeNode.getChildren();
        for (AddTreeNode child:childrenNode)
        {
            Children.add(child.getNodeName());
        }
        //        移除自身
        Children.remove(nodeName);
        father.setChildArray(Children);
//        更新父节点
        treeStructService.upsertByObj(father);

//        删除自身结点,构建对象查询获取id
        TreeStruct struct = new TreeStruct();
        struct.setType(addTreeNode.getTreeType());
        struct.setNodeName(addTreeNode.getNodeName());
        ObjectId id = treeStructService.findByObj(struct).get(0).getId();
        treeStructService.removeById(id);
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
        //      获取需要的数据
        String treeType = addTreeNode.getTreeType();
        String nodeName=addTreeNode.getNodeName();
//        1、找到父节点：构建父节点查询对象
        ArrayList<String> Children=new ArrayList<String>();
        Children.add(nodeName);
        TreeStruct findFather = new TreeStruct();
        findFather.setChildArray(Children);
        findFather.setType(treeType);
//        查询到父节点
        TreeStruct father = treeStructService.findByObj(findFather).get(0);
//        2、修改其孩子数组
        ArrayList<String> children=father.getChildArray();
        children.remove(nodeName);
        father.setChildArray(children);
//        更新父节点
        treeStructService.upsertByObj(father);

//        4、递归删除该结点及该节点所有孩子结点
        DeleteRecursion(nodeName,treeType);
        return father.getNodeName();
    }

//    递归删除子树
    public void DeleteRecursion(String NodeName,String Type){
        //构建孩子结点，递归
        TreeStruct struct = new TreeStruct();
        struct.setType(Type);
        struct.setNodeName(NodeName);
        TreeStruct Node = treeStructService.findByObj(struct).get(0);
        ArrayList<String> childArray = Node.getChildArray();
        if (childArray!=null && childArray.size() >0) {
            for (String child:childArray){
//               找到到该完整节点
                DeleteRecursion(child,Type);
            }
        }
//        没有孩子则删除当前结点
        treeStructService.removeById(Node.getId());
    }



}
