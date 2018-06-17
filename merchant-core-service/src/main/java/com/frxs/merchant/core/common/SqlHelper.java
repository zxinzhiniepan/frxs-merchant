package com.frxs.merchant.core.common;

import com.frxs.framework.util.common.log4j.LogUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 * @author wushuo
 * @version $Id: SqlHelper.java,v 0.1 2018年02月27日 10:44 $Exp
 */
public class SqlHelper {
    public static DataSource dataSource;

    public static Long getNextSeqForUser() {
        Statement st = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            st = conn.createStatement();
            ResultSet set = st.executeQuery("select nextval for seq_user");
            while (set.next()){
                return set.getLong(1);
            }
            st.close();
        }catch (SQLException e){
            LogUtil.error(e, "execute sql failed, sql = {}", "");
        }finally {
            close(conn);
            close(rs);
            close(st);
        }
        return null;
    }

    private static void close(AutoCloseable c){
        if (c == null){
            return;
        }
        try {
            c.close();
            c = null;
        } catch (Exception e) {
            LogUtil.error(e, "close ResultSet failed");
        }
    }
}
