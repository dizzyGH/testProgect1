package ru.spb.nastavshev.testReports;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Data
@Entity
public class UserData {

    private static DateFormat format = new SimpleDateFormat("YYYY-MM-DD-HH", Locale.ENGLISH);

    @Id
    @GeneratedValue
    private Long id;
    private String ssoid;
    private Integer ts;
    private String grp;
    private String type;
    private String subtype;
    private String url;
    private String orgid;
    private String formid;
    private String code;
    private String ltpa;
    private String sudirresponse;
    private Date ymdh;

    public UserData(String ssoid, String ts, String grp, String type, String subtype, String url, String orgid, String formid, String code, String ltpa, String sudirresponse, String ymdh) {
        this.ssoid = ssoid;
        this.ts = Integer.valueOf(ts);
        this.grp = grp;
        this.type = type;
        this.subtype = subtype;
        this.url = url;
        this.orgid = orgid;
        this.formid = formid;
        this.ltpa = ltpa;
        this.sudirresponse = sudirresponse;
        try {
            this.ymdh = format.parse(ymdh);
        } catch (ParseException e) {
           // System.out.println("ymdh: " + ymdh  );
            e.printStackTrace();
        }
    }


}
