# Spring_boot_airline_system
A complete back_end app, that supports  the minimum functionalities provided by any airline.

The  application has two main contollers(rest api), one for handling the requests for (saving, getting, updating, deleting) the entities I have such as the traveler, baggage, flight,...

The second controller handles the registration and logging for employees(users).
I provided the service of registeration directly in the app , also provided the service of logging using "Google".
I used OAuth2.0 for authorization and handled the authenticated user to be saved in my "user" table.

I designed the application to have three layers a "controller" to handle requests, service layer to handle the business logic also a repository layer to handle 
the interaction with the database I have.

I used MySQL as my dataBase, and used insomnia to fetch requests to my controller layer.
