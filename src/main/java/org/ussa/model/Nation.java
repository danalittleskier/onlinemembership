package org.ussa.model;

import java.io.*;
import javax.persistence.*;


@Entity
@Table (name="NATION")

public class Nation implements Serializable {

    //Member table fields

    private String nationCode;

    public Nation() {
    }

    /**
     * @return the nATION_CODE
     */
    @Id
    @Column(name = "NATION_CODE", nullable = false, length=20, unique=false)
    public String getNATION_CODE()
    {
        return nationCode;
    }

    /**
     * @param nation_code the nATION_CODE to set
     */
    public void setNATION_CODE(String nation_code)
    {
        nationCode = nation_code;
    }


}
