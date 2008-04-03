package org.ussa.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.ussa.beans.AccountBean;
import org.ussa.dao.AddressDao;
import org.ussa.dao.MemberDao;
import org.ussa.model.Address;
import org.ussa.model.AddressPk;
import org.ussa.model.Member;

public class ContactController extends AbstractSimpleFormController
{

    /** Logger for this class and subclasses */
    protected final Log log = LogFactory.getLog(getClass());

    private MemberDao memberDao;
    private AddressDao addressDao;

    public ContactController()
    {
        setCommandName("member");
        setCommandClass(AccountBean.class);
    }
    protected Map referenceData(HttpServletRequest request) throws Exception
    {
        Map model = new HashMap();
        //model.put("selected", selected);
        return model;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws Exception
    {
        ModelAndView result = null;
        AccountBean obj = (AccountBean) command;
        result = new ModelAndView(getSuccessView());
        return result;
    }


    protected Object formBackingObject(HttpServletRequest request)
            throws Exception
    {
        AccountBean obj = new AccountBean();
        Long id = ServletRequestUtils.getLongParameter(request, "id", 0);
        System.out.println("id["+id+"]");
        Member member = new Member();
        Address address = new Address();

        if (id != 0)
        { //Get member and address if they exist
            member = memberDao.get(id);

            if (member == null)
            {
                member = new Member();
            }

            AddressPk addressPk = new AddressPk();
            addressPk.setType("P");
            addressPk.setId(id);//new Long(6050553)); //Micahel Henderson, lucky john
            address = addressDao.get(addressPk);
            if (address == null)
            {
                address = new Address();
            }
        }

        obj.setAddress(address);
        obj.setMember(member);
        return obj;
    }

    public void setMemberDao(MemberDao memberDao)
    {
        this.memberDao = memberDao;
    }
    public void setAddressDao(AddressDao addressDao)
    {
        this.addressDao = addressDao;
    }

}
