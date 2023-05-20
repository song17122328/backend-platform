package shu.xai.Descriptors.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shu.xai.Descriptors.Dao.DescriptorInfoDao;
import shu.xai.Descriptors.Dao.TreeStructDao;
import shu.xai.Descriptors.Entity.DescriptorInfo;
import shu.xai.Descriptors.Entity.TreeStruct;
import shu.xai.Descriptors.Vo.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TreeNodeService {

    @Autowired
    DescriptorInfoDao descriptorInfoDao;

    @Autowired
    TreeStructDao treeStructDao;
    /**
     * 传入一个TreeStruct类型的结点，改造成一个完整类型的Tree结点
     *
     * @param StructNode TreeStruct类型的结点
     * @return TreeNode结点
     */
    private TreeNode transformToTreeNode(TreeStruct StructNode) {
        String nodeName = StructNode.getNodeName();
        DescriptorInfo InfoNode = descriptorInfoDao.findByNodeName(nodeName);
        return new TreeNode(StructNode, InfoNode);
    }

    /**
     * 得到根节点
     *
     * @param treeStructs 同一类型树的跟结点列表
     * @return 根节点
     */
    private TreeStruct getRoot(List<TreeStruct> treeStructs) {
        for (TreeStruct treeStruct : treeStructs) {
//            根节点没有父节点，故根节点父节点为空
            if(treeStruct.getParentId()==null || treeStruct.getParentId().isEmpty()){
                return treeStruct;
            }
        }
        return null;
    }

    /**
     * 得到孩子结点列表，给出一个父节点，得到它的孩子结点列表
     *  @param treeStructs 查询获得的所有treeStruct数据
     * @param FatherNode 顶级节点
     * @return childTreeStructList 孩子结点列表
     */
    private List<TreeStruct> getChildNodeList(List<TreeStruct> treeStructs, TreeStruct FatherNode) {
        ArrayList<TreeStruct> childTreeStructList = new ArrayList<>();
        ArrayList<String> childIdArray = FatherNode.getChildrenId();
        // 如果孩子数组不为空，则遍历孩子数组，生成孩子结点列表
        if (childIdArray!=null && childIdArray.size()!=0){
            for (String childId: childIdArray)
            {
//                遍历所有结点列表，把列表结点加入到孩子结点列表
                for (TreeStruct treeStruct : treeStructs){
                    if (treeStruct.getId().equals(childId)) {
//                      返回TreeStruct类型的列表，生成的孩子结点列表为完整的TreeNode孩子结点列表
                        childTreeStructList.add(treeStruct);
                        }
                    }
                }
            }
        return childTreeStructList;
    }


    /**
     * 判断是否有子节点
     *
     * @param list 节点列表
     * @param treeStruct 部门节点
     * @return Boolean
     */
    private boolean hasChild(List<TreeStruct> list, TreeStruct treeStruct) {
        return getChildNodeList(list, treeStruct).size() > 0;
    }
    /**
     * 递归列表
     * 结束条件为所遍历的节点无下一级节点
     *
     * @param list 查询获得的所有数据
     * @param root 顶级节点
     */
    private void recursionFind(TreeStruct root,List<TreeStruct> list, TreeNode treeNode) {
        // 得到结构子节点列表
        List<TreeStruct> TreeStructList = getChildNodeList(list, root);

        List<TreeNode> TreeNodeList=new ArrayList<>();
        for (TreeStruct SChild : TreeStructList) {
            TreeNodeList.add(transformToTreeNode(SChild));
        }//        得到完整子结点列表


//        添加完整孩子结点列表
        treeNode.setChildren(TreeNodeList);

        for (int i = 0; i < TreeStructList.size(); i++) {
            // 如果子节点有下一级节点，得到下一级的节点列表
            if (hasChild(list, TreeStructList.get(i))) {
                recursionFind(TreeStructList.get(i),list, TreeNodeList.get(i));
            }
        }
    }

    /**
     * 构建前端所需要树结构
     *
     * @param treeStructs 结构结点列表
     * @return 树结构列表
     */
    private TreeNode buildDeepTree(TreeStruct localNode,List<TreeStruct> treeStructs) {
//        如果不传结点，默认从根节点起构建嵌套树
        if (localNode==null){
            localNode=getRoot(treeStructs);
        }
//        根节点转换
        TreeNode treeNode=transformToTreeNode(localNode);
//        递归向下扩建根节点
        recursionFind(localNode, treeStructs,treeNode);
        treeNode.setFrom(localNode.getTreeType());
        return treeNode;
    }



    /**
     * 调用方法，结果返回前端需要的树形结构数据
     *
     * @param type 描述符树所属类型
     * @param NodeName 节点名
     * @return 构建的描述符树
     */
    public TreeNode getDescriptorTree(String type,String NodeName) {
        List<TreeStruct> Structs=treeStructDao.findByTreeType(type);
        if (NodeName!=null){
            TreeStruct localNode = treeStructDao.findByTypeAndNodeName(type,NodeName);
            return buildDeepTree(localNode,Structs);
        }
        return buildDeepTree(null,Structs);
    }
}
