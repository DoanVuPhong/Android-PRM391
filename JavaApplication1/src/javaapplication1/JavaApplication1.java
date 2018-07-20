/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;

/**
 *
 * @author pdoan4
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Event e = new Event();
        e.setCode("ANC 1234");
        e.setLocation("365 Etown cong hoa");
        e.setStatus(1);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaApplication1PU");
        
        EventJpaController controller = new EventJpaController(emf);

        
        controller.create(e);
        
        List<Event> list = controller.findEventEntities();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
            
        }
        
        
        
        
    }

}
