package shu.xai.Descriptors.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import shu.xai.Descriptors.Vo.AddTreeNode;
import shu.xai.Descriptors.Vo.TreeNode;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Yuan on 2023/3/14.
 */
@Document(collection = "TreeStruct")
public class TreeStruct {
    @Id
    private ObjectId id;
    private String NodeName;
    private String RootName;
    private ArrayList<String> ChildArray;
    private Integer LevelHierarchy;
    private String Type;

    public TreeStruct() {
    }


    public TreeStruct(AddTreeNode addTreeNode) {
        System.out.println(addTreeNode);
        this.NodeName=addTreeNode.getNodeName();
        this.RootName=addTreeNode.getRootName();
        this.Type=addTreeNode.getTreeType();
        this.LevelHierarchy= Integer.valueOf(addTreeNode.getLevelHierarchy());
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

    public String getRootName() {
        return RootName;
    }

    public void setRootName(String rootName) {
        RootName = rootName;
    }

    public ArrayList<String> getChildArray() {
        return ChildArray;
    }

    public void setChildArray(ArrayList<String> childArray) {
        ChildArray = childArray;
    }

    public Integer getLevelHierarchy() {
        return LevelHierarchy;
    }

    public void setLevelHierarchy(Integer levelHierarchy) {
        LevelHierarchy = levelHierarchy;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "TreeStruct{" +
                "id='" + id + '\'' +
                ", NodeName='" + NodeName + '\'' +
                ", RootName='" + RootName + '\'' +
                ", ChildArray=" + ChildArray +
                ", LevelHierarchy=" + LevelHierarchy +
                ", Type='" + Type + '\'' +
                '}';
    }



}
