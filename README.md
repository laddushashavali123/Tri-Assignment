# Assignment
Task I – Test Automation

We can run the tests in 2 ways : 

1. Running from RunCukesTest   
 a. Right click on the runner class and select Run
2. Running from build : 
  a. run the below command in prompt 
      mvn clean test 
           or 
      mvn clean verify     
       
Please cover the following scenarios:
1. Search for any location on magazine.trivago.com by using the search bar
2. Fill in the contact form and send it (accessible through the footer)
3. Subscribe to the Newsletter
4. Navigate to a destination through the menu on the top left
5. Create 2 different automated test cases for the actions you think are the most important to automate
  a. Navigate to trivago portal from Destination search result item and verify the results
  b. Navigate to trivago portal from trivago link in the Home page,Search location wise  and verify the results
Please note the following information:
• Feel free to use any automation framework
• Make sure to generate and attach results for every test
• Please push your project to a Git repository and share it with us
• Explain in detail what made you choose these 2 test cases from point 5 above
• Please list the limitations for your tests :  
  a. Parallel Execution can be achieved using temyers and surefire plugin 
  b. Data Reading (Usage of DB or .xls or .xlsx )or manipulation  need changes in the implementation and is achievable.
  c. All the steps are written in the same steps class with actual logic in PageObjects
  d. Able to add the link of screenshot but not able to see the image preview in the report
  e. TestNG usage can be achieved using TestNG Listener
  f. Hooks might not work here since driver instance is one for each run.
