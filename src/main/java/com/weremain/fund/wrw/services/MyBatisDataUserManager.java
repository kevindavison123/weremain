package com.weremain.fund.wrw.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.google.common.io.Closeables;
import com.weremain.fund.wrw.support.Settings;
import com.weremain.fund.wrw.support.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kevin on 11/15/16.
 */
public class MyBatisDataUserManager implements UserDataManager {
    private static final Logger LOG = LoggerFactory.getLogger(MyBatisDataUserManager.class);
    private static final String MYBATIS_CONFIG = "mybatis-config.xml";
    private static final String USER_ENV_SUFFIX = "-user";
    private static boolean initialized = false;
    private static ObjectMapper objectMapper;
    private static SqlSessionFactory sqlUserFactory;

    static
    {
        //Setup static sql factories and object mapper
        if (!initialized)
        {
            //Get the environment
            final Settings settings = Settings.getInstance();
            final String environment = settings.getEnvironment();

            //Create readers for each environment
            Reader user = null;

            try
            {
                //Load the filter environment
                user = Resources.getResourceAsReader(MYBATIS_CONFIG);
                sqlUserFactory = new SqlSessionFactoryBuilder().build(user, environment + USER_ENV_SUFFIX);
                LOG.debug("Initialized Sql Session Factory for {}{}", environment, USER_ENV_SUFFIX);
                //Flag the factories as being initialized
                initialized = true;
            }
            catch (Exception x)
            {
                LOG.error("Cannot load {} configuration", MYBATIS_CONFIG, x);
            }
            finally
            {
                Closeables.closeQuietly(user);
            }
            //Instantiate JSON Object Mapper
            if (objectMapper == null)
            {
                objectMapper = new ObjectMapper();
            }
        }
    }

    @Override
    public List<User> findUsers() {
        final SqlSession session = sqlUserFactory.openSession();
        try
        {
            return session.selectList(Mappings.SELECT_USERS);
        }
        finally {
            session.close();
        }
    }

    @Override
    public Optional<User> findUser(String email) {
        final SqlSession session = sqlUserFactory.openSession();
        try{
            final User found = session.selectOne(Mappings.SELECT_USER,email);
            if(found == null )
            {
                LOG.warn("No filter found for {}", email);
            }
            final String content = checkNotNull(found.getContent(), "JSON content is empty");
            final User user = objectMapper.readValue(content,User.class);
            return Optional.of(user);
        }
        catch (JsonParseException e) {
            LOG.error("Cannot parse content {}",e);
        }
        catch (JsonMappingException e) {
            LOG.error("Cannot map content {}",e);
        }
        catch (IOException e) {
            LOG.error("Cannot convert content to the user {}",e);
        }
        finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean saveUser(User user) {
        final SqlSession session = sqlUserFactory.openSession();
        final Date timestamp = new Date();
        try{

            if(checkNotNull(user,"User cannot be null").getId() == null)
            {
                user.setJoined(timestamp);
                session.insert(Mappings.INSERT_USER,user);
            }

            user.setUpdated(timestamp);

            final String newContent = objectMapper.writeValueAsString(user);
            user.setContent(newContent);

            session.update(Mappings.UPDATE_USER,user);

        } catch (JsonProcessingException e) {
           LOG.error("Cannot write content for {}", user.getContent());
            return false;
        }
        finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean deleteUser(String email) {
        int deleted = 0;
        final SqlSession session = sqlUserFactory.openSession();
        try{
            deleted = session.delete(Mappings.DELETE_USER, email);
            if(deleted == 0)
            {
                LOG.warn("No users found to be deleted with {}", email);
            }
            session.commit();
        }finally {
            session.close();
        }
        return deleted != 0;
    }

    private static class Mappings
    {
        private static final String SELECT_USERS = "users.selectUsers";
        private static final String SELECT_USER = "users.selectUserByEmail";
        private static final String INSERT_USER = "users.insertUser";
        private static final String UPDATE_USER = "users.updateUser";
        private static final String DELETE_USER = "users.deleteUserByEmail";

    }
}
