package vibe.tasks.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.contacts2.tests.BusinessContactsTests;
import vibe.widgets.tests.WidgetsMethods;
import common.Priority;

@Priority(106)
public class TasksTests extends TaskMethods {	
	
	TaskMethods tm = new TaskMethods();
	BusinessContactsTests bct = new BusinessContactsTests();	
	BusinessContactsMethods bcm = new BusinessContactsMethods();
	WidgetsMethods wm = new WidgetsMethods();
	
	

	@Test(priority=10602)
	public void dragAllTasksWidgets() throws Exception{
		logInfo("inside dragAllTasksWidgets() Test");

		navigate2BusinessTasks();	
		// remove all widgets in home page.
		  // wm.removeAllWidgetInHomepage();
		
		// drag and drop the widget on the home page.
		  dragAndDropWidgetFromTasksPage("Tasks Add","Left Panel");
		  dragAndDropWidgetFromTasksPage("Tasks Today","Left Panel");
		  dragAndDropWidgetFromTasksPage("Tasks No Due Date","Left Panel");
		  dragAndDropWidgetFromTasksPage("Tasks Future","Right Panel");
		  dragAndDropWidgetFromTasksPage("Tasks Completed","Right Panel");
		  dragAndDropWidgetFromTasksPage("Tasks Overdue","Right Panel");
		  dragAndDropWidgetFromTasksPage("Tasks Incomplete","Right Panel");
			
		  collapseEditWidget();
	}
	
	

	@Test(priority=10603)
	public void validateTasksWithNoTitle() throws Exception{
		logInfo("inside validateTasksWithNoTitle() Test");
		
		navigate2BusinessTasks();	
		addTask(todaysTask_text,"NoTaskTitle");
		confirmationMessage("Please enter a Task Title.");
	}
	
	
	
	@Test(priority=10604)
	public void searchTaskInWidget() throws Exception{
		logInfo("inside searchTaskInWidget() Test");
		
		navigate2BusinessTasks();	
		addTask(todaysTask_text,"TodaysTask");
		System.out.println(todaysTask_text);		
		boolean isTaskFoundInTodaysWidget = verifyTaskPresentinWidget("TodaysTaskWidget",todaysTask_text);
		System.out.println("isTaskFoundInTodaysWidget =" +isTaskFoundInTodaysWidget);
		if (isTaskFoundInTodaysWidget==true){
			logInfo(todaysTask_text + " task is present in Today's Task widget.");
			deleteTask("TodaysTaskWidget",todaysTask_text);
			logInfo("Deleting the task " + todaysTask_text);
		} else {
			logInfo(todaysTask_text + " task could not be found in Today's Task widget.");
			Assert.assertTrue(isTaskFoundInTodaysWidget, todaysTask_text + " task could not be found in Today's Task widget.");
			
		}
	}
	

