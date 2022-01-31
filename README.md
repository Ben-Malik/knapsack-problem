# Mobiquity Package-problem

**Problem Definition:**

The very API is aimed at solving the below problem commonly known as the Knapsack problem.

Assume that you want to send your friend a package with different things. Each thing you put inside the package has such parameters as index number, weight and cost. 
The package has a weight limit. Your goal is to determine which things to put into the package so that the total weight is less than or equal to the package limit and the total cost is as large as possible. You would prefer to send a package which weighs less in case there is more than one package with the same price.

This API accepts as its first argument a path to a filename. The input file contains several lines. 
Each line is one test case.  
Each line contains the weight that the package can take (before the colon)  and the list of items you need to choose. Each item is enclosed in parentheses where the 1st number is an item’s index number, the 2nd is its weight and the 3rd is its cost. E.g. 

```
81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48) 
8 : (1,15.3,€34) 
75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78) 
56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64) 
```

For each test case, this API provides a new row in the output string (items’ index numbers are separated by comma). E.g.  

```
4 
- 
2,7 
8,9
```

**Constraints:**
1. Max weight that a package can take is ≤ 100 
2. There might be up to 15 items you need to choose from 
3. Max weight and cost of an item is ≤ 100
4. For floating values (target weight, item weight, cost), the algorithm guarantees to run correctly for maximum of 2 decimal points.
5. API handles both the lines with whitespace at the end of the line and those without the whitespace at the end of the lines.
6. This API assumes that the input file is in UTF-8 encoding.

**Solution:**

>***Input Reading***
   Input is read by Files.lines() to get the file lines as a stream and continue transforming the stream until the solution is reached for each line.
   
>***Parsing and Validating***
   For parsing and validating, regex patterns are used. In case of invalid input meaningful exceptions are thrown.
   
>***Solving***
   As a solution to the knapsack problem, dynamic programming approach is implemented because of the complexity of recursive strategy. Because the problem is to be solved with    Double values the complexity is increased by 100 times. [nW100].
   
>***Data models***

   ```
   Payload (Double targetWeight, List<Item> items)
   Item  (Integer index, Double weight, Double cost)
   ```

   
>***Packer***
   ```
   reader.readLines(filePath)                                           //returns line stream
                    .map(lineParserService::parse)                      //returns problem stream
                    .map(problemService::solve)                         //returns solution stream
                    .map(List<Integer>::toString)                       //returns string value of every solution
                    .collect(Collectors.joining("\n"));                 //prints the solution line by line 
   ```

   

**Environment:** 
   
```
Java 11

Junit 5.7.2

Mockito 3.11.0
```


**Cleaning:** 

`./gradlew clean`

**Building:** 

`./gradlew build`

**Testing:**

Most of the edge cases are covered by the validations and they are also tested in the unit tests and integration tests. Line coverage is 96%. 
  
`./gradlew test`

**Running:** 

Since this is a library, it doesn't have a main method, you should include the library in your project and call the method to be able to run it. 
If you want to publish this library to your local maven artifactory, after building, you should run the 5 commands below:
   
```
./gradlew generateMetadataFileForMavenPublication
./gradlew generatePomFileForMavenPublication
./gradlew publish
./gradlew publishMavenPublicationToMavenLocal 
./gradlew publishToMavenLocal
```

Then, import this library from the local maven artifactory and now you can start calculating by calling the static method

```
static com.mobiquity.packer.pack(String filePath) throws APIException
```



