# upgrade-test-challenge 
upgrade coding challenge
The project is based on the existing framework with a rich amount of tests,
so a lot of code that is not used and artifacts from other modules(that are deleted) are present,
but it describes the testing approach and techniques.
There are no specific requirements for installation. 
The only one thing that can happen is that some SDK may create conflicts (happened with few sdk versions). 1.8.0_241 was used, 
but should also work with others. Maven will do the rest.
Please refer to the screen capture for the SDK configuration
https://www.screencast.com/t/HGV9O3sNibt
3 test are implemented:
1 for the UI flow with selenium:   verifyNewUserLoanOffer() located in upgrade-test\ui\src\test\java\smoke
2 for the api flow with REST Assured: upgradeUserLogin() AND upgradeUserLoginUnAuthorized() 
located in upgrade-test\api\src\test\java\tests\auth
refer to screenshot: https://www.screencast.com/t/aSSjLzYuBo
