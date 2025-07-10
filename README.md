# Format Parser with FTP/SFTP Support
A Java program that parses common file formats (JSON, XML, YAML, CSV) and demonstrates FTP/SFTP operations.

## Requirements
- Java 21
- Maven

## Features
- **File Format Parsing:**
  - JSON files
  - XML files  
  - YAML files
  - CSV files
- **Remote File Operations:**
  - FTP connection and file operations
  - SFTP connection and file operations
  - List files on remote servers
  - Download files from remote servers

## Sample Output
Sample files created successfully!

Successfully parsed JSON
Content: {
  "name" : "Khaled",
  "age" : 30,
  "city" : "Cairo"
}

Successfully parsed CSV
Content: [{name=Ali, age=25, city=Cairo}, {name=Omar, age=35, city=Sohag}, {name=Waleed, age=28, city=Giza}]

Successfully parsed YAML
Content{person={name=Sarah, age=27, skills=[Java, Python, SQL]}}

Successfully parsed XML
Content{name=Ahmed Mohamed, department=IT, salary=75000}

Connecting to FTP test server...
Files on the server:
1- pub
2- readme.txt
Downloaded: readme.txt
FTP operations completed

Connecting to SFTP test server...
Files on SFTP server:
- .
- ..
- pub
- readme.txt
Downloaded: readme.txt
SFTP operations completed
