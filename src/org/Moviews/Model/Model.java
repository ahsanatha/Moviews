package org.Moviews.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.Moviews.Database.Database;

public abstract class Model {

    public abstract Object findData(String x);

    public boolean isExist(String namatable, String namakolom, String id) throws SQLException {
        boolean cek = false;
        Database db = new Database();
        db.Connect();
        String query = "SELECT * FROM `" + namatable + "` WHERE `" + namakolom + "` = '" + id + "';";
        db.setRs(query);
        ResultSet rs = db.getRs();
        if (!db.isRsEmpty(db.getRs())) {
            cek = true;
        }
        return cek;
    }

    public void Delete(String tbname, String clname, String id) {
        Database db = new Database();
        db.Connect();
        String query = "DELETE FROM `" + tbname + "` WHERE  `" + clname + "`='" + id + "';";
        System.out.println(query);
        if (!db.Manipulate(query)) {
            System.out.println("Data " + id + "telah di hapus.");
        } else {
            System.out.println("Data gagal di hapus.");
        }
    }

    public int getCurrentId(String tbname, String clname, String key) throws SQLException {
        Database db = new Database();
        db.Connect();
        int x = 1;
        String query = "SELECT " + clname + " FROM `" + tbname + "` WHERE " + clname + " = '" + key + String.valueOf(x) + "';";
        boolean m = isExist(tbname, clname, key + (String.valueOf(x)));
        while (m) {
            x = x + 1;
            m = isExist(tbname, clname, key + (String.valueOf(x)));
            System.out.println(query);
        }
        db.Disconnect();
        return x;
    }
}
