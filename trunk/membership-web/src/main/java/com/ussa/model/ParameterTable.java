package com.ussa.model;

import java.io.*;
import javax.persistence.*;


@Entity
@Table (name="PARAMETERTABLE")

public class ParameterTable implements Serializable {

    //Member table fields

    private String PARAMETER_CODE;
    private String PARAMETER_TABLE;

    /**
     * @return the pARAMETER_CODE
     */
    @Id
    @Column(name = "PARAMETER_CODE", nullable = false, length=20, unique=false)
    public String getPARAMETER_CODE()
    {
        return PARAMETER_CODE;
    }

    /**
     * @param parameter_code the pARAMETER_CODE to set
     */
    public void setPARAMETER_CODE(String parameter_code)
    {
        PARAMETER_CODE = parameter_code;
    }

    /**
     * @return the pARAMETER_TABLE
     */
    @Column(name = "PARAMETER_TABLE", nullable = true, length=20, unique=false)
    public String getPARAMETER_TABLE()
    {
        return PARAMETER_TABLE;
    }

    /**
     * @param parameter_table the pARAMETER_TABLE to set
     */
    public void setPARAMETER_TABLE(String parameter_table)
    {
        PARAMETER_TABLE = parameter_table;
    }

    public ParameterTable() {
    }

}
