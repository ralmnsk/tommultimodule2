package my.tomcat.app.DBEmul;

public class Testing {
    public static void main(String[] a){
        DBemulation db=new DBemulation();
        System.out.println(db.getLogin()+" "+db.getPassword());
    }
}
