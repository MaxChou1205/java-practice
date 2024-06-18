import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

static class Test {
    private final int id;
    private final String name;

    public Test(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: " + this.id + " name: " + this.name;
    }
}

private static Connection c;

public static class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        initDB();

        String name = JOptionPane.showInputDialog("find by name");
        String sqlStatement = "select * from Test where name = ? ;";
        PreparedStatement pps = c.prepareStatement(sqlStatement);
        pps.setString(1, name);
        ResultSet rs = pps.executeQuery();

        if (rs.next()) {
            Test t = new Test(Integer.parseInt(rs.getString("id")), rs.getString("name"));
            JOptionPane.showMessageDialog(null, t.toString());
        } else {
            JOptionPane.showMessageDialog(null, "not found");
        }

        // ArrayList<Test> result = new ArrayList<>();
        // while (rs.next()) {
        // Test t = new Test(Integer.parseInt(rs.getString("id")),
        // rs.getString("name"));
        // result.add(t);
        // }

        // for (Test t : result) {
        // System.out.println(t.toString());
        // }

        closeDB();
    }
}

private static void initDB() throws SQLException {
    System.out.println("test");
    c = DriverManager.getConnection("jdbc:mysql://localhost:3306/kadmonorepo", "root", "root");
    if (c != null) {
        System.out.println("connecting to database");
    } else {
        System.out.println("cannot connect to database");
    }
}

private static void closeDB() throws SQLException {
    c.close();
}

public void main() {
}
