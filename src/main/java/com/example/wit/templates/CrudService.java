package com.example.wit.templates;

import java.util.List;

public interface CrudService<RequestObj, ResponseObj, key> {
    public List<ResponseObj> read ();
    public ResponseObj read (key id);
    public void create (RequestObj obj);
    public void update (key id, RequestObj obj);
    public void delete (key id);
}
