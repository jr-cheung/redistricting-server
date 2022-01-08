package com.example.demo;

import org.json.JSONObject;
import javax.persistence.*;
import java.util.Collection;

@Entity
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @Transient
    private boolean split;
    @Transient
    private Collection<Precinct> precincts;
    @Convert(converter = JSONObjectConverter.class)
    private JSONObject geometry;

    protected County() {}

    //getters
    public String getName() {
        return name;
    }

    public boolean isSplit() {
        return split;
    }

    public Collection<Precinct> getPrecincts() {
        return precincts;
    }

    public JSONObject getGeometry(){
        return geometry;
    }
}
