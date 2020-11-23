# ssl
Gallery image uploading with spring boot

Build spec.
    1. Spring boot framework
    2. Spring security
    3. Hibernate
    4. Java 1.8
    5. Intellij ide
    6. MySql database
    7. Inbuilt tomcat in spring boot
    
How to deploy and run the application.

    Configure MySQL database
        1. The galley.sql at the root of the project contains the necessary script to create a database and its tables
        2. Create a database user for 'galleryUser' with password 'gallery@11'
        3. Run CREATE DATABASE IF NOT EXISTS `db_gallery`;
        4. Run GRANT ALL PRIVILEGES ON db_gallery.* TO 'galleryUser' WITH GRANT OPTION;
        5. Run the gallery.sql script to create the necessary tables. Please note, this must be done after running and deploying the application
     
    Run and deploy the spring boot application
        1. From the Build Project tool from the top right hand, select SslApplication.
        2. Click on the green play button to run the application
        3. After successful running, go to localhost:8080 to view the web pages.
        
    Application flow
        1. We have the following main pages: registration, admin, gallery, login, and errors.
        2. User must register to be able to upload images, therefore, please go to localhost:8080 and use the registration form
        3. After registration, the login page will be loaded. Use the login form to login into the admin page
        4. On the admin page, you have the ability to upload images to the system.
        5. The admin page is only visible to the login user and user will only see their own images uploaded into the gallery
        6. The gallery page is visible to everyone.
        
    Requirement and issues
        1. The task meet the basic requirement plus custom error page
        2. The integration testing has not been possible due to some dependency issues that I am still trying to figure out.
        3. I have used CSS HTML bootstrap for the UI but still face challenges with UI
        4. Image byte array has been saved into db because of portability
        
    

