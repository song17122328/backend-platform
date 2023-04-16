package shu.xai.Descriptors.Vo;

import shu.xai.Descriptors.Entity.DescriptorInfo;
import shu.xai.Descriptors.Entity.TreeStruct;

import java.util.List;

//此java对象存储树形数据
public class TreeNode {
    private String Id;
    private String NodeName;
    private String ZhName;
    private Integer LevelHierarchy;
    private String ConceptHierarchy;
    private String Introduce;
    private String Source;
    private String Formula;
//   可视化中与antV G6中type字段冲突，故去掉
//    private String Type;
    private String Score;
    private List<TreeNode> Children;



    public TreeNode(TreeStruct treeStruct, DescriptorInfo descriptorInfo) {
        Id = String.valueOf(treeStruct.getId().getCounter());
        NodeName = treeStruct.getNodeName();
        ZhName = descriptorInfo.getZhName();
        LevelHierarchy = treeStruct.getLevelHierarchy();

        ConceptHierarchy = descriptorInfo.getConceptHierarchy();
        Introduce = descriptorInfo.getIntroduce();
        Source = descriptorInfo.getSource();
        Formula = descriptorInfo.getFormula();
    }

    public TreeNode() {

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
//                ", Type='" + Type + '\'' +
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

}
