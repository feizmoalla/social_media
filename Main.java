import java.util.*;
public class Main {
    static User user = null;
    static void a(String str) {
        System.out.println(str);
    }
    static String b(String str) {
        Scanner sc = new Scanner(System.in);
        System.out.print(str);
        return sc.nextLine();
    }
    static void welcome() {
    a(
"                            .-''''-.                   "+"\n"+
"                       / .===. \\                       "+"\n"+
"                       \\/ 6 6 \\/                       "+"\n"+
"                       ( \\___/ )                       "+"\n"+
"  _________________ooo__\\_____/_____________________   "+"\n"+
" /                                                  \\  " +"\n"+
"|          ⛵️⛵️  WELCOME TO SOCIALARK   ⛵️⛵         | "+"\n"+
" \\______________________________ooo_________________/  "+"\n"+
"                       |  |  |     By Dalansi Malek     "+"\n"+
"                       |_ | _|        Zgolli Youssef    "+"\n"+
"                       |  |  |        Moalla Feiz       "+"\n"+
"                       |__|__|                         "+"\n"+
"                       /-'Y'-\\                         "+"\n"+
"                      (__/ \\__)                        "+"\n"+
"                                                       ");
    }

    static void showMainOptions() {
        a("[00] Show Main Options");
        a("[1]  Create Group");
        a("[2]  Create Page");
        a("[3]  List Members Names");
        a("[4]  Send a Friend Request");
        a("[5]  List Friend Requests");
        a("[6]  Accept Some Friend Requests");
        a("[7]  List Friends");
        a("[8]  Join a Group");
        a("[9]  Leave a Group");
        a("[10] Like a Page");
        a("[11] Poste in a Page");
        a("[12] Poste in a Group");
        a("[13] Poste in a Wall");
        a("[14] Show your posts");
        a("[15] Show Page posts");
        a("[16] Show Group Posts");
        a("[17] View Suggested \n\n");


    }
    
    static void showLoginOptions() {
        a("[1] create an account"+"\n"+"[2] login");
    }
    static User createAccount() {
        String un = b("username : ");
        String pwd = b("password : ");
        return new User(un,pwd);
    }


    public static void main(String[] args) {
        welcome();
        a("WAIT [connecting to SocialArk] ...");
        SocialArk SA = new SocialArk();
        showLoginOptions();
        while(true) {
        String ch1=b("your choice: ");
        if(ch1.equals("1")) {
            user = createAccount();
            a("\n\n Account Created!\n\n");
           break;

        }else if(ch1.equals("2")) {
            String e= b("your username: ");
            String r=b("your password: ");
            user = new User(e,r);
            a("\n\n Logged In!");
           break;

        } else {
            a("\n\n Ivalid Option \n\n");
        }
        }
        
        showMainOptions();
        while(true) {
            String ch2=b("your choice: ");
            if(ch2.equals("1")) {
                String e = b("Group Name: ");
                String r= b("Genre: ");
                user.createGroup(e,r);
               

            } else if (ch2.equals("2")) {
                String e = b("Page Name: ");
                String r= b("Genre: ");
                user.createPage(e,r);
               

            } else if (ch2.equals("3")) {
                user.listMembersName();
               
            } else if (ch2.equals("4")) {
                String e=b("Name: ");
                user.sendFriendRequest(e);
               
            } else if (ch2.equals("5")) {
                user.listFriendRequests();
               
            } else if (ch2.equals("6")) {
                String e=b("Name: ");
                user.acceptFriendRequest(e);
               
                
            } else if (ch2.equals("7")) {
                user.friendList();
               
            } else if (ch2.equals("8")) {
                String e=b("Name: ");
                user.joinGroup(e);
               
            } else if (ch2.equals("9")) {
                String e=b("Name: ");
                user.leaveGroup(e);
               
                
            } else if (ch2.equals("10")) {
                String e=b("Name of the Page: ");
                user.likePage(e);
               

                
            } else if (ch2.equals("11")) {
                String e=b("Page Name: ");
                String r=b("Text: ");
                user.pubToPage(e,r);
               

            } else if (ch2.equals("12")) {
                String e=b("Group Name: ");
                String r=b("Text: ");
                user.pubToGroup(e,r);
               
                
            } else if (ch2.equals("13")) {
                String e=b("User Name: ");
                String r=b("Text: ");
                user.pubToUser(e,r);
               
            } else if (ch2.equals("14")) {
                user.showMyPubs();
               
            } else if (ch2.equals("15")) {
                String e=b("Page Name: ");
                user.showPagePubs(e);
               
            } else if (ch2.equals("16")) {
                String e=b("Group Name: ");
                user.showGroupPubs(e);
               
            } else if(ch2.equals("17")) {
                a("this option doesn't work for the moment , wait for the next update !");
               

            } 
           else if(ch2.equals("00")) {
               showMainOptions();
           } else {
                a("\n\nInvalid Option!\n\n");
            }
        }


        
        
    }
}

