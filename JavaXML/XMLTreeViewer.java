import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class XMLTreeViewer extends JFrame {
    private JTree tree;
    private DefaultMutableTreeNode rootNode;

    public XMLTreeViewer() {
        setTitle("XML Parser без атрибутов");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        
        JButton loadButton = new JButton("Загрузить XML");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                if (chooser.showOpenDialog(XMLTreeViewer.this) == JFileChooser.APPROVE_OPTION) {
                    parseXML(chooser.getSelectedFile());
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane();
        add(loadButton, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        rootNode = new DefaultMutableTreeNode("XML Root");
        tree = new JTree(rootNode);
        scrollPane.setViewportView(tree);
    }

    private void parseXML(File file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            rootNode.removeAllChildren();
            buildTree(doc.getDocumentElement(), rootNode);
            tree.updateUI();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void buildTree(Node node, DefaultMutableTreeNode treeNode) {
        String nodeName = node.getNodeName();
        if (node.getNodeType() == Node.TEXT_NODE && node.getTextContent().trim().length() > 0) {
            nodeName += " (" + node.getTextContent().trim() + ")";
        }
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(nodeName);
        treeNode.add(newNode);

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            buildTree(nodeList.item(i), newNode);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new XMLTreeViewer().setVisible(true);
        });
    }
}
