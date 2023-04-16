package shu.xai.Descriptors.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


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
