package org.ussa.bl;

import java.util.List;

import org.springframework.validation.Errors;
import org.ussa.beans.AccountBean;
import org.ussa.beans.UserBean;
import org.ussa.model.Member;

public interface CasLdap {

    public abstract UserBean getUserInfo(String username);
    public abstract boolean doesIdBelongToAccount(String id, UserBean userBean);
    public abstract void addUssaIdToAccount(UserBean userBean, AccountBean accountBean, String ussaId);
    public abstract void addGroupsToUser(String username, List<String> groups);
    
}