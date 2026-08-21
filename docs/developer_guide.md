Gym Management system 

Architecture overview: 

The application follows a standard multi tier Data Access Object (DAO) architecture:

Main.Java ----> Model Layer (User, Membership, WorkoutClasses, Merchandise) ------> DAO Layer (Userdao, Membershipdao, WorkoutClassesdao, Merchandisedao) -----------> PostgreSQL database (gym-db) 

