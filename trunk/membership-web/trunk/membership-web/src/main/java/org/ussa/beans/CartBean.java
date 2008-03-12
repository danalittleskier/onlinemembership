package org.ussa.beans;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.ussa.model.Inventory;


public class CartBean
{
    Set<Inventory> memberships;
    //TODO: add the following:
    Set<LineItemBean> magazines = new HashSet<LineItemBean>();
    //Set<ShirtBean>
    //Set<DecalBean>

    Set<LineItemBean> lineItems;
    BigDecimal total;
    String totalCost;

    public Set<Inventory> getMemberships()
    {
        return memberships;
    }

    public void setMemberships(Set<Inventory> memberships)
    {
        this.memberships = memberships;
    }

    public void addLineItem(LineItemBean lineItem)
    {
        if (lineItems == null)
        {
            lineItems = new HashSet<LineItemBean>();
        }
        lineItems.add(lineItem);
    }

    public void addMembership(Inventory membership)
    {
        if (memberships == null)
        {
            memberships = new HashSet<Inventory>();
        }
        memberships.add(membership);
    }

    public void deleteMembership(String membershipId)
    {
        if (memberships != null && membershipId != null)
        {
            for (Iterator<Inventory> i = memberships.iterator(); i.hasNext();)
            {
                if (membershipId.equalsIgnoreCase(i.next().getId()))
                {
                    i.remove();
                    break; // done
                }
            }
        }
    }

    public Set<LineItemBean> getShoppingCart()
    {
        BigDecimal cost = BigDecimal.ZERO;
        lineItems = new HashSet<LineItemBean>();
        System.out.println("Beg: shoppingCart["+lineItems.size()+"], members["+memberships.size()+"]");

        for (Inventory m:memberships)
        {
            LineItemBean lineItem = new LineItemBean();
            String description = new String();
            BigDecimal amount = BigDecimal.ZERO;
            if (m.getDescription() != null)
            {
                description = m.getDescription();
            }
            if (m.getAmount() != null)
            {
                amount = m.getAmount();
            }

            lineItem.setDescription(description);
            lineItem.setAmount(amount);
            if (m.getAmount() != null)
            {

                cost = cost.add(m.getAmount());
                System.out.println("Add amount["+m.getAmount()+"] to cost["+cost+"]");
            }
            addLineItem(lineItem);
            Set<LineItemBean>hardCodedMagazines = getMagazines(); //TODO: DELETE
            lineItems.addAll(hardCodedMagazines);//TODO: DELETE
            //lineItems.addAll(magazines); //Keep this
        }
        System.out.println("End: shoppingCart["+lineItems.size()+"], members["+memberships.size()+"], cost["+cost+"]");
        if (cost == null)
        {
            cost = BigDecimal.ZERO;
        }
        setTotalCost(cost.toString());
        System.out.println(getTotalCost());
        return lineItems;
    }

    public BigDecimal getTotal()
    {
        total = BigDecimal.ZERO;
        if (lineItems== null)
        {
            return BigDecimal.ZERO;
        }
        for (LineItemBean li:lineItems)
        {
            if (li.getAmount() != null)
            {

                total = total.add(li.getAmount());
            }
        }
        return total;
    }

    public void setTotal(BigDecimal total)
    {
        this.total = total;
    }


    public void setTotalCost(String totalCost)
    {
        this.totalCost = totalCost;
    }

    public Set<LineItemBean> getLineItems()
    {
        return lineItems;
    }

    public void setLineItems(Set<LineItemBean> lineItems)
    {
        this.lineItems = lineItems;
    }

    public String getTotalCost()
    {
        return totalCost;
    }

    public Set<LineItemBean> getMagazines()
    {
        //Hardcode a magazine TO THE CART
        Set<LineItemBean> magazines = new HashSet<LineItemBean>();//TODO: DELETE
        LineItemBean magazine = new LineItemBean();//TODO: DELETE
        magazine.setDescription("Ski Racing");//TODO: DELETE
        magazine.setAmount(BigDecimal.TEN);//TODO: DELETE
        magazines.add(magazine);//TODO: DELETE

        return magazines;
    }

    public void setMagazines(Set<LineItemBean> magazines)
    {
        this.magazines = magazines;
    }

    // process payment to wells fargo
    public String processPayment() {


    	return "complete transaction";
    }
}
