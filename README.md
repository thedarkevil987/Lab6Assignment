# ProjectX Readme
ProjectX is a Java program that can read an AUTOSAR (Automotive Open System Architecture) XML file, sort its containers by the short name tag and output the sorted file in a new file.

## Installation
1- Install Java on your machine if it's not already installed.<br>
2- Download the ProjectX.java file to your local machine.
##  Usage
1- Open a command prompt or terminal and navigate to the directory containing the ProjectX.java file.<br>
2- Compile the Java program using the following command:<br>
```cmd
javac ProjectX.java
```
3- Run the Java program using the following command:
```cmd
java ProjectX <path_to_autosar_file>
```
Replace <path_to_autosar_file> with the path to the AUTOSAR XML file you want to sort.

## Exceptions
ProjectX throws two exceptions:

1- NotVaildAutosarFileException: This exception is thrown when the file does not have the ".arxml" extension.<br>
2- EmptyAutosarFileException: This exception is thrown when the file is empty.
## Output
The output of the program is a new AUTOSAR XML file with "_mod" appended to the original file name. The sorted containers are written to the new file in the same format as the original file.

## Contributing
If you'd like to contribute to the development of ProjectX, feel free to submit a pull request or open an issue.

## License
ProjectX is licensed under the [MIT License](https://opensource.org/license/mit/).
