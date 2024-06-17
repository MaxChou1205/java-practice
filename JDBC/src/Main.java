import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String sqlStatement = "select * from Test";

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/kadmonorepo", "root", "root");
        if (c != null) {
            System.out.println("connecting to database");
        } else {
            System.out.println("cannot connect to database");
        }

        PreparedStatement pps = c.prepareStatement(sqlStatement);
        ResultSet rs = pps.executeQuery();

        ArrayList<Test> result = new ArrayList<>();
        while (rs.next()) {
            Test t = new Test(Integer.parseInt(rs.getString("id")), rs.getString("name"));
            result.add(t);
        }

        for (Test t : result) {
            System.out.println(t.toString());
        }
    }
}

class Test {
    private int id;
    private String name;

    public Test(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: " + this.id + "name: " + this.name;
    }
}