	@Test(priority=10605)
	public void verifySortingByTitleInWidget() throws Exception{
		logInfo("inside verifySortingByTitleInWidget() Test");
		String zeroTask = "0001_Task";
			
		navigate2BusinessTasks();	
		addTask(todaysTask_text,"TodaysTask");
		navigate2BusinessTasks();	
		addTask(zeroTask,"TodaysTask");
				
		boolean isTaskFoundInTodaysWidget = verifyTaskPresentinWidget("TodaysTaskWidget",zeroTask);
		System.out.println("isTaskFoundInTodaysWidget =" +isTaskFoundInTodaysWidget);
		if (isTaskFoundInTodaysWidget==false){
			logInfo(zeroTask + " task not present in Today's Task widget.");
			Assert.assertTrue(isTaskFoundInTodaysWidget, zeroTask + " task could not be found in Today's Task widget.");
		} else {
			logInfo(zeroTask + " task present in Today's Task widget.");
			logInfo("clicking on the Task field in the widget");
			boolean isTaskInSorted = false;
			
			sortTaskByField("IncompleteTasksWidget","Task");
			boolean isTaskInFirstRow = verifyTaskPresentinFirstRowInWidget("IncompleteTasksWidget",zeroTask);
			if(isTaskInFirstRow==true){
				isTaskInSorted=true;
			}
			
			sortTaskByField("IncompleteTasksWidget","Task");
			boolean isTaskInLasttRow = verifyTaskPresentinFirstRowInWidget("IncompleteTasksWidget",zeroTask);
			if(isTaskInLasttRow==true){
				isTaskInSorted=true;
			}
			
			if(isTaskInSorted==true){
				logInfo(zeroTask + " task found in the first row in the widget.");
			} else {
				logInfo(zeroTask + " task not found in the first row in the widget.");
				Assert.assertTrue(isTaskInSorted, zeroTask + " task not found in the first row in the widget.");
			}
		}
		
		 deleteTask("TodaysTaskWidget",todaysTask_text);
		 deleteTask("TodaysTaskWidget",zeroTask);
	}
	
	
	@Test(priority=10606)
	public void addTodaysTasks() throws Exception{
		logInfo("inside addTodaysTasks() Test");
		
		navigate2BusinessTasks();	
		addTask(todaysTask_text,"TodaysTask");
				
		boolean isTaskFoundInTodaysWidget = verifyTaskPresentinWidget("TodaysTaskWidget",todaysTask_text);
		System.out.println("isTaskFoundInTodaysWidget =" +isTaskFoundInTodaysWidget);
		if (isTaskFoundInTodaysWidget==false){
			logInfo(todaysTask_text + " task should present in Today's Task widget.");
			Assert.assertTrue(isTaskFoundInTodaysWidget, todaysTask_text + " task is not present in Today's Task widget.");
		} 
		
		boolean isWidgetWithNoData = verifyWidgetWhenNoData("TodaysTaskWidget");
		if(isWidgetWithNoData==false){
			boolean isTaskFoundIncompleteWidget = verifyTaskPresentinWidget("IncompleteTasksWidget",todaysTask_text);
			System.out.println("isTaskFoundIncompleteWidget =" +isTaskFoundIncompleteWidget);
			if (isTaskFoundIncompleteWidget==false){
				logInfo(todaysTask_text + " task should present in incomplete widget.");
				Assert.assertTrue(isTaskFoundIncompleteWidget, todaysTask_text + " task is not present in Incomplete Task widget.");
			} 
		}	
		 deleteTask("TodaysTaskWidget",todaysTask_text);
	}


	
	@Test(priority=10607)
	public void addFutureTasks() throws Exception{
		logInfo("inside addFutureTasks() Test");
	
		navigate2BusinessTasks();	
		addTask(futureTask_text,"FutureTask");
		confirmationMessage("Your task has been created.");
		
		boolean isTaskFoundIncompleteWidget = verifyTaskPresentinWidget("IncompleteTasksWidget",futureTask_text);
		if (isTaskFoundIncompleteWidget==false){
			logInfo(futureTask_text + " task should present in Incomplete Task widget.");
			Assert.assertTrue(isTaskFoundIncompleteWidget, futureTask_text + " task should present in Incomplete Task widget.");
		} 
		
		boolean isTaskFoundInTodaysWidget = verifyTaskPresentinWidget("TodaysTaskWidget",futureTask_text);
		if (isTaskFoundInTodaysWidget==true){
			logInfo(futureTask_text + " task should not present in Today's Task widget.");
			Assert.assertFalse(isTaskFoundInTodaysWidget, futureTask_text + " should not present in Today's Task widget.");
		} 
		
		deleteTask("FutureTaskWidget",futureTask_text);
	}
	
	
	@Test(priority=10608)
	public void addNoDueDateTasks() throws Exception{
		logInfo("inside addNoDueDateTasks() Test");
		
		navigate2BusinessTasks();	
		addTask(noDueDateTask_text,"NoDueDateTask");
		confirmationMessage("Your task has been created.");
		
		boolean isTaskFoundInNoDueDateWidget = verifyTaskPresentinWidget("NoDueDateWidget",noDueDateTask_text);
		if (isTaskFoundInNoDueDateWidget==false){
			logInfo(noDueDateTask_text + " should present in NoDueDate Task widget.");
			Assert.assertTrue(isTaskFoundInNoDueDateWidget, noDueDateTask_text + " should present in NoDueDate Task widget.");
		} 
		
		boolean isTaskFoundInTodaysWidget = verifyTaskPresentinWidget("TodaysTaskWidget",noDueDateTask_text);
		if (isTaskFoundInTodaysWidget==true){
			logInfo(noDueDateTask_text + " should not present in Today's Task widget.");
			Assert.assertFalse(isTaskFoundInTodaysWidget, noDueDateTask_text + " should not present in Today's Task widget.");
		} 
		
		boolean isTaskFoundIncompleteWidget = verifyTaskPresentinWidget("IncompleteTasksWidget",noDueDateTask_text);
		if (isTaskFoundIncompleteWidget==false){
			logInfo(noDueDateTask_text + " task should present in Incomplete Task widget.");
			Assert.assertTrue(isTaskFoundIncompleteWidget, noDueDateTask_text + " task should present in Incomplete Task widget.");
		} 
		
		// deleteTask("NoDueDateWidget", noDueDateTask_text);
	}
	
	
	@Test(priority=10609)
	public void markTaskAsComplete() throws Exception{
		logInfo("inside markTaskAsComplete() Test");
		
		navigate2BusinessTasks();	
		addTask(todaysTask_text,"TodaysTask");
		confirmationMessage("Your task has been created.");
		
		boolean isTaskFoundInTodaysWidget1 = verifyTaskPresentinWidget("TodaysTaskWidget",todaysTask_text);
		if (isTaskFoundInTodaysWidget1==false){
			logInfo(todaysTask_text + " task should not present in Today's Task widget.");
			Assert.assertTrue(isTaskFoundInTodaysWidget1, todaysTask_text + " task should not present in Today's Task widget.");
		} 
		
		markTaskAsComplete("TodaysTaskWidget", todaysTask_text);
	 
		boolean isTaskFoundCompletedWidget = verifyTaskPresentinWidget("CompletedTasksWidget",todaysTask_text);
		System.out.println("isTaskFoundCompletedWidget =" + isTaskFoundCompletedWidget);
		if (isTaskFoundCompletedWidget==false){
			logInfo(todaysTask_text + " task should present in Completed Task widget");
			Assert.assertTrue(isTaskFoundCompletedWidget, todaysTask_text + " task should present in Completed Task widget");
		} 
		
		boolean isWidgetWithNoData = verifyWidgetWhenNoData("IncompleteTasksWidget");
		if(isWidgetWithNoData==false){
			boolean isTaskFoundIncompleteWidget = verifyTaskPresentinWidget("IncompleteTasksWidget",todaysTask_text);
			System.out.println("isTaskFoundIncompleteWidget =" + isTaskFoundIncompleteWidget);
			if (isTaskFoundIncompleteWidget==true){
				logInfo(todaysTask_text + " task should not present in Incomplete Task widget");
				Assert.assertFalse(isTaskFoundIncompleteWidget, todaysTask_text + " task should not present in Incomplete Task widget");
			}
		}
		
		deleteTask("CompletedTasksWidget", todaysTask_text);
	}
	
	
	@Test(priority=10610)  
	public void markTaskAsIncomplete() throws Exception{
		logInfo("inside markTaskAsIncomplete() Test");
		
		navigate2BusinessTasks();
		addTask(todaysTask_text,"TodaysTask");
		confirmationMessage("Your task has been created.");
		
		boolean isTaskFoundInTodaysWidget1 = verifyTaskPresentinWidget("TodaysTaskWidget",todaysTask_text);
		if (isTaskFoundInTodaysWidget1==true){
			markTaskAsComplete("TodaysTaskWidget", todaysTask_text);
		} 
		
			
		boolean isTaskFoundCompletedWidget = verifyTaskPresentinWidget("CompletedTasksWidget",todaysTask_text);
		if (isTaskFoundCompletedWidget==true){
			markTaskAsIncomplete("CompletedTasksWidget", todaysTask_text);
		}
		
		
		// Verify in Incomplete Task Widget
		
		boolean isTaskFoundIncompleteWidget = verifyTaskPresentinWidget("IncompleteTasksWidget",todaysTask_text);
		if (isTaskFoundIncompleteWidget==false){
			logInfo(todaysTask_text + " task should be present in InComplete Task widget");
			Assert.assertTrue(isTaskFoundIncompleteWidget, todaysTask_text + " task should be present in Incomplete Task widget");
		} 
		
			
		// Verify in Completed Task Widget
		

		/*boolean isTaskFoundCompletedWidget1 = verifyTaskPresentinWidget("CompletedTasksWidget",todaysTask_text);
		if (isTaskFoundCompletedWidget1==true){
			logInfo(todaysTask_text + " task should present in Completed Task widget");
			Assert.assertFalse(isTaskFoundCompletedWidget1, todaysTask_text + " task not be presented in Completed Task widget");
		} */

		deleteTask("IncompletedTasksWidget", todaysTask_text); 	
		
	}
	
	
	@Test(priority=10611)
	public void addTaskFromContacts() throws Exception{
		logInfo("inside addTaskFromContacts() Test");
		bcm.go2ContactsPage();
		boolean isContactFound = bcm.verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==false){
			bcm.addBusinessContacts(contactFirstName_text,contactLastName_text,contactEmail_text);
			logInfo("creating the business contact");
		} 
		
		bct.createTask4BusinessContact();
		logInfo("creating task from the business contact");
		Thread.sleep(5000);
		navigate2BusinessTasks();
		
		// Verify in Incomplete Task Widget
		
		boolean isTaskFoundIncompleteWidget = verifyTaskPresentinWidget("IncompleteTasksWidget",contactTaskDesc_text);
		if (isTaskFoundIncompleteWidget==false){
			logInfo(contactTaskDesc_text + " task should be present in InComplete Task widget");
			Assert.assertTrue(isTaskFoundIncompleteWidget, contactTaskDesc_text + " task should be present in Incomplete Task widget");
			} 
		
		 deleteTask("IncompletedTasksWidget", contactTaskDesc_text); 	
		}
	
	

	
	
}
