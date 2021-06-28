import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class SocialArk {
    Statement statement = null;

    public SocialArk() {
        try {
            Data data = new Data();
            statement = data.getStatement();
            
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS USERS (" +
                            "userid INTEGER not NULL AUTO_INCREMENT," +
                            "username VARCHAR(255)," +
                            "password VARCHAR(255)," +
                            "PRIMARY KEY(userid))"
            );
            
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS SOCIALGROUPS (groupid INT NOT NULL PRIMARY KEY AUTO_INCREMENT,groupName VARCHAR(255),creator INTEGER,date VARCHAR(255),genre VARCHAR(255))");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS PAGES (pageid INTEGER not NULL AUTO_INCREMENT,pageName VARCHAR(255),creator  INTEGER,date VARCHAR(255),genre VARCHAR(255),PRIMARY KEY (pageid))");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS FRIENDSHIPS (userid INTEGER ,friend INTEGER)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS GROUPUSERS (groupid INTEGER,member integer)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS PAGEFOLLOWERS (pageid INTEGER,liker INTEGER)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS MESSAGES (messageid INTEGER not NULL AUTO_INCREMENT,date VARCHAR(255) ,author INTEGER ,content TEXT,PRIMARY KEY (messageid))");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS WALLS (owner INTEGER not NULL,poste INTEGER)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS GROUPMESSAGES (groupid INTEGER,messageid INTEGER)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS PAGEMESSAGES (pageid INTEGER,messageid INTEGER)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS FRIENDREQUESTS(userid INTEGER , requestedfriend INTEGER)");

            
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void suggestFriends() {
        
    }

    


}
