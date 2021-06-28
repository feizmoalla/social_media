import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
public class Data {
    private String url="jdbc:mysql://localhost:3306/SocialArkDB";
    private String user ="root";
    private String pwd = "passwd";
    Connection connexion = null;
    Statement statement = null;

    public String getUrl() {
        return this.url;
    }

    public String getUser() {
        return this.user;
    }

    public String getPwd() {
        return this.pwd;
    }
    public Statement getStatement() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connexion = DriverManager.getConnection(getUrl(),getUser(), getPwd());
            statement = connexion.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
        return statement;

    }
}