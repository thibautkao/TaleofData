*** Explanation of the implementation of the program. ***

1- We have created a generic class to avoid the strong coupling with a particular data type.
2- Our program uses a layered architecture:
   bean: contains our POJOs or bean
   service: contains the class for handling and transforming the CSV file
   the Program class which represents the main program

3- We have the unit tests to make sure that our DataService class works well


Explanation of layers:
Bean: we have two POJOs, a generic GenericData and another Data which inherits from the first one.
Service: we have a generic class which has three main functions namely:
 a function for reading the CSV file
 a function for transforming data from the CSV file. To have control over the type of transformation to be performed on our POJOs, we take
     parameter, a functional interface: Function <T, T>
 a function for saving data transformed into a file

Main: the main program which calls the functions to process our file is passed as a parameter
