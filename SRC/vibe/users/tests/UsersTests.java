package vibe.users.tests;

import org.openqa.selenium.By.ByXPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.EnvProperties;
import vibe.billing.tests.BillingMethods;
import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.mywebsite.tests.MyWebSiteMethods;

@Priority(104)
public class UsersTests extends UsersMethods {
	 BusinessContactsMethods bc = new BusinessContactsMethods();
	 BillingMethods bm = new BillingMethods();
	 MyWebSiteMethods wm = new MyWebSiteMethods();
	 EnvProperties prop = new EnvProperties();
	 	 @Test(priority = 10405)
	 public void loggedInAsUser() throws  Exception{
		 String enrollUser = "automation.enroll2";
		 logInfo("inside loggedInAsUser() Test");
		 System.out.println("inside loggedInAsUser() Test");
		 boolean isVerified = false;
		 go2AdminUsers();
		 searchAdminUser(enrollUser);
		 if(verifyUserPresent(enrollUser)){
			 isVerified = verifyLoggedInUser(enrollUser);
			 if(isVerified == false){
			 	Assert.assertTrue(isVerified, "Unable to logged in as user from admin.");
			 } 
		 }
		
	 }
	 
	 @Test(priority = 10406)
	 public void verifyUserDetailsPage() throws  Exception{
		 logInfo("inside verifyUserDetailsPage() Test");
		 System.out.println("inside verifyUserDetailsPage() Test");
		 boolean isVerified = false;
		 go2Users();
		 searchUser(userName_text);
		 if(verifyUserPresent(userName_text)){
			 isVerified = verifyUserDetailsPage(userName_text);
			 if(isVerified == false){
				 Assert.assertTrue(isVerified, "Unable to verify the user details.");
			 } 
		 }
		
	 }
	 
