package shu.xai.Descriptors.Vo;

import shu.xai.Descriptors.Entity.DescriptorInfo;
import shu.xai.Descriptors.Entity.TreeStruct;

import java.util.List;

//此java对象存储树形数据
public class AddTreeNode {
    private String Id;
    private String NodeName;
    private String ZhName;
    private String ConceptHierarchy;
    private String Introduce;
    private String Source;
    private String Formula;
//   与可视化中antV G6中type字段冲突，故修改为TreeType
    private String TreeType;
    private String Score;
    private List<AddTreeNode> Children;
//    待添加结点的父节点名字
    private String FatherName;
//    待修改结点的原名
    private String OldName;
//    根节点
    private String RootName;
// 水平层级
    private String LevelHierarchy;

    @Override
    public String toString() {
        return "AddTreeNode{" +
                "Id='" + Id + '\'' +
                ", NodeName='" + NodeName + '\'' +
                ", ZhName='" + ZhName + '\'' +
                ", ConceptHierarchy='" + ConceptHierarchy + '\'' +
                ", Introduce='" + Introduce + '\'' +
                ", Source='" + Source + '\'' +
                ", Formula='" + Formula + '\'' +
                ", TreeType='" + TreeType + '\'' +
                ", Score='" + Score + '\'' +
                ", Children=" + Children +
                ", FatherName='" + FatherName + '\'' +
                ", OldName='" + OldName + '\'' +
                ", RootName='" + RootName + '\'' +
                ", LevelHierarchy='" + LevelHierarchy + '\'' +
                '}';
    }

    public String getOldName() {
        return OldName;
    }

    public void setOldName(String oldName) {
        OldName = oldName;
    }

    public String getLevelHierarchy() {
        return LevelHierarchy;
    }

    public void setLevelHierarchy(String levelHierarchy) {
        LevelHierarchy = levelHierarchy;
    }

    public String getTreeType() {
        return TreeType;
    }

    public void setTreeType(String treeType) {
        TreeType = treeType;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
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

    public List<AddTreeNode> getChildren() {
        return Children;
    }

    public void setChildren(List<AddTreeNode> children) {
        Children = children;
    }

    public String getRootName() {
        return RootName;
    }

    public void setRootName(String rootName) {
        RootName = rootName;
    }
}
