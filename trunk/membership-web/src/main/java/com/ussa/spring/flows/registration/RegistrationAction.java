package com.ussa.spring.flows.registration;

import java.io.Serializable;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.webflow.action.MultiAction;

import com.ussa.beans.AccountBean;
import com.ussa.dao.AddressDao;
import com.ussa.dao.MemberDao;
import com.ussa.manager.MemberManager;
import com.ussa.model.Address;
import com.ussa.model.Member;import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.context.servlet.ServletExternalContext;
import javax.servlet.http.HttpServletRequest;

public class RegistrationAction extends MultiAction implements Serializable
{
    private MemberManager memberManager;
    private MemberDao memberDao;
    private AddressDao addressDao;


    public void setMemberManager(MemberManager memberManager)
    {
        this.memberManager = memberManager;
    }
    public void setMemberDao(MemberDao memberDao)
    {
        this.memberDao = memberDao;
    }
    public void setAddressDao(AddressDao addressDao)
    {
        this.addressDao = addressDao;
    }
    public Event findContactInfo(RequestContext context) throws Exception
    {
        HttpServletRequest request = ((ServletExternalContext)context.getExternalContext()).getRequest();
        AccountBean obj = (AccountBean) context.getFlowScope().get("accountBean");
        String zId = context.getRequestParameters().get("id");
        System.out.println("zId["+zId+"]");
        Long id = null;
        if (zId != null)
        {
            id = Long.parseLong(zId);
        }
        System.out.println("id["+id+"]");
        Member member = new Member();
        Address address = new Address();

        if (id != null)
        {
            //Member exists, do a renew and pre-populate info
            member = memberDao.get(id);

            if (member == null)
            {
                member = new Member();
            }

            address = addressDao.get(id);
            if (address == null)
            {
                address = new Address();
            }
            obj.setAddress(address);
            obj.setMember(member);
            return result("renew");
        }
        else
        {
            //Member does not exist, do a register
            obj.setAddress(address);
            obj.setMember(member);
            return result("register");
        }
    }

}
