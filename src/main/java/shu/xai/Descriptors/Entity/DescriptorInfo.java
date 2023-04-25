package shu.xai.Descriptors.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import shu.xai.Descriptors.Vo.AddTreeNode;
import shu.xai.Descriptors.Vo.TreeNode;


/**
 * Created by Yuan on 2023/3/14.
 */
@Document(collection = "DescriptorInfo")
public class DescriptorInfo {
    @Id
    private ObjectId id;
    private String NodeName;
    private String ZhName;
    private String Introduce;
    private String Source;
    private String Formula;
    private String ConceptHierarchy;

    public DescriptorInfo() {
    }
    public DescriptorInfo(AddTreeNode addTreeNode) {
        this.NodeName=addTreeNode.getNodeName();
        this.ZhName=addTreeNode.getZhName();
        this.Introduce=addTreeNode.getIntroduce();
        this.ConceptHierarchy=addTreeNode.getConceptHierarchy();
        this.Formula=addTreeNode.getFormula();
        this.Source=addTreeNode.getSource();
    }

    public DescriptorInfo(TreeNode treeNode) {
        this.NodeName=treeNode.getNodeName();
        this.ZhName=treeNode.getZhName();
        this.Introduce=treeNode.getIntroduce();
        this.ConceptHierarchy=treeNode.getConceptHierarchy();
        this.Formula=treeNode.getFormula();
        this.Source=treeNode.getSource();
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public String getConceptHierarchy() {
        return ConceptHierarchy;
    }

    public void setConceptHierarchy(String conceptHierarchy) {
        ConceptHierarchy = conceptHierarchy;
    }

    @Override
    public String toString() {
        return "DescriptorInfo{" +
                "id='" + id + '\'' +
                ", nodeName='" + NodeName + '\'' +
                ", ZhName='" + ZhName + '\'' +
                ", Introduce='" + Introduce + '\'' +
                ", Source='" + Source + '\'' +
                ", Formula='" + Formula + '\'' +
                ", ConceptHierarchy='" + ConceptHierarchy + '\'' +
                '}';
    }





}
