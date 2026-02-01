package employee.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class RemoveEmployee extends JFrame implements ActionListener{

    Choice choiceEMPID;
    JButton delete, back;

    RemoveEmployee() {

        getContentPane().setLayout(null);

        JLabel label = new JLabel("Employee ID");
        label.setBounds(50, 50, 100, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(label);

        choiceEMPID = new Choice();
        choiceEMPID.setBounds(200, 50, 150, 30);
        add(choiceEMPID);

        // Populate Employee IDs
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("select * from employee");
            while (rs.next()) {
                choiceEMPID.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel labelName = new JLabel("Name");
        labelName.setBounds(50, 100, 100, 30);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelName);

        JLabel textName = new JLabel();
        textName.setBounds(200, 100, 200, 30);
        add(textName);

        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setBounds(50, 150, 100, 30);
        labelPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelPhone);

        JLabel textPhone = new JLabel();
        textPhone.setBounds(200, 150, 200, 30);
        add(textPhone);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(50, 200, 100, 30);
        labelEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelEmail);

        JLabel textEmail = new JLabel();
        textEmail.setBounds(200, 200, 200, 30);
        add(textEmail);

        // Load first employee details
        loadEmployeeDetails(textName, textPhone, textEmail);

        // Change listener for choice box
        choiceEMPID.addItemListener(e -> loadEmployeeDetails(textName, textPhone, textEmail));

        delete = new JButton("Delete");
        delete.setBounds(80, 250, 100, 30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBounds(220, 250, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        // Foreground image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        JLabel img = new JLabel(new ImageIcon(i2));
        img.setBounds(700, 80, 200, 200);
        add(img);

        // Background image
        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("icons/rback.png"));
        Image bgImage = bgIcon.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        JLabel background = new JLabel(new ImageIcon(bgImage));
        background.setBounds(0, 0, 1120, 630);
        getContentPane().add(background);

        // Set JFrame
        setSize(900, 400);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadEmployeeDetails(JLabel textName, JLabel textPhone, JLabel textEmail) {
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery(
                    "select * from employee where empId = '" + choiceEMPID.getSelectedItem() + "'");
            if (rs.next()) {
                textName.setText(rs.getString("name"));
                textPhone.setText(rs.getString("phone"));
                textEmail.setText(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {

            try {
                conn c = new conn();
                String query = "delete from employee where empId = '"+choiceEMPID.getSelectedItem()+"'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Employee deleted successfully");
                setVisible(false);
                new Main_class();

            } catch (Exception E) {
                E.printStackTrace();
            }
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}
