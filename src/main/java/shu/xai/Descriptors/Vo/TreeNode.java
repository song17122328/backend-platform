package shu.xai.Descriptors.Vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import shu.xai.Descriptors.Entity.DescriptorInfo;
import shu.xai.Descriptors.Entity.TreeStruct;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

//此java对象存储树形数据
public class TreeNode implements Comparable<TreeNode>{
    @JsonProperty("_id")
    private String Id;
    private String NodeName;
    private String ZhName;
    private Integer LevelHierarchy;
    private String ConceptHierarchy;
    private String Introduce;
    private String Source;
    private String Formula;
//  若改树为融合树，则字段表示该结点来自哪棵树
    private String from;
    private String Score;
    private List<TreeNode> Children;
    private String TreeType;
    //    待添加结点的父节点名字
    private String FatherId;

    public String getFatherId() {
        return FatherId;
    }

    public void setFatherId(String fatherId) {
        FatherId = fatherId;
    }

    public TreeNode() {

    }
    public String getFrom() {
        return from;
    }

    public TreeNode TreeNodeForFusion(TreeNode treeNode){
        TreeNode node = new TreeNode();
        node.setId(treeNode.getId());
        node.setNodeName(treeNode.getNodeName());
        node.setZhName(treeNode.getZhName()) ;
        node.setLevelHierarchy(treeNode.getLevelHierarchy());
        node.setConceptHierarchy(treeNode.getConceptHierarchy()) ;
        node.setIntroduce(treeNode.getIntroduce()) ;
        node.setSource(treeNode.getSource()) ;
        node.setFormula(treeNode.getFormula()) ;
        node.setScore(treeNode.getScore()) ;
        node.setFrom(treeNode.getFrom());
        node.setTreeType(treeNode.getTreeType());
        node.setFatherId(treeNode.getFatherId());
        return node;
    }

    //    重写排序方法
    @Override
    public int compareTo(TreeNode o) {
        return this.NodeName.length() - o.NodeName.length();
    }

    public String getTreeType() {
        return TreeType;
    }

    public void setTreeType(String treeType) {
        TreeType = treeType;
    }

    public void setFrom(String from) {
        this.from = from;
    }
    public TreeNode(TreeStruct treeStruct, DescriptorInfo descriptorInfo) {
        Id = treeStruct.getId();
        NodeName = treeStruct.getNodeName();
        ZhName = descriptorInfo.getZhName();
        from = treeStruct.getTreeType();
        TreeType="fusion";
        FatherId=treeStruct.getParentId();

        ConceptHierarchy = descriptorInfo.getConceptHierarchy();
        Introduce = descriptorInfo.getIntroduce();
        Source = descriptorInfo.getSource();
        Formula = descriptorInfo.getFormula();
    }


    @Override
    public String toString() {
        return "Tree{" +
                "id='" + Id + '\'' +
                ", NodeName='" + NodeName + '\'' +
                ", ZhName='" + ZhName + '\'' +
                ", LevelHierarchy=" + LevelHierarchy +
                ", ConceptHierarchy='" + ConceptHierarchy + '\'' +
                ", Introduce='" + Introduce + '\'' +
                ", Source='" + Source + '\'' +
                ", Formula='" + Formula + '\'' +
                ", from='" + from + '\'' +
                ", Score='" + Score + '\'' +
                ", Children=" + Children +
                '}';
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getNodeName() {
        return NodeName;
    }

    public void setNodeName(String nodeName) {
        NodeName = nodeName;
    }

    public String getZhName() {
        return ZhName;
    }

    public void setZhName(String zhName) {
        ZhName = zhName;
    }

    public Integer getLevelHierarchy() {
        return LevelHierarchy;
    }

    public void setLevelHierarchy(Integer levelHierarchy) {
        LevelHierarchy = levelHierarchy;
    }

    public String getConceptHierarchy() {
        return ConceptHierarchy;
    }

    public void setConceptHierarchy(String conceptHierarchy) {
        ConceptHierarchy = conceptHierarchy;
    }

    public String getIntroduce() {
        return Introduce;
    }

    public void setIntroduce(String introduce) {
        Introduce = introduce;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getFormula() {
        return Formula;
    }

    public void setFormula(String formula) {
        Formula = formula;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public List<TreeNode> getChildren() {
        return Children;
    }

    public void setChildren(List<TreeNode> children) {
        Children = children;
    }

    public void addChild(TreeNode treeNode) {
        TreeNode child = TreeNodeForFusion(treeNode);
        if (Children==null) {
            Children= new ArrayList<>();
        }
        Children.add(child);
    }
}
