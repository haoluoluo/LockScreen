package comp.base;

import javax.swing.tree.DefaultMutableTreeNode;

public class TreeNodeEnh extends DefaultMutableTreeNode {
    private Integer name;

    public Integer getName() {
        return name;
    }

    public TreeNodeEnh(Integer name, Object userObject){
        super(userObject);
        this.name = name;
    }
    public TreeNodeEnh(Object userObject){
        super(userObject);
    }
    public TreeNodeEnh(){
        super();
    }
}
