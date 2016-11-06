package com.edutilos.test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlExample {
    public static void main(String[] args) {
        WorkerDAO dao = new WorkerDAO();
        dao.connect();
        dao.dropWorkerTable();
        dao.createWorkerTable();
        List<Worker> workerList = Arrays.asList(
           new Worker("foo",10 , 100.0, true),
                new Worker("bar", 20 , 200.0 , false),
                new Worker("bim", 30 , 300.0, true),
                new Worker("pako", 40 , 400.0 , true)
        );
        workerList.forEach(w-> {
            dao.insertEntry(w);
        });


        List<Worker> all = dao.findAll();
        printWorkerList(all);

        System.out.println("");
        Worker one = dao.findById(1L);
        printWorker(one);


        dao.disconnect();
    }

    private static void printWorker(Worker worker) {
        System.out.println(worker.toString());
    }

    private static void printWorkerList(List<Worker> workerList) {
        System.out.println("<<all workers>>");
        workerList.forEach(w -> {
            printWorker(w);
        });
    }
}


class WorkerDAO {
  private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/test";
    private final String USER = "root";
    private final String PASS = "";

    private Connection conn ;
    private Statement stmt ;
    private PreparedStatement pstmt ;
    private ResultSet rs ;


    public void connect() {
      try {
          Class.forName(DRIVER);
          conn = DriverManager.getConnection(URL, USER, PASS);
          stmt = conn.createStatement();
      } catch(Exception ex) {
          ex.printStackTrace();
      }
    }

    public void disconnect() {
       if(rs != null) {
           try {
               rs.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }


        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        if(pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void createWorkerTable() {
      String sql = "create table if not exists worker(\n" +
              "  id BIGINT not null PRIMARY KEY AUTO_INCREMENT, \n" +
              "  name varchar(50) not null , \n" +
              "  age int not null, \n" +
              "  wage DOUBLE not null , \n" +
              "  active BOOLEAN not null \n" +
              "); ";


        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropWorkerTable() {
         String sql = "drop table if exists worker";
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertEntry(String name, int age, double wage, boolean active) {
          String sql = "insert into Worker (name, age, wage , active) VALUES (?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setDouble(3, wage);
            pstmt.setBoolean(4, active);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void insertEntry(Worker worker) {
        insertEntry(worker.getName(), worker.getAge(), worker.getWage(), worker.isActive());
    }

    public void updateEntry(long id , String newName, int newAge, double newWage, boolean newActive) {
       String sql = "update Worker  set name = ? , age = ? , wage = ? , active = ? where id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newName);
            pstmt.setInt(2, newAge);
            pstmt.setDouble(3, newWage);
            pstmt.setBoolean(4,newActive);
            pstmt.setLong(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

     public void updateEntry(Worker newWorker) {
         updateEntry(newWorker.getId(), newWorker.getName(), newWorker.getAge(), newWorker.getWage(), newWorker.isActive());
     }

    public void deleteEntry(long id) {
       String sql = "delete from worker where id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Worker findById(long id) {
        String sql = "select * from Worker where id = ?";
        Worker w = null ;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            rs.next();
            w = convertOneSetToWorker();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return w ;
    }


    private Worker convertOneSetToWorker() {
        Worker w = new Worker();
        try {
            w.setId(rs.getLong(1));
            w.setName(rs.getString(2));
            w.setAge(rs.getInt(3));
            w.setWage(rs.getDouble(4));
            w.setActive(rs.getBoolean(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return w;
    }

    public List<Worker> findAll() {
        List<Worker> list = new ArrayList<>();
        String sql = "select * from Worker";
        try {
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                list.add(convertOneSetToWorker());
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list ;
    }

    public List<Worker> findByNameAndAgeAndWage(String name , int minAge , int maxAge, double minWage, double maxWage) {
        List<Worker> list = new ArrayList<>();
        String sql = "select * from Worker where name = ? AND age >= ? AND age <= ? AND wage >= ? AND wage <= ? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, minAge);
            pstmt.setInt(3, maxAge);
            pstmt.setDouble(4, minWage);
            pstmt.setDouble(5, maxWage);
            rs = pstmt.executeQuery();
            while(rs.next())
                list.add(convertOneSetToWorker());


            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }



}
