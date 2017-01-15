package com.pvt;

import com.pvt.beans.Ad;

import static com.pvt.beans.IObjects.*;
import static com.pvt.beans.IPriority.*;
import static com.pvt.beans.IStatus.*;

import com.pvt.dao.DAO;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class AdDAOTest extends TestCase {
    static DAO dao = DAO.getDAO();
    static Ad ad = new Ad();
    int MANAGER1 = 2;
    int MANAGER2 = 3;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AdDAOTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AdDAOTest.class);
    }

    /**
     * Rigourous Test :-)
     */

    public void testA_CreateAd() {
        // create
        ad.setSubject("не работает WBA");
        ad.setDescription("Bill acceptor failure");
        ad.setFk_priority(low);
        ad.setFk_status(open);
        ad.setFk_zia(GmlKisileva);
        ad.setFk_user(MANAGER1);
        boolean id = dao.ad.create(ad);
        assertTrue(id);
    }

    public void testB_UpdateAd() {
        // update
        ad.setSubject("не работает Cashcode");
        ad.setDescription("Jam in Bill acceptor");
        ad.setFk_priority(high);
        ad.setFk_status(inprogress);
        ad.setFk_zia(LidSovetskaja);
        ad.setFk_user(MANAGER2);

        boolean updated = dao.ad.update(ad);
        assertTrue(updated);
    }

    public void testC_ReadAd() {
        // read
        Ad updatedAd = dao.ad.read(ad.getId());

        assertEquals("не работает Cashcode", updatedAd.getSubject());
        assertEquals("Jam in Bill acceptor", updatedAd.getDescription());
        assertEquals(high, updatedAd.getFk_priority());
        assertEquals(inprogress, ad.getFk_status());
        assertEquals(LidSovetskaja, ad.getFk_zia());
        assertEquals(MANAGER2, ad.getFk_user());

    }

    public void testD_DeleteAd() {
        // delete
        boolean deleted = dao.ad.delete(ad);
        assertTrue(deleted);
    }
}