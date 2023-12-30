package com.check_in_system.demo.entities.Tables;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data 
public class Travelers {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String nationality;
    private String phoneNum;
//the column name gonna be flight_num , but the json format property passed gonna be flight 
    @ManyToOne
    @JoinColumn(
        name = "flight_num",
        referencedColumnName = "num"
    )
    @JsonIgnoreProperties("travelers") //handling circular refrencing 
    private Flights flight;//u will store a Fligh obj here in this cell

    @OneToMany(mappedBy = "traveler")//majd:writing mappedBy so i dont make any new relations(the relation is already made)
    @JsonIgnoreProperties("travelers")
    private List<Baggage> baggage;

//the join column gonna be in the Many table (majd)

/*In the Travelers table, there will be a foreign key column (let's call it flight_num based on the default naming strategy) that references the primary key (num) of the Flights table. This foreign key column stores the num values of the corresponding flights.

So, the Travelers table will have a column (flight_num) with values that correspond to the primary key values (num) in the Flights table. It does not store the entire Flights object, just the reference to the associated flight through the foreign key.

In summary, the relationship is implemented using a foreign key column in the Travelers table, and this column stores the num values of the associated flights.*/
/*
 in the Flights table, there won't be a column storing a list of travelers. In a relational database, a @OneToMany or @ManyToMany association is typically represented by a foreign key in the table on the "many" side (in this case, the Travelers table). This foreign key establishes a link between rows in the two tables.

In your case, the Travelers table will have a column named flight_num (as specified by @JoinColumn(name = "flight_num", referencedColumnName = "num")) that serves as a foreign key referencing the num column in the Flights table. This column in the Travelers table will contain the value of the num of the associated flight.

So, if you have a row in the Travelers table with flight_num equal to, for example, "ABC123," it means that traveler is associated with the flight in the Flights table where num is "ABC123." This is how the association is established at the database level.

It's important to note that JPA and Hibernate take care of these database-level details for you. When you retrieve a Flights entity, JPA/Hibernate will use the foreign key relationship to fetch the associated Travelers entities, and vice versa. You work with Java objects in your code, and the ORM framework handles the translation between Java objects and database tables.
    */
    /*
    The choice between using @ManyToOne and @OneToMany annotations in a bidirectional association depends on the direction of the relationship and how you want to navigate it.

@ManyToOne and @OneToMany (Bidirectional): This is used when you want to navigate the association from both sides. It allows you to access the associated entities from both the owning and the inverse side.

Example:

java
Copy code
// In Flights entity
@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
private List<Travelers> travelers;

// In Travelers entity
@ManyToOne
@JoinColumn(name = "flight_num", referencedColumnName = "num")
private Flights flight;
@ManyToOne (Unidirectional): This is used when you only need to navigate the association from the owning side. The inverse side won't have a direct reference to the owning side.

Example:

java
Copy code
// In Travelers entity
@ManyToOne
@JoinColumn(name = "flight_num", referencedColumnName = "num")
private Flights flight;
@OneToMany (Unidirectional): This is used when you only need to navigate the association from the inverse side. The owning side won't have a direct reference to the inverse side.

Example:

java
Copy code
// In Flights entity
@OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name = "flight_num", referencedColumnName = "num")
private List<Travelers> travelers;
In many cases, bidirectional associations are chosen for ease of navigation. However, if you only need to navigate the relationship in one direction, you can choose the appropriate unidirectional mapping.

Keep in mind that bidirectional associations come with responsibilities, such as managing both sides of the relationship when updating the database to ensure data consistency.
    */
    
    
}
