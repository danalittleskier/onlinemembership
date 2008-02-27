package org.ussa.spring.flows.registration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.context.servlet.ServletExternalContext;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.ussa.beans.AccountBean;
import org.ussa.dao.AddressDao;
import org.ussa.dao.ClubDao;
import org.ussa.dao.MemberDao;
import org.ussa.dao.StateDao;
import org.ussa.dao.InventoryDao;
import org.ussa.manager.MemberManager;
import org.ussa.model.Address;
import org.ussa.model.AddressPk;
import org.ussa.model.Club;
import org.ussa.model.Member;
import org.ussa.model.State;
import org.ussa.model.Inventory;

public class RegistrationAction extends MultiAction implements Serializable
{
    private MemberManager memberManager;
    private MemberDao memberDao;
    private AddressDao addressDao;
    private StateDao stateDao;
    private ClubDao clubDao;
    private InventoryDao inventoryDao;


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
        //System.out.println("zId["+zId+"]");
        Long id = null;
        if (zId != null)
        {
            id = Long.parseLong(zId);
        }
        //System.out.println("id["+id+"]");
        Member member = new Member();
        Address address = new Address();
        Inventory inventory = new Inventory();

        List<State> usStates = stateDao.getAllStateUS_CodeOrdered();
        obj.setUsStates(usStates);

        List<Inventory> inv = inventoryDao.getAllMemberships_TypeOrdered();
        obj.setMembership(inv);


         System.out.println("brad["+inv.toString()+"]");
        System.out.println("brad["+inventory.getDescription()+"]");
        //MutableAttributeMap requestScope = context.getRequestScope();
        //request.setAttribute("usStates", usStates);

        if (id != null)
        {
            //Member exists, do a renew and pre-populate info
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
            obj.setAddress(address);
            obj.setMember(member);
            obj.setInventory(inventory);
            return result("renew");
        }
        else
        {
            //Member does not exist, do a register
            obj.setAddress(address);
            obj.setMember(member);
            obj.setInventory(inventory);
            return result("register");
        }
    }

    public Event findClubInfo(RequestContext context) throws Exception
    {
        HttpServletRequest request = ((ServletExternalContext)context.getExternalContext()).getRequest();
        AccountBean obj = (AccountBean) context.getFlowScope().get("accountBean");
        List<Club> clubs = new ArrayList<Club>();
        String stateAffiliation = new String();
        System.out.println("Address's city["+obj.getAddress().getCity()+"]");
        System.out.println("Address's state["+obj.getAddress().getStateCode()+"]");
        System.out.println("StateAffiliation["+obj.getStateAffiliation()+"]");

        if (obj != null)
        {
            System.out.println("Club Info -- Orig state["+obj.getStateAffiliation()+"]");
            if (obj.getStateAffiliation() != null)
            { //If I change the state affiliation (in stateClub) set it here
                stateAffiliation = obj.getStateAffiliation();
            }
            else if (obj.getAddress() != null)
            { //Coming through first pass regular flow
                if (obj.getAddress().getStateCode() != null)
                { //This should always be hit as the user is required to put in an address on the prior screen
                    stateAffiliation = obj.getAddress().getStateCode();

                }
            }
            if (stateAffiliation.length()!=0)
            {
                System.out.println("state["+stateAffiliation+"]");
                clubs = clubDao.findByStateCode(stateAffiliation);
                System.out.println("clubs["+clubs.size()+"]");
            }
        }
        obj.setClubsForState(clubs);
        //System.out.println("state["+stateAffiliation+"]");
        obj.setStateAffiliation(stateAffiliation);
        obj.getMember().setStateCode(stateAffiliation);//TODO: get rid of stateAffiliation should just use member.stateCode
        return result("form");
    }


    public void setStateDao(StateDao stateDao)
    {
        this.stateDao = stateDao;
    }
    public void setClubDao(ClubDao clubDao)
    {
        this.clubDao = clubDao;
    }
    /**
     * @param inventoryDao the inventoryDao to set
     */
    public void setInventoryDao(InventoryDao inventoryDao)
    {
        this.inventoryDao = inventoryDao;
    }

}
