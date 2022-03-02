package comp.base;

import com.formdev.flatlaf.icons.FlatTreeCollapsedIcon;
import com.formdev.flatlaf.icons.FlatTreeExpandedIcon;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

public class JTreeEnh extends JTree {
    public JTreeEnh(TreeNode root, JPanelEnh panelEnh){
        super(root);
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();

        renderer.setOpenIcon(new FlatTreeExpandedIcon());
        renderer.setClosedIcon(new FlatTreeCollapsedIcon());

        renderer.setLeafIcon(null);

        this.setCellRenderer(renderer);

        addMouseListener(panelEnh);

    }
    public void addMouseListener(JPanelEnh panelEnh) {
        this.addMouseListener(new MouseAdapter() {
                                  public void mouseClicked(MouseEvent event) {
                                      int selRow = getRowForLocation(event.getX(), event.getY());
                                      if (selRow != -1) {
                                          changeCard(panelEnh, getLastSelectedPathComponent());
                                      }
                                  }

                              }
        );
    }

    public void changeCard(JPanelEnh panelEnh, Object card){
        ((CardLayout)panelEnh.getLayout()).show(panelEnh, card.toString());
    }
    public void expandTree() {
        TreeNode root = (TreeNode) this.getModel().getRoot();
        expandTree(new TreePath(root));
    }

    public void expandTree(TreePath path) {
        TreeNode node = (TreeNode) path.getLastPathComponent();

        // Go to leaf
        if (node.getChildCount() > 0) {
            Enumeration<TreeNode> children = (Enumeration<TreeNode>) node.children();
            while (children.hasMoreElements()) {
                TreeNode n = children.nextElement();
                TreePath newPath = path.pathByAddingChild(n);
                expandTree(newPath);
            }
        }
        this.expandPath(path);
    }

}
