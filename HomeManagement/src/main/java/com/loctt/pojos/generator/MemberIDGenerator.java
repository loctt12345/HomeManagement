/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.pojos.generator;

import java.io.Serializable;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 *
 * @author loc12345
 */
public class MemberIDGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj)
            throws HibernateException {
        Query q = session.createQuery("SELECT m.id FROM member m ORDER BY m.id DESC"); 
        q.setMaxResults(1);
        String number = String.format("%02d",  Integer.parseInt(
                q.getSingleResult().toString().substring(2)) + 1);
        return "TV" + number;
    }

}
