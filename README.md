# Context:

This project was done during the CodeForAll_ Online Bootcamp, as an assignment from week-11 - World Wide Web.

# Goal:

Create a web server that responds to GET requests and serves static HTML pages.

# Requirements:

The main requirements are: 

- Create a Java web server that only responds to GET requests.
  
- The server must listen on a specific port (e.g., 8095).

- Store HTML files and static resources in the www/ directory inside src/main/.

- Serve static files (HTML, images, icons) based on incoming requests.

- Respond with a 200 (OK) status code when the requested file is found.

- Respond with a 404 (Not Found) status code and display the 404.html page when the requested file does not exist.

- Configure Maven to package the project and generate an executable JAR file.


# Achievements:

- The key achievements include:

- Consolidating knowledge of client-server architecture.

- Understanding how the HTTP protocol works and the request-response flow.

- Learning how HTTP requests and responses are structured.

- Practicing the use of Maven to compile and package Java applications.


# Challenges:

During the development of this project, I needed to understand the structure of HTTP responses, particularly the division between headers and body. The header contains essential information for the browser to process the response, such as the response status and content type, while the body contains the actual content of the requested page or file. With this understanding, I was able to correctly implement the server's response, including the header information and the appropriate body, whether for existing content or for the 404 error page.
Additionally, when handling files and directories, I used the File class from the java.io package to check the existence of the requested files in the www/ folder. This verification ensured that the server returned the correct content when the file was available and a 404 response when the file was not found.

# Project Improvements:

This project is still under development, as I aim to improve its scalability. Although the current approach ensures security and control over the files sent, I realized that it limits the application’s growth potential. Therefore, my goal is to adopt a more dynamic approach, analyzing common cases and implementing solutions that provide greater flexibility and scalability, allowing the system to grow more efficiently and adaptably in the future.

# Instructions to Test the Project:

To test the functionality of the project, follow the steps below:

1. Open the project in your IDE.
   
3. Run the Main class to start the server.
   
5. After execution, access the server through the browser by going to http://localhost:8095/ to see the server in action.
   
7. On the server, you can place the following files and directories so that the server serves them correctly:

      http://localhost:8095/
      
      http://localhost:8095/favicon.ico
      
      http://localhost:8095/404.html
      
      http://localhost:8095/images/logo.png
      
      http://localhost:8095/images/index-online.png
      

The server will serve the main index.html page. If the address is invalid, the 404.html error page will be displayed.


