	
package ai.dltk.tests;

import ai.dltk.pages.*;
import ai.generic.BaseTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CommunityFriendrequestsTest extends BaseTest {


	 @Test
	    @Parameters({"username","password"})
	    public void CommunityFriendrequests(@Optional("Abc") String username, String password) {
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.goTo();
	        loginPage.login(username,password);
	        CommunityPage commPage = new CommunityPage(driver);
	        commPage.Friendrequests();
	    }
	}


								


			



