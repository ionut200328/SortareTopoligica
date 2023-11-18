import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    JFrame frame = new JFrame("Sortare Topologica");
    Frame Graph=new Frame();

    public Main() {
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create a menu
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        // Create a menu item
        JMenuItem menuItem = new JMenuItem("Menu Item");
        menu.add(menuItem);

        // Create a toolbar
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false); // make it non-floatable

        //add button to frame. When click call TopologicSort
        JButton button = new JButton("Topologic Sort");
        button.addActionListener(e -> {
            TopologicSort topologicSort = new TopologicSort();
            topologicSort.createListaAdiacenta();
            if (topologicSort.isCiclic()) {
                JOptionPane.showMessageDialog(null, "Graful este ciclic");
                //topologicSort.printListaAdiacenta();
            } else {
                //topologicSort.printListaAdiacenta();

                ArrayList<Integer> topologicSortList = topologicSort.PTDF();

                //add 1 to all elements in topologicSortList
                for (int i = 0; i < topologicSortList.size(); i++) {
                    topologicSortList.set(i, topologicSortList.get(i) + 1);
                }

                JOptionPane.showMessageDialog(null, "Topologic Sort: " + topologicSortList);

            }

        });

        //add undo with ctrl+z
        button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control Z"), "undo");
        button.getActionMap().put("undo", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Graph.undo();
            }
        });

        // Add the button to the toolbar
        toolBar.add(button);

        // Add the toolbar to the menu bar
        menuBar.add(toolBar);

        // Add the menu bar to the frame
        frame.setJMenuBar(menuBar);

        frame.add(Graph);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
