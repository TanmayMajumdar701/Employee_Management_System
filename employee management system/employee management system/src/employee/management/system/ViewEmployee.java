package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils; // Make sure rs2xml.jar is in classpath

public class ViewEmployee extends JFrame implements ActionListener {

    JTable table;
    Choice choiceEMP;
    JButton searchbtn,print,update,back;


    ViewEmployee() {

        getContentPane().setBackground(new Color(255, 131, 122));
        setLayout(null);

        JLabel search = new JLabel("Search by employee id");
        search.setBounds(20, 20, 150, 20);
        add(search);

        choiceEMP = new Choice();
        choiceEMP.setBounds(180, 20, 150, 20);
        add(choiceEMP);

        // Fill choice box with employee IDs
        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM employee");
            while (resultSet.next()) {
                choiceEMP.add(resultSet.getString("empID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();

        // Fill table with employee data
        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM employee");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(0, 100, 900, 600);
        add(jp);

        searchbtn = new JButton("Search");
        searchbtn.setBounds(20,70,80,20);
        searchbtn.addActionListener(this);
        add(searchbtn);

        print = new JButton("Print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(220,70,80,20);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(320,70,80,20);
        back.addActionListener(this);
        add(back);

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == searchbtn){
            String query = "select * from employee where empId = '"+choiceEMP.getSelectedItem()+"'";
            try{
                conn c = new conn();
                ResultSet resultset = c.statement.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(resultset));
            }catch(Exception E){
                E.printStackTrace();
            }

        } else if ( e.getSource() == print) {
            try{
                table.print();
            }catch( Exception E){
                E.printStackTrace();
            }

        } else if ( e.getSource() == update) {
            setVisible(false);
            new UpdateEmployee(choiceEMP.getSelectedItem());

        } else {
            setVisible(false);
            new Main_class();
        }

    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}
