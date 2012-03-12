package by.zloy.gruz.web.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Activator implements BundleActivator
{
    // We should configure this using Config Admin service
    public static final String dsName = "jdbc/__default";
    public static final String tableName = "USERINFO";

    private DataSource ds;

    public void start(BundleContext context) throws Exception
    {
        InitialContext ctx = new InitialContext();
        Connection c = null;
        Statement s = null;
        try
        {
            ds = (DataSource) ctx.lookup(dsName);
            c = ds.getConnection();
            s = c.createStatement();
            String sql = "create table " + tableName +
                    " (NAME VARCHAR(10) NOT NULL, PASSWORD VARCHAR(10) NOT NULL," +
                    " PRIMARY KEY(NAME))";
            System.out.println("sql = " + sql);
            s.executeUpdate(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (c!= null) c.close();
                if (s!=null) s.close();
            }
            catch (Exception e)
            {
            }
        }
    }

    public void stop(BundleContext context) throws Exception
    {
        Connection c = null;
        Statement s = null;
        try
        {
            c = ds.getConnection();
            s = c.createStatement();
            String sql = "drop table " + tableName;
            System.out.println("sql = " + sql);
            s.executeUpdate(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (c!= null) c.close();
                if (s!=null) s.close();
            }
            catch (Exception e)
            {
            }
        }

    }

}
