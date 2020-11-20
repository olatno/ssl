# ssl
Gallery image uploading with spring boot

Build spec.
    1. Spring boot framework.
    2. Java 1.8
    3. Intellij ide
    4. MySql database
    5. Inbuilt tomcat web server
    
How to deploy and run the application.

    Configure mysql database
        1. The galley.sql at the root of the project contains the necessary script to ceate database and its tables
        2. Create databsse user for 'galleryUser' with password 'gallery@11'
        3. Run CREATE DATABASE IF NOT EXISTS `db_gallery`;
        4. Run GRANT ALL PRIVILEGES ON db_gallery.* TO 'galleryUser' WITH GRANT OPTION;
        5. Run the gallery.sql script to create the neccessary tables. Plase note, this must be done after running and deploying the application
     
    Run and deploy the spring boot application
        1. From the Build Project tool from the top right hand, select SslApplication.
        2. Click on the green play button to run the application
        3. After successful running, go to localhost:8080 to view the web pages.
        
    Application flow
        1. We have following pages : registration, admin, gallery and login.
        2. User must register to be able to upload images, therefore, please go to localhost:8080 and use the registration form
        3. After registration, the login page will be loaded. Use the login form to login into the admin page
        4. In the admin page, you have the ability to upload images to the system.
        5. The admin page is only visibile to the login user and user will only see their gallery
        6. The gallery page is visible to everyone.
        
    

