package com.example.wit.templates;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CrudController<RequestObj, ResponseObj, key> {
    public ResponseEntity<List<ResponseObj>> read ();
    public ResponseEntity<ResponseObj> read (key id);
    public ResponseEntity<String> create (RequestObj obj);
    public ResponseEntity<String> update (key id, RequestObj obj);
    public ResponseEntity<String> delete (key id);
}
