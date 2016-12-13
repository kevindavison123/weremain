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
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by kevin on 12/2/16.
 */
public class MyBatisDataUserManagerTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisDataUserManagerTest.class);
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

       LOGGER.debug("---------- {} ----------", testsName.getMethodName());
   }

    @Test
    public void should_create_a_user(){
        LOGGER.debug("{}",userManager.findUsers().size());
        User createUser = new User("DAVID","[\"CHARITY1\",\"CHARITY2\",\"CHARITY3\"]","DAVID@DAVID.COM","444-444-4444");
        boolean saved = userManager.saveUser(createUser);
        for(User user: userManager.findUsers())
        {
            LOGGER.debug("User: {}", user.getContent());
        }
        assertThat(saved,is(true));
        assertThat(userManager.findUsers().size(),is(3));


    }

    @Test
    public void should_get_all_users(){
        List<User> list = userManager.findUsers();
        assertThat(list.isEmpty(), is(false));
        assertThat(list.size(), is(2));

    }

    @Test
    public void should_get_and_not_get_single_user(){
        Optional<User> user = userManager.findUser("AARON@AARON.COM");
        assertThat(user.isPresent(), is(true));
        assertThat(user.get().getId(), is(1001L));
        assertThat(user.get().getEmail(),is("AARON@AARON.COM"));

        Optional<User> fakeUser = userManager.findUser("NOTAPERSON@NOTAPERSON.COM");
        assertThat(fakeUser.isPresent(), is(false));
    }

    @Test
    public void should_delete_user(){
        boolean deleted = userManager.deleteUser("AARON@AARON.COM");
        assertThat(deleted, is(true));
        assertThat(userManager.findUsers().size(),is(1));

        boolean notDeleted = userManager.deleteUser("NOTAPERSON@NOTAPERSON.COM");
        assertThat(deleted, is(true));
        assertThat(userManager.findUsers().size(), is(1));
    }

    @Test
    public void should_update_user(){
        User someOldUser = userManager.findUser("AARON@AARON.COM").get();
    }

    @After
    public void teardown() throws SQLException {
        statement.execute("DROP TABLE USERSDB");
        statement.close();
    }

}
