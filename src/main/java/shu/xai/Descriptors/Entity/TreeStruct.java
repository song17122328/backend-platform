package shu.xai.Descriptors.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import shu.xai.Descriptors.Vo.AddTreeNode;

import java.util.ArrayList;
import java.util.UUID;


/**
 * Created by Yuan on 2023/3/14.
 */
@Document(collection = "TreeStruct")
public class TreeStruct {
    @Id
    private String Id;
    private String NodeName;
    private String ParentId;
    private ArrayList<String> ChildrenId;
    private ArrayList<String> ChildrenName;
    private String TreeType;

    public TreeStruct(AddTreeNode addTreeNode) {
        Id = addTreeNode.getId();
        NodeName=addTreeNode.getNodeName();
        ParentId=addTreeNode.getParentId();
        TreeType=addTreeNode.getTreeType();
        ChildrenId=new ArrayList<>();
        ChildrenName=new ArrayList<>();

    }

    public TreeStruct() {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNodeName() {
        return NodeName;
    }

    public void setNodeName(String nodeName) {
        NodeName = nodeName;
    }


    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }

    public ArrayList<String> getChildrenId() {
        return ChildrenId;
    }

    public void setChildrenId(ArrayList<String> childrenId) {
        ChildrenId = childrenId;
    }

    public ArrayList<String> getChildrenName() {
        return ChildrenName;
    }

    public void setChildrenName(ArrayList<String> childrenName) {
        ChildrenName = childrenName;
    }

    public String getTreeType() {
        return TreeType;
    }

    public void setTreeType(String treeType) {
        TreeType = treeType;
    }

    @Override
    public String toString() {
        return "TreeStruct{" +
                "Id='" + Id + '\'' +
                ", NodeName='" + NodeName + '\'' +
                ", ParentId='" + ParentId + '\'' +
                ", ChildrenId=" + ChildrenId +
                ", ChildrenName=" + ChildrenName +
                ", TreeType='" + TreeType + '\'' +
                '}';
    }
}
