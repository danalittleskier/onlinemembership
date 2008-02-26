package org.ussa.model;

import java.io.*;
import javax.persistence.*;


@Entity
@Table (name="PARAMETERTABLE")

public class ParameterTable implements Serializable {

    //Member table fields

    private String parameterCode;
    private String parameterTable;

    /**
     * @return the pARAMETER_CODE
     */
    @Id
    @Column(name = "PARAMETER_CODE", nullable = false, length=20, unique=false)
    public String getPARAMETER_CODE()
    {
        return parameterCode;
    }

    /**
     * @param parameter_code the pARAMETER_CODE to set
     */
    public void setPARAMETER_CODE(String parameter_code)
    {
        parameterCode = parameter_code;
    }

    /**
     * @return the pARAMETER_TABLE
     */
    @Column(name = "PARAMETER_TABLE", nullable = true, length=20, unique=false)
    public String getPARAMETER_TABLE()
    {
        return parameterTable;
    }

    /**
     * @param parameter_table the pARAMETER_TABLE to set
     */
    public void setPARAMETER_TABLE(String parameter_table)
    {
        parameterTable = parameter_table;
    }

    public ParameterTable() {
    }

}
