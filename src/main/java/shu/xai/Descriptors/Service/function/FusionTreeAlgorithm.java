package shu.xai.Descriptors.Service.function;



import org.springframework.stereotype.Service;
import shu.xai.Descriptors.Vo.TreeNode;

import java.util.*;


/**
 * 外层准备函数：GetFusionTree(A,B)；return 融合树C
 *  递归融合算法 Recursion(A,B,C)； 无返回值，递归把C补全为融合树
 *  1、判断A==B？
 *      if(A==B) 则令C=commonAB(A=B)，判断A,B孩子数组是否相同，P(A,B)
 *          if(Judge(A,B))
 *              遍历A的children，对每一个A.child, 找到其对应的B.child。递归执行R(A.child,B.child)
 *           if(!Judge(A,B))
 *              对A，B执行子树融合M(A,B)；C.children=M(A,B)
 *  2、if(A!=B) 程序出错
 */

/**
 * 判断孩子数组是否相同：双层循环，只判断孩子的节点名是否一致，不深入判断——不判断孩子的孩子是否相同；
 *  Judge(A,B); return TRUE,FALSE
 * 1、A.children==B.children?
 *      若相同，则返回True
 *      若不同，则返回False
 */

/**
 * 已知：A=B，但A.children!=B.children; 返回C.children= A孩子数组和B孩子数组的并集
 * 子树融合算法  M(A,B); return C; C.Children = A.children [U] B.children
 * bool find=false
 * 1、for(childA:A.children)-------childA
 *       for(childB:B.children)----------------childrenB
 *          if(childA==childB)
 *              {
 *                   find=true;
 *               }
 *              break;     只判断A，故跳出
 *        if(find==true)  C.commonAB=childA;
 *        if(find==false) C.fromA   =childA;
 *  2、令临时变量 temp=childrenB
 *              temp.remove(C.commonAB)
 *              C.fromB=temp
 */


// 融合树算法类
@Service
public class FusionTreeAlgorithm {

//    外层函数
    public TreeNode GetFusionTree(TreeNode A,TreeNode B){
//        新建一棵树作为融合树，执行融合算法，向融合树中增加结点
        TreeNode FusionTree = new TreeNode();
        FusionTree.setId(String.valueOf(UUID.randomUUID()));
        FusionTree.setNodeName(A.getFrom()+"and"+B.getFrom());
        FusionTree.setFrom("mix");
        FusionTree.setTreeType("fusion");
        FusionTree.setConceptHierarchy("FusionTree");
        FusionTree.setNodeName("FusionTreeRoot");
        FusionTree.setZhName("融合树根节点");
//        递归融合
        Recursion(A,B,FusionTree);

        return FusionTree;
    }

//    递归融合
    public void Recursion(TreeNode A, TreeNode B,TreeNode C){
//        如果孩子数组相等，则递归对比每一个孩子
        if (A.getChildren()==null) {
//            System.out.println("A孩子为空");
            A.setChildren(new ArrayList<TreeNode>());
        }
        if (B.getChildren()==null) {
//            System.out.println("B孩子为空");
            B.setChildren(new ArrayList<TreeNode>());
        }
        if (Judge(A.getChildren(),B.getChildren())) {
            for (int i=0;i<A.getChildren().size();i++){
                if (Objects.equals(A.getChildren().get(i).getNodeName(), B.getChildren().get(i).getNodeName()))
                {
                    TreeNode node=A.getChildren().get(i);
                    node.setFrom("mix");
                    node.setTreeType("fusion");
                    C.addChild(node);
//                    System.out.println("C"+C);
                    Recursion(A.getChildren().get(i),B.getChildren().get(i),C.getChildren().get(i));
                }
                else
                {
                    System.out.println("孩子数组相同，但是指定位置的元素不同，请修改逻辑");
                }

            }
        }
//        如果孩子数组不同，则把两棵树A,B的孩子数组融合到一起
        else{
            Merger(A,B,C);
        }
    }

//        判断两个孩子数组的节点名是否相同
    public boolean Judge(List<TreeNode> ChildrenA, List<TreeNode> ChildrenB){
//        按照规则排序

        if (ChildrenA.size()>=2){
            Collections.sort(ChildrenA);
        }
        if (ChildrenB.size()>=2){
            Collections.sort(ChildrenB);
        }
        String[] ArrayA= new String[ChildrenA.size()];
        String[] ArrayB= new String[ChildrenB.size()];
        for (int i=0;i<ChildrenA.size();i++){
            ArrayA[i]=ChildrenA.get(i).getNodeName();
        }
        for (int i=0;i<ChildrenB.size();i++){
            ArrayB[i]=ChildrenB.get(i).getNodeName();
        }
        return Arrays.equals(ArrayA,ArrayB);
    }


    public void Merger(TreeNode A, TreeNode B,TreeNode C){
        ArrayList<TreeNode> CChildren = new ArrayList<>();
        ArrayList<TreeNode> AChildren = new ArrayList<TreeNode>(A.getChildren());
        ArrayList<TreeNode> BChildren = new ArrayList<TreeNode>(B.getChildren());
        int k=0;
        for(int i=0;i<A.getChildren().size();i++) {
            for(int j=0;j<B.getChildren().size();j++){
//                System.out.println(B.getChildren().size());
                if (A.getChildren().get(i).getNodeName().equalsIgnoreCase(B.getChildren().get(j).getNodeName())) {

//                    碰到相同的结点做融合操作
                    TreeNode node=A.getChildren().get(i);
                    node.setFrom("mix");
                    node.setTreeType("fusion");
                    C.addChild(node);
                    Recursion(A.getChildren().get(i),B.getChildren().get(j),C.getChildren().get(k));
                    k=k+1;
//                共同元素已经添加，移除A,B里面的共同元素。使得A,B剩下的数组没有共同元素
                    AChildren.remove(A.getChildren().get(i));
                    BChildren.remove(B.getChildren().get(j));
//                    System.out.println(CChildren);
                    break; //找到共同元素，则下面不需要再次寻找，跳出B的循环
                }
            }
        }


//        处理孩子数组B，此时B还存在的元素，不包含A,B共同元素
        CChildren.addAll(AChildren);
        CChildren.addAll(BChildren);
        for (TreeNode child:CChildren){
            child.setTreeType("fusion");
        }
        if (C.getChildren()==null){
            C.setChildren(new ArrayList<TreeNode>());
        }
        C.getChildren().addAll(CChildren);
    }

    public void UpdateId(TreeNode fusion){
        fusion.setId(fusion.getId()+"_fusion");
        fusion.setFatherId(fusion.getFatherId()+"_fusion");
        if (fusion.getChildren()!=null &&fusion.getChildren().size()!=0){
            for(TreeNode child:fusion.getChildren()){
                UpdateId(child);
            }
        }
    }
}
