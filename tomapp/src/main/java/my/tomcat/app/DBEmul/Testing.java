package my.tomcat.app.DBEmul;

public class Testing {
    public static void main(String[] a){
        DBemulation db=new DBemulation();
        System.out.println("data base emulation:");
        System.out.println(db.getLogin()+" "+db.getPassword());
    }
}
