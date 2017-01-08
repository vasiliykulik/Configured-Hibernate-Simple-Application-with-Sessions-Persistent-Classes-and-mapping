package net.proselyte.hibernate.example.mappings.collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by Raketa on 08.01.2017.
 */
/*Связывается (mapped) с помощью элемента <list> и инициализируется с помощью java.util.ArrayList*/
public class DeveloperRunner {
  private static SessionFactory sessionFactory;

  public static void main(String[] args) {
    sessionFactory = new Configuration().configure().buildSessionFactory();

    DeveloperRunner developerRunner = new DeveloperRunner();

    System.out.println("Creating the collection of projects.");

    ArrayList projects1 = new ArrayList();
    projects1.add(new Project("Proselyte Tutorial", "proselyte.net"));
    projects1.add(new Project("SkybleLib", "SkybleSoft"));

    ArrayList projects2 = new ArrayList();
    projects2.add(new Project("Some Project", "Some Company"));
    projects2.add(new Project("One more Project", "One more Company"));

    System.out.println("Adding developer's records to the DB");

    Integer developerId1 = developerRunner.addDeveloper("Proselyte", "Developer", "Java Developer", 2, projects1);
    Integer developerId2 = developerRunner.addDeveloper("Peter", "UI", "UI Developer", 4, projects2);

    System.out.println("List of developers");
    developerRunner.listDevelopers();

    System.out.println("===================================");
    System.out.println("Updating Proselyte");
    developerRunner.updateDeveloper(developerId1, 3);

    System.out.println("Final list of developers");

    developerRunner.listDevelopers();
    System.out.println("===================================");
    sessionFactory.close();
  }

  public Integer addDeveloper(String firstName, String lastName, String specialty, int experience, ArrayList projects) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;
    Integer developerId = null;

    transaction = session.beginTransaction();
    Developer developer = new Developer(firstName, lastName, specialty, experience);
    developer.setProjects(projects);
    developerId = (Integer) session.save(developer);
    transaction.commit();
    session.close();
    return developerId;
  }

  public void listDevelopers() {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    transaction = session.beginTransaction();
    Collection <Developer> developers = session.createQuery("FROM Developer").list();
    for (Developer developer : developers) {
      System.out.println(developer);
      Collection <Project> projects = developer.getProjects();
      for (Project project : projects) {
        System.out.println(project);
      }
      System.out.println("\n================\n");
    }
    session.close();
  }

  public void updateDeveloper(int developerId, int experience) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    transaction = session.beginTransaction();
    Developer developer = (Developer) session.get(Developer.class, developerId);
    developer.setExperience(experience);
    session.update(developer);
    transaction.commit();
    session.close();
  }

  public void removeDeveloper(int developerId) {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    transaction = session.beginTransaction();
    Developer developer = (Developer) session.get(Developer.class, developerId);
    session.delete(developer);
    transaction.commit();
    session.close();
  }

}
