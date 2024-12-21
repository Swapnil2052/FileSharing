# File Sharing Project

This project is a file-sharing application built using Spring Boot. It includes stateless user authentication with JWT and MySQL for data storage.

## Features
- Secure user authentication using JWT.
- File upload and sharing capabilities.
- Database integration with MySQL.

## Technologies Used
- **Backend:** Spring Boot, Hibernate, JWT
- **Database:** MySQL

## Installation
1. Clone the repository:
   
   git clone https://github.com/Swapnil2052/FileSharing.git

## Details
- User needs to signup and then can login into application.
- There are two roles, ops user and client user, one can strictly upload the file while other can only list the files or download it.
- Files of type pptx, docx, xlsx are only allowed to upload in this app.
- JWT Auth is used, so user needs to pass the token in order to access their role functionalities.
- Postman dump of every API call is attached with project for further illustrations.
