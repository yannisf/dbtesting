package fraglab.dbunit;

import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.jdbc.JDBCDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseTest.class);

    private DataSource dataSource;

    @BeforeClass
    public void beforeClass() throws Exception {
        initializeDataSource();
        initializeDatabase();
    }

    private void initializeDataSource() {
        JDBCDataSource hsqlDataSource = new JDBCDataSource();
        hsqlDataSource.setUrl("jdbc:hsqldb:mem:mymemdb");
        dataSource = hsqlDataSource;
    }

    private void initializeDatabase() throws Exception {
        File script = new File(getClass().getResource("/script.sql").toURI());
        SqlFile sqlFile = new SqlFile(script);
        sqlFile.setConnection(dataSource.getConnection());
        sqlFile.execute();
    }

    @Test
    public void testSingle() {
        BusinessEntity businessEntity = new BusinessEntity();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from idval where id = 0");
            while (resultSet.next()) {
                businessEntity.setId(resultSet.getInt(1));
                businessEntity.setValue(resultSet.getString(2));
                businessEntity.setListValues(Util.asList(resultSet.getString(3)));
                LOG.info("Select one: [{}]", businessEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(businessEntity.getId(), Integer.valueOf(0));
        Assert.assertNull(businessEntity.getValue());
        Assert.assertNull(businessEntity.getListValues());

    }

    @Test
    public void testMany() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from idval");
            List<BusinessEntity> businessEntityList = new ArrayList<>();
            while (resultSet.next()) {
                BusinessEntity businessEntity = new BusinessEntity();
                businessEntity.setId(resultSet.getInt(1));
                businessEntity.setValue(resultSet.getString(2));
                businessEntity.setListValues(Util.asList(resultSet.getString(3)));
                LOG.info("Select all: [{}]", businessEntity);
                businessEntityList.add(businessEntity);
            }
            businessEntityList.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
