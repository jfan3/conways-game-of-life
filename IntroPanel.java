/**
 * An intro panel. Written by Fiona Fan
 */
import javax.swing.*;

public class IntroPanel extends JPanel {
  
    private JLabel wellesleyPicLabel;
    private JLabel conwayPicLabel;
    private JLabel projectLabel;

    //constructor
    public IntroPanel() {
        initComponents();
    }


    @SuppressWarnings("unchecked")

    private void initComponents() {

        wellesleyPicLabel = new JLabel();
        conwayPicLabel = new JLabel();
        projectLabel = new JLabel();

        wellesleyPicLabel.setIcon(new ImageIcon(getClass().getResource("/wellesley2.jpg"))); 
        wellesleyPicLabel.setText("wellesleyPicLabel");

        conwayPicLabel.setIcon(new ImageIcon(getClass().getResource("/conway.jpg"))); 
        conwayPicLabel.setText("conwayPicLabel");

        projectLabel.setBackground(new java.awt.Color(204, 204, 255));
        projectLabel.setFont(new java.awt.Font("American Typewriter", 1, 24)); 
        projectLabel.setForeground(new java.awt.Color(153, 0, 153));
        projectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        projectLabel.setText("Project by Candice Gong, Canssandra Zheng  && Fiona Fan");
        projectLabel.setOpaque(true);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(projectLabel)
                .addGap(0, 180, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(wellesleyPicLabel, GroupLayout.PREFERRED_SIZE, 583, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(conwayPicLabel, GroupLayout.PREFERRED_SIZE, 583, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(conwayPicLabel, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(projectLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(wellesleyPicLabel, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );
    }


}
