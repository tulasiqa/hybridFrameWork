package vibe.chat.tests;


import org.testng.annotations.Test;


import common.Priority;
import common.EnvProperties;
import vibe.calendar2.tests.CalendarTests;
import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.contacts2.tests.BusinessContactsTests;
import vibe.inbox.tests.InboxMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.people.tests.PeopleMethods;
import vibe.reports.tests.ReportsMethods;
import vibe.resourcelibrary3.tests.RL3Methods;
import vibe.tasks.tests.TaskMethods;
import vibe.training.tests.TrainingMethods;
import vibe.users.tests.UsersMethods;

@Priority(6)
public class MonatChatTests extends ChatMethods{
	CalendarTests cm = new CalendarTests();
	BusinessContactsMethods bc = new BusinessContactsMethods();
	InboxMethods inbox = new InboxMethods();
	CommunityMethods comm = new CommunityMethods();
	MyProfileMethods profile = new MyProfileMethods();
	PeopleMethods ppl = new PeopleMethods();
	ReportsMethods reports = new ReportsMethods();
	RL3Methods rl2 = new RL3Methods();
	TaskMethods tm = new TaskMethods();
	TrainingMethods train = new TrainingMethods(); 
	NewsMethods nm = new NewsMethods();
	BusinessContactsTests cont = new BusinessContactsTests();
	UsersMethods um = new UsersMethods();
	ChatMethods chat = new ChatMethods();
	EnvProperties prop = new EnvProperties();
	
	
	public static String vibeTochatUser=  "";/*vibeUser(adminUser_text); */
	
		
	@Test(priority=601)
	public void verifyQuickChat() throws Exception{	
		
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				                prop.getLocatorForEnvironment(appUrl,"newsLN1"),
                                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
 
       try{
		back2Office();
		validateQuickChat();				
		cm.go2CalendarPage();
		validateQuickChat();
		bc.go2ContactsPage();
		validateQuickChat();
		inbox.go2Inbox();
		validateQuickChat();			
		reports.navigate2Report();
		validateQuickChat();
		rl2.navigate2UserRL();
		validateQuickChat();
		tm.navigate2BusinessTasks();
		validateQuickChat();	
		userLogout(); 
		
     }catch (Exception e){
    	 userLogout(); 
     		}
		
	}
	
	
	
		
	@Test(priority=602)		
	  public void chatwithMonatUsers() throws Exception{            
            back2Office();
            //nm.loginAsUser(prop.getLocatorForEnvironment(appUrl,"loginUser"));            
            expandNdSelectTypeOfChat("Recent Chats");
            selectAUserFromQuickchat(prop.getLocatorForEnvironment(appUrl,"newsFN1"));    
            chat.sendMsgFomChatBox(chatMessageText1);
            nm.userLogout();  
        	loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
	                                prop.getLocatorForEnvironment(appUrl,"newsLN1"),
                                    prop.getLocatorForEnvironment(appUrl,"newsCon1"));
                     
            back2Office();
            expandNdSelectTypeOfChat("Recent Chats");    
            selectUserFromRecentChats("Marie");
            validateMsgInChat(chatMessageText1);
            chat.sendMsgFomChatBox(chatMessageText2);
            nm.userLogout(); 
            //nm.loginAsUser(prop.getLocatorForEnvironment(appUrl,"loginUser"));
            expandNdSelectTypeOfChat("Recent Chats");    
            selectUserFromRecentChats(prop.getLocatorForEnvironment(appUrl,"newsFN1"));
            validateMsgInChat(chatMessageText2);
            nm.userLogout();
		}
		
		

}
