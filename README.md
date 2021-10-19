# TaleofData
Technical Test: Big Data Engineer

To test the application in your environment, you will need to download the project in zipped format and paste it in a directory that you will have created on your computer.
And then the Unzip the file.
1. Then launch an IDE, I use IntelliJ IDEA
2. Go to the "File" menu -> "Open" -> Choose the path where your directory containing your project is located -> choose the pom.xml file and click "OK"
3. Go to the following tree structure: src -> main -> resources -> in  Open data.csv (Add lines name, double example: 

            thomas,45.6
            mathieu,87.4
            Pierre,87.8
            
Go to in File and do "Save All"

4. Go to the tree structure src -> main -> java -> org -> example -> Program
Right click and click on "Run Program"

5. Go to the src tree -> main -> resources -> out
to see the content of the transformation result file.

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

Main: the main program which calls the functions to process our file is passed as a parameter.
