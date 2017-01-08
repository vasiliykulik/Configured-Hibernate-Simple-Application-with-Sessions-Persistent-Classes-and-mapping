package net.proselyte.hibernate.mappings.collection;

/**
 * Created by Raketa on 08.01.2017.
 */
public class Project {
  private int id;
  private String projectName;
  private String companyName;

  /**
   * Constructors
   */
  public Project() {
  }

  public Project(String projectName, String companyName) {
    this.projectName = projectName;
    this.companyName = companyName;
  }


  /**
   * Getters and Setters
   */
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getCompanyName() {
    return companyName;
  }


  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  @Override
  public String toString() {
    return "Project:\n" +
            "id: " + id +
            "\nProject Name: " + projectName +
            "\nCompany Name: " + companyName + "\n";
  }
}
