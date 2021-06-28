import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class User {
    static int userid;
    String username;
    String password;
    Statement statement = null;
    //ResultSet rs = null;
    public User(String username, String password){
        
        try {
                this.username = username;
                this.password = password;
                Data data = new Data();
                statement = data.getStatement();
                String stmt1 = String.format("SELECT username , password FROM USERS WHERE username = '%s'   AND password = '%s'",username,password);
                ResultSet rs = statement.executeQuery(stmt1);
                if (rs.next()){
                    System.out.println("Already Exist !");    
                } else {
                    String stmt = String.format("INSERT INTO USERS(username,password) VALUES('%s','%s')",username,password);
                    statement.executeUpdate(stmt);
                }
                
                
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public void createGroup(String groupName,String genre) {
        try {
                Data data = new Data();
                LocalDateTime date = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                String formattedDateTime = date.format(formatter);
                String stmt = String.format("INSERT INTO SOCIALGROUPS(groupName,creator,date,genre) VALUES('%s','%d','%s','%s')",groupName,getUserID(this.username),formattedDateTime,genre);
                statement.executeUpdate(stmt);
                
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void createPage(String pageName , String genre) {
        try {
                Data data = new Data();
                LocalDateTime date = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                String formattedDateTime = date.format(formatter);
                String stmt = String.format("INSERT INTO PAGES(pageName,creator,date,genre) VALUES('%s','%d','%s','%s')",pageName,getUserID(this.username),formattedDateTime,genre);
                statement.executeUpdate(stmt);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public int getUserID(String username) {
        try{
            Data data = new Data(); 
            String stmt = String.format("SELECT userid from USERS WHERE username='%s'",username);
            statement = data.getStatement();
            ResultSet rs = statement.executeQuery(stmt);
            if(rs.next()) {
                return rs.getInt("userid");

            }


        } catch(Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    public void listMembersName() {
        try{
            Data data = new Data();
            String stmt = String.format("SELECT username FROM USERS");
            statement = data.getStatement();
            ResultSet rs = statement.executeQuery(stmt);
            
            while(rs.next()) {
                System.out.println(rs.getString("username"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
       
    }
    public void sendFriendRequest(String name) {
        try{
             Data data = new Data();
            String stmt = String.format("INSERT INTO FRIENDREQUESTS (userid,requestedfriend) VALUES ('%d','%d')",getUserID(this.username),getUserID(name));
            statement = data.getStatement();
            statement.executeUpdate(stmt);
        } catch(Exception e) {
            System.out.println(e);
        }
       
    }
    public void listFriendRequests() {
        try{

            Data data = new Data();
            String stmt = String.format("SELECT u.username FROM USERS u, FRIENDREQUESTS f WHERE u.userid = f.userid AND f.requestedfriend='%d'",getUserID(this.username));
            ResultSet rs = statement.executeQuery(stmt);
            
            while(rs.next()) {
                System.out.println(rs.getString("username"));
            }

        } catch(Exception e) {
            System.out.println(e);
        }

    }
    public void acceptFriendRequest(String name) {
        try {
            Data data = new Data();
            statement = data.getStatement();
            String stmt = String.format("SELECT username FROM USERS u , FRIENDREQUESTS f WHERE u.userid = f.userid and f.requestedfriend='%d' and u.username = '%s'",getUserID(this.username),name);
            ResultSet rs = statement.executeQuery(stmt);
            if(rs.next()) {
                String stmt1 = String.format("INSERT INTO TABLE FRIENDSHIPS (userid,friend) VALUES ('%d','%d')",getUserID(this.username),getUserID(name));
                statement.executeUpdate(stmt1);
                String stmt2 = String.format("DELETE FROM FRIENDREQUESTS WHERE userid = '%d' and requestedfriend='%d'",getUserID(name),getUserID(this.username));
                statement.executeUpdate(stmt2);

            }
            

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void friendList(){
        try {
            Data data = new Data();
            statement = data.getStatement();
            String stmt = String.format("SELECT username FROM USERS u, FRIENSHIPS f WHERE f.friend = u.userid AND f.userid = '%d'",getUserID(this.username));
            ResultSet rs = statement.executeQuery(stmt);
    
            while(rs.next()) {
                System.out.println(rs.getString("username"));
            }

        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public void joinGroup(String groupName) {
        try{
            Data data = new Data();
            statement = data.getStatement();
            String stmt = String.format("INSERT INTO GROUPSERS (groupid , member) VALUES ('%d','%d')",getGroupID(groupName),getUserID(this.username));
            statement.executeUpdate(stmt);
        } catch(Exception e) {
            System.out.println(e);
        }

    }

    public int getGroupID(String groupName) {
        try{
            Data data = new Data(); 
            String stmt = String.format("SELECT groupid from SOCIALGROUPS WHERE groupName='%s'",groupName);
            statement = data.getStatement();
            ResultSet rs = statement.executeQuery(stmt);
            if(rs.next()) {
                return rs.getInt("groupid");

            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    public void leaveGroup(String groupName) {
         try{
            Data data = new Data();
            statement = data.getStatement();
            String stmt = String.format("DELETE FROM GROUPSERS WHERE groupid = '%d' AND member = '%d'",getGroupID(groupName),getUserID(this.username));
            statement.executeUpdate(stmt);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public int getPageID(String pageName) {
         try{
            Data data = new Data(); 
            String stmt = String.format("SELECT pageid from SOCIALGROUPS WHERE pageName='%s'",pageName);
            statement = data.getStatement();
            ResultSet rs = statement.executeQuery(stmt);
            if(rs.next()) {
                return rs.getInt("pageid");

            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    public void likePage(String pageName) {
         try{
             Data data = new Data();
            statement = data.getStatement();
            String stmt = String.format("INSERT INTO PAGEFOLLOWERS (pageid , member) VALUES ('%d','%d')",getPageID(pageName),getUserID(this.username));
            statement.executeUpdate(stmt);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public int createMessage(String text) {
        try{
            Data data = new Data();
            statement = data.getStatement();
            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            String formattedDateTime = date.format(formatter);
            String stmt = String.format("INSERT INTO MESSAGES (date,author,content) VALUES ('%s','%d','%s')",formattedDateTime,getUserID(this.username),text);
            statement.executeUpdate(stmt);

            String stmt1 = String.format("SELECT messageid FROM MESSAGES WHERE date='%s' AND author = '%d'",formattedDateTime,getUserID(this.username));
            ResultSet rs = statement.executeQuery(stmt1);
            if(rs.next()) {
                return rs.getInt("messageid");
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    public void pubToPage(String pageName ,String text) {
         try{
            int mid = createMessage(text);
            Data data = new Data();
            statement = data.getStatement();
            String stmt = String.format("INSERT INTO PAGEMESSAGES (pageid,messageid) VALUES ('%d','%d')",getPageID(pageName),mid);
            statement.executeUpdate(stmt);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public void pubToGroup(String groupName, String text) {
        try{
            int mid = createMessage(text);
            Data data = new Data();
            statement = data.getStatement();
            String stmt = String.format("INSERT INTO GROUPMESSAGES (groupid,messageid) VALUES ('%d','%d')",getGroupID(groupName),mid);
            statement.executeUpdate(stmt);

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void pubToUser(String username, String text) {
         try{
            int mid = createMessage(text);
            Data data = new Data();
            statement = data.getStatement();
            String stmt = String.format("INSERT INTO WALLS (userid,poste) VALUES ('%d','%d')",getUserID(username),mid);
            statement.executeUpdate(stmt);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void showMyPubs() {
         try{
             Data data = new Data();
            statement = data.getStatement();
            String stmt = String.format("SELECT username,content FROM USERS u ,MESSAGES m, WALLS w WHERE u.userid = m.author AND m.messageid = w.poste AND w.userid = '%d' ",getUserID(this.username));
        
            ResultSet rs = statement.executeQuery(stmt);
            while(rs.next()) {
                System.out.println(rs.getString("username") + ":");
                System.out.println(rs.getString("content"));
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void showGroupPubs(String groupName) {
         try{
            Data data = new Data();
            statement = data.getStatement();
            String stmt = String.format("SELECT username,content,m.date FROM USERS u ,MESSAGES m, GROUPMESSAGES g,SOCIALGROUPS s WHERE u.userid = m.author AND m.messageid = g.messageid AND g.groupid = '%d' ",getGroupID(groupName));
        
            ResultSet rs = statement.executeQuery(stmt);
            while(rs.next()) {
                System.out.println(rs.getString("username") + ":" + "(" +rs.getString("date") + ")");
                System.out.println(rs.getString("content"));
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void showPagePubs(String pageName) {
         try{
            Data data = new Data();
            statement = data.getStatement();
            String stmt = String.format("SELECT username,content,m.date FROM USERS u,PAGESMESSAGES pm ,MESSAGES m WHERE u.userid = m.author and pm.pageid = '%d'",getPageID(pageName));
        
            ResultSet rs = statement.executeQuery(stmt);
            while(rs.next()) {
                System.out.println(rs.getString("username") + ":" + "("+rs.getString("m.date")+")");
                System.out.println(rs.getString("content\n\n"));
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void ViewSuggestedFriends() {
         try{

        } catch(Exception e) {
            System.out.println(e);
        }

    }




}