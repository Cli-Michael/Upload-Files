package Database;





import java.sql.Connection;
import java.sql.DriverManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class DB {
    public static Connection getConnection() {
    Connection con=null;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/uploadfiles?allowPublicKeyRetrieval=true&useSSL=false","root","Government@1998");
//                con=DriverManager.getConnection("jdbc:mysql://database-2.cjkiau6gyrt6.ap-south-1.rds.amazonaws.com:3306/study_?allowPublicKeyRetrieval=true&useSSL=false","admin","O5uHJjsEZkprHqoV1eb0");
            }catch(Exception e){
                System.out.println(e);
            }
            return con;
        }
}