	 @Test(priority = 10407)
	 public void searchUsersAdvancedSearch() throws  Exception{
	     
		 logInfo("inside searchUsersAdvancedSearch() Test");
		 System.out.println("inside searchUsersAdvancedSearch() Test");
		 boolean isVerified = false;
		 go2Users();
		 isVerified = verifyUserDetailsByAdvancedSearch(prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"),prop.getLocatorForEnvironment(appUrl,"userName"),prop.getLocatorForEnvironment(appUrl,"email"),prop.getLocatorForEnvironment(appUrl,"market"));
		 if(isVerified == false){
			 Assert.assertTrue(isVerified, "Unable to verify the user details using advanced search option.");
		 } 
		
	 }
	 
	 @Test(priority = 10408)
	 public void verifyValidationsForNewAdminUser() throws  Exception{
		 logInfo("inside verifyValidationsForNewAdminUser() Test");
		 System.out.println("inside verifyValidationsForNewAdminUser() Test");
		 boolean isVerified = false;
		 go2Users();
		 isVerified = verifyValidationsForAdminUser();
		 if(isVerified == false){
			 Assert.assertTrue(isVerified, "Unable to verify the validations while creating a new admin user.");
		 } 
		
	 }
	 
	 @Test(priority = 10409)
	 public void ResetPassword() throws  Exception{
		
		 logInfo("inside ResetPassword() Test");
		 System.out.println("inside ResetPassword() Test");
		 boolean isVerified = false;
		 go2AdminUsers();
		 searchAdminUser(userName_text);
		 boolean isUserFound = verifyAdminUserPresent(userName_text);
		 if(isUserFound){
			 editAdminUser(userName_text);
			 isVerified = verifyResetPassword(userName_text);
			 if(isVerified == false){
				 Assert.assertTrue(isVerified, "Unable to reset the password for the user.");
			 } 
		 }
	 }
	 
	 @Test(priority = 10410)
	 public void validateBlankResetPasswordFields() throws  Exception{
		 logInfo("inside validateBlankResetPasswordFields() Test");
		 System.out.println("inside validateBlankResetPasswordFields() Test");
		 boolean isVerified = false;
   		 logOut();
   		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		 go2PasswordSettings();
		 changePasswordSettings("None");
		 implicityWaits(104);
		 go2Users();
		 searchUser(userName_text);
		 boolean isUserFound = verifyUserPresent(userName_text);
		 if(isUserFound){
			 editUser(userName_text);
			 isVerified = validateBlankResetPasswordFields(userName_text);
			 if(isVerified == false){
				 Assert.assertTrue(isVerified, "Unable to validate the blank reset password fields, after re-setting password for the user.");
			 } 
		 }
	 }
	 
	 @Test(priority = 10411)
	 public void validateResetPasswordFields() throws  Exception{
				 
		 logInfo("inside validateResetPasswordFields() Test");
		 System.out.println("inside validateResetPasswordFields() Test");
		 boolean isVerified = false;
		 
		 logOut();
			logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		 go2PasswordSettings();
		 changePasswordSettings("Minimum length 7. Include at least one number.");
		 confirmAlert();
		 go2Users();
		 searchUser(userName_text);
		 boolean isUserFound = verifyUserPresent(userName_text);
		 if(isUserFound){
			 editUser(userName_text);
			 isVerified = validateResetPasswordFields(userName_text);
			 if(isVerified == false){
				 Assert.assertTrue(isVerified, "Unable to validate the reset password fields, after re-setting password for the user.");
			 } 
		 }
	 }
	 
	 @Test(priority = 10412)
	 public void DenyOldPasswords() throws  Exception{
			 
		 logInfo("inside DenyOldPasswords() Test");
		 System.out.println("inside DenyOldPasswords() Test");
		 boolean isVerified = false;

			logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		 go2PasswordSettings();
		 implicityWaits(3);
		 changeDenyPasswordSettings("Password Archiving Count","Deny Old Passwords");
		 confirmAlert();
		 go2Users();
		 searchUser(userName_text);
		 boolean isUserFound = verifyUserPresent(userName_text);
		 if(isUserFound){
			 editUser(userName_text);
			 isVerified = validateDenyOldPasswords(userName_text);
			 if(isVerified == false){
				 Assert.assertTrue(isVerified, "Unable to validate the deny old password fields, after re-setting password for the user.");
			 } 
		 }
	 }

	@Test(priority = 10413)
	public void changeSubscriptionPlanFromLite2Pro() throws  Exception{
		//take lite-monthly user as enrollUser
		String user = prop.getLocatorForEnvironment(appUrl,"enrollUser"); //"automation.enroll2";
		logInfo("inside changeSubscriptionPlanFromLite2Pro Test");
		System.out.println("inside changeSubscriptionPlanFromLite2Pro Test");
		bm.go2BillingSubscriptionAdmin(user);
		String drpSubscriptionPlanName = "//*[@id='pyr_core_subscription_subscription_plan_id']";
		String before_change = getCurrentSelectionFromDropdown("xpath",drpSubscriptionPlanName);
		System.out.println("before_change =" +before_change);
		bm.changeSubscriptionPlanInAdmin(txtSubPlanProMonthly);
		String after_change = getCurrentSelectionFromDropdown("xpath",drpSubscriptionPlanName);
		System.out.println("after_change =" +after_change);
		boolean isNotesMatchFound = bm.verifySubscriptionNotesPresent("Subscription plan changed from "+before_change+" to "+after_change);
		Assert.assertTrue(isNotesMatchFound, "Subscription plan changed from "+before_change+" to "+after_change + " match not found.");
	}
	
	@Test(priority = 10413)
	public void changeSubscriptionPlanFromLite2ProIDLV() throws  Exception{
		//take lite-monthly user as enrollUser
		String enrollUser = "cutler";
		String txtSubPlanProMonthly = "Pro Bundle";
		
		logInfo("inside changeSubscriptionPlanFromLite2ProIDLV Test");
		System.out.println("inside changeSubscriptionPlanFromLite2ProIDLV Test");
		bm.go2BillingSubscriptionAdmin(enrollUser);
		String drpSubscriptionPlanName = "//*[@id='pyr_core_subscription_subscription_plan_id']";
		String before_change = getCurrentSelectionFromDropdown("xpath",drpSubscriptionPlanName);
		System.out.println("before_change =" +before_change);
		bm.changeSubscriptionPlanInAdmin(txtSubPlanProMonthly);
		String after_change = getCurrentSelectionFromDropdown("xpath",drpSubscriptionPlanName);
		System.out.println("after_change =" +after_change);
		boolean isNotesMatchFound = bm.verifySubscriptionNotesPresent("Subscription plan changed from "+before_change+" to "+after_change);
		Assert.assertTrue(isNotesMatchFound, "Subscription plan changed from "+before_change+" to "+after_change + " match not found.");
	}
	 
	@Test(priority = 10414)
	public void changeSubscriptionPlanFromPro2Lite() throws  Exception{
				
		logInfo("inside changeSubscriptionPlanFromPro2Lite Test");
		System.out.println("inside changeSubscriptionPlanFromPro2Lite Test");
		bm.go2BillingSubscriptionAdmin(enrollUser);
		String drpSubscriptionPlanName = "//*[@id='pyr_core_subscription_subscription_plan_id']";
		String before_change = getCurrentSelectionFromDropdown("xpath",drpSubscriptionPlanName);
		System.out.println("before_change =" +before_change);
		bm.changeSubscriptionPlanInAdmin(txtSubPlanLiteMonthly);
		String after_change = getCurrentSelectionFromDropdown("xpath",drpSubscriptionPlanName);
		System.out.println("after_change =" +after_change);
		boolean isNotesMatchFound = bm.verifySubscriptionNotesPresent("Subscription plan changed from "+before_change+" to "+after_change);
		Assert.assertTrue(isNotesMatchFound, "Subscription plan changed from "+before_change+" to "+after_change + " match not found.");
	}
	 
	@Test(priority = 10415)
	public void AccountSectionFieldsAndWebsites() throws  Exception{
		logInfo("inside verifyAccountSectionFieldsAndWebsites Test");
		System.out.println("inside verifyAccountSectionFieldsAndWebsites Test");
		back2Office();
		logOut();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));

