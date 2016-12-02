package services;

import com.google.common.base.Optional;
import com.weremain.fund.wrw.services.MyBatisDataUserManager;
import com.weremain.fund.wrw.support.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by kevin on 12/2/16.
 */
public class MyBatisDataUserManangerTest
{
    private static final Logger LOG = LoggerFactory.getLogger(MyBatisDataUserManangerTest.class);
    private static String CONNECTIONURL = "jdbc:h2:mem:test;MODE=MSSQLSERVER";
    private static String USERNAME = "sa";
    private static String PASSWORD = "sa";
    private Statement statement;
    private MyBatisDataUserManager userManager;
    @Rule
    public TestName testsName = new TestName();
   @Before
    public void setup() throws SQLException, ClassNotFoundException {
       userManager = new MyBatisDataUserManager();
       Class.forName("org.h2.Driver");
       Connection con = DriverManager.getConnection(CONNECTIONURL, USERNAME, PASSWORD);
       statement = con.createStatement();

       //create db for users
       File ddlFile = new File("src/test/resources", "users-ddl.sql");
       String databasePath = ddlFile.getAbsolutePath();
       String createDatabaseFromScript = "RUNSCRIPT FROM " +"'" + databasePath + "'";
       statement.execute(createDatabaseFromScript);

       //insert usrs into database
       File insertionFile = new File("src/test/resources", "insert-users.sql");
       String insertionPath = insertionFile.getAbsolutePath();
       String insertIntoDBFromScript = "RUNSCRIPT FROM " +"'" + insertionPath + "'";
       statement.execute(insertIntoDBFromScript);

       LOG.debug("---------- {} ----------", testsName);
   }

    @Test
    public void should_create_a_user(){}

    @Test
    public void should_get_all_users(){}

    @Test
    public void should_get_single_user(){
        Optional<User> user = userManager.findUser("AARON@AARON.COM");

    }

    @Test
    public void should_delete_user(){}

    @Test
    public void should_update_user(){}

    @After
    public void teardown() throws SQLException {
        statement.execute("DROP TABLE USERSDB");
        statement.close();
    }

}
