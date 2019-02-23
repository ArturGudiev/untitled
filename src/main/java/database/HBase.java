package database;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;


public class HBase {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        try{
            Admin admin = connection.getAdmin();
            HTableDescriptor tableName = new HTableDescriptor(TableName.valueOf("census"));
            tableName.addFamily(new HColumnDescriptor("personal"));
            tableName.addFamily(new HColumnDescriptor("professional"));

            if (!admin.tableExists(tableName.getTableName())) {
                System.out.println("Creating");
                admin.createTable(tableName);
                System.out.println("Done");
            }else{
                System.out.println("Table exists");
            }

        }finally{
        }
    }
}