		String websiteTemplate_text = prop.getLocatorForEnvironment(appUrl,"websiteTemplate_text");
		wm.addMyWebsite(websiteTemplate_text,websiteName_text);
		if(wm.verifyWebsiteIsPresent(websiteName_text)){
			logOut();
			logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
			go2Users();
			searchUser(userName_text);
			boolean isUserFound = verifyUserPresent(userName_text);
			if(isUserFound){
				editUser(userName_text);
				boolean isFieldsVerified = verifyAccountSectionFieldsAndWebsites(websiteName_text);
				if(!isFieldsVerified){
					Assert.assertTrue(isFieldsVerified, "Unable to verify account section fields and websites.");
				}
			}
		}
		
	}
	 
	@Test(priority = 10416)
	
	public void updateUserDetails() throws  Exception{
				
		logInfo("inside updateUserDetails Test");
		System.out.println("inside updateUserDetails Test");
//		back2Office(); uncomment this during smoke
		logOut();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		boolean isWebsiteUpdated = updateWebSiteName(websiteName_text);
		if(isWebsiteUpdated){
			logOut();
			logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
			go2Users();
			searchUser(userName_text);
			boolean isUserFound = verifyUserPresent(userName_text);
			if(isUserFound){
				editUser(userName_text);
				boolean isVerified = verifyUpdateUserDetails(userName_text,websiteName_text);
				if(!isVerified){
					Assert.assertTrue(isVerified, "Unable to update the user details.");
				}
			}
		}
		else{
			isWebsiteUpdated = false;
			Assert.assertTrue(isWebsiteUpdated, "Unable to update the website.");
		}
		logOut();
		logIn(prop.getLocatorForEnvironment(appUrl,"adminUser_text"),prop.getLocatorForEnvironment(appUrl,"adminPwd_text"));
		
	}
	
	
	/*public void updateUserDetails() throws  Exception{
		logInfo("inside updateUserDetails Test");
		System.out.println("inside updateUserDetails Test");
		back2Office();
		logOut();
		logIn(userName_text,adminPwd_text);
		boolean isWebsiteUpdated = updateWebSiteName(websiteName_text);
		if(isWebsiteUpdated){
			logOut();
			logIn(adminUser_text,adminPwd_text);
			go2Users();
			searchUser(userName_text);
			boolean isUserFound = verifyUserPresent(userName_text);
			if(isUserFound){
				editUser(userName_text);
				boolean isVerified = verifyUpdateUserDetails(userName_text,websiteName_text);
				if(!isVerified){
					Assert.assertTrue(isVerified, "Unable to update the user details.");
				}
			}
		}
		else{
			isWebsiteUpdated = false;
			Assert.assertTrue(isWebsiteUpdated, "Unable to update the website.");
		}
		Thread.sleep(3000);
		logOut();
		logIn(adminUser_text,adminPwd_text);
		
	}*/
	 
	@Test(priority = 10417)
	public void validateEmail() throws  Exception{
		logInfo("inside validateEmail() Test");
		System.out.println("inside validateEmail() Test");
		waitOnElement("xpath",lnkBack2Admin);
		clickOnElement("xpath",lnkBack2Admin);
		go2Users();
		searchUser(userName_text);
		editUser(userName_text);
		boolean isValidated = verifyValidateEmail();
		if(!isValidated){
			Assert.assertTrue(isValidated, "Unable to validate the blank email when updating user.");
		}
	}
	
	@Test(priority = 10418)
	public void verifyUserStats() throws  Exception{
		logInfo("inside verifyUserStats() Test");
		System.out.println("inside verifyUserStats() Test");
		go2Users();
		searchUser(userName_text);
		editUser(userName_text);
		boolean isVerified = verifyUserStatistics();
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to validate the user statistics.");
		}
	}
	
	
	

	@Test(priority = 10419)
	 public void logInAsNewAdminUser() throws  Exception{
		 logInfo("inside logInAsNewAdminUser() Test");
		 System.out.println("inside logInAsNewAdminUser() Test");
		 boolean isVerified = false;
		 back2Office();
		 logOut();
		 logIn(userName_text,userPassword_text);
		 confirmationMessage("Signed in successfully.");
		 implicityWaits(3);
		 bc.go2ContactsPage();
		 String pageTitle = driver().findElement(ByXPath.xpath("//*[@id='home-link']")).getText().trim();
		 if(!pageTitle.contains("Contacts")){
			 Assert.assertTrue(false,"Unable to access the contacts module with this new admin user " + userName_text);
		 }
	}
	


	
	
}
