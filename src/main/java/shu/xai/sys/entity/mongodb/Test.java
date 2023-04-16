//package shu.xai.sys.entity.mongodb;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.lang.reflect.Array;
//import java.lang.reflect.Type;
//
///**
// * Created by Yuan on 2023/3/14.
// */
//@Document(collection = "DescriptorTree")
//public class Test {
//    @Id
//    private String id;
//    private String NodeName;
//    private Boolean IsRoot;
//    private String RootName;
//    private Array ChildArray;
//
//    public String getNodeName() {
//        return NodeName;
//    }
//
//    public void setNodeName(String nodeName) {
//        NodeName = nodeName;
//    }
//
//    public Boolean getRoot() {
//        return IsRoot;
//    }
//
//    public void setRoot(Boolean root) {
//        IsRoot = root;
//    }
//
//    public String getRootName() {
//        return RootName;
//    }
//
//    public void setRootName(String rootName) {
//        RootName = rootName;
//    }
//
//    public Array getChildArray() {
//        return ChildArray;
//    }
//
//    public void setChildArray(Array childArray) {
//        ChildArray = childArray;
//    }
//
//    public String getConceptHierarchy() {
//        return ConceptHierarchy;
//    }
//
//    public void setConceptHierarchy(String conceptHierarchy) {
//        ConceptHierarchy = conceptHierarchy;
//    }
//
//    public int getLevelHierarchy() {
//        return LevelHierarchy;
//    }
//
//    public void setLevelHierarchy(int levelHierarchy) {
//        LevelHierarchy = levelHierarchy;
//    }
//
//    public String getIntroduce() {
//        return Introduce;
//    }
//
//    public void setIntroduce(String introduce) {
//        Introduce = introduce;
//    }
//
//    public String getSource() {
//        return Source;
//    }
//
//    public void setSource(String source) {
//        Source = source;
//    }
//
//    public String getScore() {
//        return Score;
//    }
//
//    public void setScore(String score) {
//        Score = score;
//    }
//
//    public String getFormula() {
//        return Formula;
//    }
//
//    public void setFormula(String formula) {
//        Formula = formula;
//    }
//
//    private String ConceptHierarchy;
//    private int LevelHierarchy;
//    private String Introduce;
//    private String Source;
//    private String Score;
//    private String Formula;
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//}
