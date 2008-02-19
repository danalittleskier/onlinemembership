package com.ussa.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.ServletRequestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import com.ussa.spring.controller.AbstractSimpleFormController;

import com.ussa.dao.MemberDao;
import com.ussa.manager.MemberManager;
import com.ussa.model.Member;

public class ContactController extends AbstractSimpleFormController
{

    /** Logger for this class and subclasses */
    protected final Log log = LogFactory.getLog(getClass());

    private MemberDao memberDao;
    private MemberManager memberManager;
    public ContactController()
    {
        setCommandName("member");
        setCommandClass(Member.class);
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
        Member obj = (Member) command;
        result = new ModelAndView(getSuccessView());
        return result;
    }


    protected Object formBackingObject(HttpServletRequest request)
            throws Exception
            {
                Member obj = new Member();
                Long ussaId = ServletRequestUtils.getLongParameter(request, "ussaId", 0);




         return obj;
            }

    /**
     * @return the memberDao
     */
    public MemberDao getMemberDao()
    {
        return memberDao;
    }
    /**
     * @param memberDao the memberDao to set
     */
    public void setMemberDao(MemberDao memberDao)
    {
        this.memberDao = memberDao;
    }
    /**
     * @return the memberManager
     */
    public MemberManager getMemberManager()
    {
        return memberManager;
    }
    /**
     * @param memberManager the memberManager to set
     */
    public void setMemberManager(MemberManager memberManager)
    {
        this.memberManager = memberManager;
    }

}
