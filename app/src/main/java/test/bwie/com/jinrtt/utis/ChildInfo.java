package test.bwie.com.jinrtt.utis;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;



@Table(name = "child_info",onCreated = "")
public class ChildInfo {
    @Column(name = "id",isId = true,autoGen = true,property = "NOT NULL")
    private int id;
    @Column(name = "uri")
    private String uri;
    @Column(name = "title")
    private String title;

    public ChildInfo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
