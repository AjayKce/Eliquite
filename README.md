# ELIQUITE
Hello, Geekeee!!! I am Ajay V from Karpagam College of Engineering. I am very font of UI/UX design and I made this app for the better sake of Education.
Eliquite is a ERP Application which is a global authentication Application which provides the many modules such as attendance, feedback etc to satisfy
the college ERP modules.
<hr/>
<h3>Modules Covered</h3>
<ul>
<li>Subject Creation</li>
<li>Creating Staff Module</li>
<li>Classrooms Creation</li>
<li>Allocating Classrooms for particular Staff ( Tutor )</li>
<li>Allocating Subject Handled by staff</li>
<li>Providing staff to create students for particular class</li>
<li>Adding Resource for the handled subject by the staff</li>
<li>Students Enrollment</li>
<li>Downloading Resources uploaded By the staff</li>
<li>Attendance module to give attendance for each hour</li>
<li>Setting feedback by college Admin</li>
<li>Giving feedback for each staff</li>
</ul>
<hr />
<hr />
<h3>Creating Superuser</h3>
	This is the first level of the Application in which the full functionality is handled by an organisation. Here the organisation refers to me.
The superusers are the users who develops and update the Application whenever a new modules is introduced.For the first time the Application will prompt
,username and password to authenticate the Application.
<img src="screenshot/1.PNG" />
<hr />
<h3>Starting Page</h3>
	<br />
	This is the starting page of my application which provides the login UI for superusers,college etc...
<img src="screenshot/2.PNG" />
<hr />
<h3>Requesting Application Permission</h3>
	The college has to request the superuser to use this application for Education purpose
<img src="screenshot/3.PNG" />
<img src="screenshot/4.PNG" />
<hr />
After Submitting the Requesition the superuser has to verify the college and create a login credentials for the college and the username and the password 
has to be send to the respective college mail<br/>
<img src="screenshot/5.PNG" />
<b>Login as superuser</b><br/>
<img src="screenshot/6.PNG" />
<hr/>
<h3>Superuser Homepage</h3>
	This superuser homepage is the panel for superuser to maintain the product such as confirming or rejecting the college requestion, Extending superuser
and accessing all the data through console [we will discuss the console later]<br>
<img src="screenshot/7.PNG" />
<img src="screenshot/8.PNG" />
<img src="screenshot/9.PNG" />
<img src="screenshot/10.PNG" />
<img src="screenshot/11.PNG" />
<img src="screenshot/12.PNG" />
<img src="screenshot/13.PNG" />
<hr/>
<h3>College Homepage</h3>
	College Module provide many functionality such as adding Admin ( here Admin refers to HOD ), Managing Admin, Creating Feedback , Scheduling periods etc..
In our Module the College create an authentication for each department, Each department should has only one admin(HOD)
<img src="screenshot/14.PNG" />
<img src="screenshot/15.PNG" />
<img src="screenshot/16.PNG" />
<img src="screenshot/17.PNG" />
<img src="screenshot/18.PNG" />
<img src="screenshot/19.PNG" />
<img src="screenshot/20.PNG" />
<img src="screenshot/21.PNG" />
<img src="screenshot/22.PNG" />
<img src="screenshot/23.PNG" />
We will discuss the Feedback and Attendance Module Later<br>
As we create the Admin(HOD) , Its time to login with Admin and acheive some stuff....Lets do it geekies....
<hr />
<h3>College Homepage</h3>
<img src="screenshot/24.PNG" />
<img src="screenshot/25.PNG" />
	The Admin(HOD) module is a powerful module which provides many functionality such as creating batch, classroom, staffs , Allocating etc...<br>
Here we will discuss various functionality and acheive the hard stuffs . Please follow each steps so that you can acheive a lot for your maintainance...<br/>
<br><br>
<u><b>Create Batch</b></u><br>
	Consider a scenario that we have to store student details, Note that each student has a roll number, each student belongs to respective department, and Each 
students belongs to a batch . So batch creation is very important...<br><br>
<img src="screenshot/26.PNG" />
<img src="screenshot/27.PNG" />
<br><br>
<u><b>Create Class Rooms</b></u><br>
	Many Students belongs to single department, and a single department has many batches , Each batch students are belongs to different sections. Here we will
define what are the classrooms available and we will get into it..
<img src="screenshot/28.PNG" />
<img src="screenshot/29.PNG" />
<br><br>
<u><b>Create Staff</b></u><br>
	Now we have to create staff and provide login credentials. These staffs can handle subjects for many classrooms, these staffs may be a tutor for classrooms.
The staffs who are the tutor for respective classrooms can only have permission to create students for respective class.<br>
<img src="screenshot/30.PNG" />
<img src="screenshot/31.PNG" />
<img src="screenshot/32.PNG" />
<img src="screenshot/33.PNG" />
<img src="screenshot/34.PNG" />
<br><br>
<u><b>Create Subject</b></u><br>
	Creating subject by giving the course code and subject title, is an important process.The Admin(HOD) who create the subject will only belong to the respective 
department i.e the subject which is created by CSE HOD will only available to CSE department. Each subject will carry course code, credits and belong to seperate
group.<br/>
<img src="screenshot/35.PNG" />
<img src="screenshot/36.PNG" />
<img src="screenshot/37.PNG" />
<br>
	Each subject has some sub-sections such as assesments,practical and so on... Now we have to create sub sections for each subject..<br>
We have to provide the fullmark and passmark for each sub-section...<br>
<img src="screenshot/38.PNG" />
<img src="screenshot/39.PNG" />
<img src="screenshot/40.PNG" />
<br><br>
<u><b>Allocate Tutor</b></u><br>
	Now it's time to allocate tutor for each classrooms. We have to handle it in a right manner for allocating staffs for each classrooms i.e same staff should
not be a tutor for both A and B section for same batch in same department.<br>
Here we have created three staffs <b>kokila</b>, <b>Rock</b> and <b>Jancy</b> .. But I have allocated Tutor as two staffs Kokila and Jancy. Note that I have not allocated Rock as Tutor.
As Rock is not the Tutor he can only teach subject but not able to create student module.<br>
<img src="screenshot/41.PNG" />
<img src="screenshot/42.PNG" />
<br><br>
<u><b>Allocate Subject</b></u><br>
	Now we came to important functionality called subject allocation. The students can study the same subject but the classrooms are different , so different staffs
has to take the same subject for the student. So subject Allocation is very important.<br>
We know that we have created three staffs <b>kokila</b>, <b>Rock</b> and <b>Jancy</b> in which Rock is a non-tutor.Now here I allocate subjects for Rock and Kokila
but not Jancy.
<img src="screenshot/43.PNG" />
<img src="screenshot/44.PNG" />
We will discuss the Department Feedback Module Later..<br>
Now We have created Three staff Modules , Lets make our UI dirty and we will see what is behind staff module..
<hr/>
<br>
<b>Note : </b>Now we will see the staff Module one by one and compare the difference.<br>
<b>Rock</b> :only Subject handler<br>
<b>Jancy</b> :only Tutor<br>
<b>Kokila</b> : Both Tutor and Subject handler<br>
<br><br>
<u><b>Staff Module (Rock)</b></u><br>
	Here we will login with rock staff module and see what are available.<br><br>
<img src="screenshot/45.PNG" />
<img src="screenshot/46.PNG" />
<img src="screenshot/47.PNG" />
<b><i>Here , Rock is a non-tutor ,so no class rooms are Allocated </i></b><br><br>
<img src="screenshot/48.PNG" />
<b><i>As Rock is allocated for two subjects it will be listed.</i></b><br>
The Three buttons manage, Add Resource and Set Marks will be discussed after student Enrollment<br><br>
<u><b>Staff Module (Jancy)</b></u><br>
<img src="screenshot/49.PNG" />
<img src="screenshot/50.PNG" />
<img src="screenshot/51.PNG" />
<img src="screenshot/52.PNG" />
<br><br>
<u><b>Staff Module (Kokila)</b></u><br>
<img src="screenshot/53.PNG" />
<img src="screenshot/54.PNG" />
<img src="screenshot/55.PNG" />
	Now you get a clear idea of subject Allocation and Tutor Allocation. Here upon seeing above we know that kokila is a tutor of cse A of 2018 batch.<br><br>
<img src="screenshot/56.PNG" /><br>
	Now as a tutor, the primary job is to maintain student details ....Lets go create some<br><br>
<img src="screenshot/57.PNG" /><br>
	Adding students include more than 40 fields but not all fields are mandatory.<br><br>
<img src="screenshot/58.PNG" />
<img src="screenshot/59.PNG" />
<img src="screenshot/60.PNG" />
<img src="screenshot/61.PNG" />
<img src="screenshot/62.PNG" />
<img src="screenshot/63.PNG" />
<br>
<b>Note: </b>A single class room has more than 60 students . So creating one by one is a tedious process. To overcome this file upload comes into play.<br>
Here for file uploading We will have sample document and the sample document has only header . We have to fill multiple details and upload it..<br/><br/>
<img src="screenshot/64.PNG" />
<img src="screenshot/65.PNG" />
<img src="screenshot/66.PNG" />
<img src="screenshot/67.PNG" />
<img src="screenshot/68.PNG" />
<img src="screenshot/69.PNG" />
<img src="screenshot/70.PNG" />
<img src="screenshot/71.PNG" />
<img src="screenshot/72.PNG" />
<img src="screenshot/73.PNG" />
<img src="screenshot/74.PNG" />
<img src="screenshot/75.PNG" />
<img src="screenshot/76.PNG" />
<img src="screenshot/77.PNG" />
<img src="screenshot/78.PNG" />
<img src="screenshot/79.PNG" /><br>
Here the student has not enrolled any subject. So We can check it out as a student module..<br>
<b>Note :</b>Inorder to acheive enrollment tutor has to enable the enrollment otherwise it will not happen..
<br><br>
<u><b>Filtering Students and Export</b></u><br>
	Filtering of student is a powerful module in which we can filter the students based on field given.<br/>
<img src="screenshot/80.PNG" />
<img src="screenshot/81.PNG" />
<img src="screenshot/82.PNG" /><br>
	Above will Export all students details<br><br>
<img src="screenshot/83.PNG" />
<img src="screenshot/84.PNG" />
<img src="screenshot/85.PNG" />
<img src="screenshot/86.PNG" />
<img src="screenshot/87.PNG" />
<img src="screenshot/88.PNG" />
<img src="screenshot/89.PNG" />
<img src="screenshot/90.PNG" /><br><br>
<u><b>Add Resource</b></u><br>
	Every staff may handle two or more subjects. Consider a scenario that the staff want to share some pdf for exam preparation for every students.here the staff 
can add resource for the particular handled subject. This resource can be downloaded by the students who enrolled this subject.<br><br>
<img src="screenshot/91.PNG" />
<img src="screenshot/92.PNG" />
<img src="screenshot/93.PNG" />
<img src="screenshot/94.PNG" />
<img src="screenshot/95.PNG" />
<hr>
<h3>Student Homepage</h3>
<br>
	Now let we explore the student module and make the functionality available..<br><br>
<img src="screenshot/96.PNG" />
<img src="screenshot/97.PNG" /><br><br>
	As a student the enrollment module is very important. The Enrollment decides the student to explore the resources provided by the staff and so on...<br><br>
<img src="screenshot/98.PNG" />
<img src="screenshot/99.PNG" /><br>
<b>Note : </b> There are three subject but as a student AJAY , enrolled only two subjects...<br>
We will discuss about feedback session later.
<br><br>
<u><b>Explore Resource</b></u><br>	
	As AJAY has enrolled two subject he has the permission to access the resource uploaded by the staff for enrolled subjects.<br><br>
<img src="screenshot/100.PNG" />
<img src="screenshot/101.PNG" />
<img src="screenshot/102.PNG" />
<img src="screenshot/103.PNG" />
<img src="screenshot/104.PNG" />
<img src="screenshot/105.PNG" />
<img src="screenshot/106.PNG" /><br><br>
<u><b>Group Resource</b></u><br>
	Consider a scenario that every students in one college can't able to share resources to the friends of other college. These webapp provides the functionality
of creating a group, adding resources to the group. Now anyone can access the resources with the group name without even login to the webapp.<br><br>
<img src="screenshot/107.PNG" />
<img src="screenshot/108.PNG" />
<img src="screenshot/109.PNG" />
<img src="screenshot/110.PNG" />
<img src="screenshot/111.PNG" />
<img src="screenshot/112.PNG" />
<img src="screenshot/113.PNG" />
<img src="screenshot/114.PNG" />
<br><br>
<b>Now the student has successfully enrolled, Now go back to staff module and see what happens</b><br>
<img src="screenshot/115.PNG" />
<img src="screenshot/116.PNG" />
<img src="screenshot/117.PNG" />
<img src="screenshot/118.PNG" />
<img src="screenshot/119.PNG" />
<img src="screenshot/120.PNG" />
<br/>
Here we note that only the students who enrolled this subject is listed down...<br><br>
<img src="screenshot/121.PNG" />
<img src="screenshot/122.PNG" />
<img src="screenshot/123.PNG" />
	From Above we have allocated marks for one student. But allocating one by one is a tedious process. To overcome we will go through some other stuff.
<br><br>
<img src="screenshot/124.PNG" />
<img src="screenshot/125.PNG" />
<img src="screenshot/126.PNG" />
<img src="screenshot/127.PNG" />
<img src="screenshot/128.PNG" />
<img src="screenshot/129.PNG" /><br>
	Now we go for tutor module to check every students mark for particular subject.<br><br>
<img src="screenshot/130.PNG" />
<img src="screenshot/131.PNG" />
<img src="screenshot/132.PNG" />
<img src="screenshot/133.PNG" />
<img src="screenshot/134.PNG" />
<img src="screenshot/135.PNG" /><br>
	Now we have a clear idea of student enrollment, setting marks and so on.. Now we dive into a new module called Feedback Module.<br>
The feedback is an important process to confirm that the staff is good in teaching or presentation. The Feedback define the staffs quality for withstand the
college for next semester or not.
<hr>
<h3>College Feedback Module</h3>
	In college feedback module , the college will create some feeds and allocate to the department. The feed allocated will only visible for students who are belongs
to the specific department.<br><br>
<img src="screenshot/136.PNG" />
<img src="screenshot/137.PNG" />
<img src="screenshot/138.PNG" />
<br>Note that after creating feedback group the button becomes in-active. This denotes that the feedback session has not started for respective department.
We have to enable this button when feedback session gets started.<br><br>
<img src="screenshot/139.PNG" />
<img src="screenshot/140.PNG" />
<img src="screenshot/141.PNG" />
<img src="screenshot/142.PNG" />
<img src="screenshot/143.PNG" />
<img src="screenshot/144.PNG" />
<img src="screenshot/145.PNG" /><br>
Now the feedset is successfully Allocated , Now we have to enable the active button.
<img src="screenshot/146.PNG" />
<img src="screenshot/147.PNG" /><br>
Now the feedback is successfully allocated and its time to logon to student module and give feedback for respective staffs who handle the enrolled subject.<br>
<b>Note :</b>We have three staff but AJAY has only enrolled 2 subjects, So AJAY has permission to give feedback to 2 staffs only.<br><br>
<img src="screenshot/148.PNG" />
<img src="screenshot/149.PNG" />
<img src="screenshot/150.PNG" />
<img src="screenshot/151.PNG" />
<img src="screenshot/152.PNG" />
<img src="screenshot/153.PNG" /><br>
Now AJAY as a Student has given feedback to 2 staffs , Similarily other students also give feedback to respective staffs who handle the enrolled subjects.<br>
Now lets dive into college module and get the report of the individual staff.<br><br>
<img src="screenshot/154.PNG" />
<img src="screenshot/155.PNG" />
<img src="screenshot/156.PNG" />
<img src="screenshot/157.PNG" />
<img src="screenshot/158.PNG" />
<img src="screenshot/159.PNG" />
<img src="screenshot/160.PNG" />
<img src="screenshot/161.PNG" />
<img src="screenshot/162.PNG" />
<hr/>
	Consider a scenario that department is conducting a wipro training progamme. Now the department needs a feedback for particular programme.Inorder to acheive that
the department has to add some feeds and process the report from it...<br><br>
<img src="screenshot/163.PNG" />
<img src="screenshot/164.PNG" />
<img src="screenshot/165.PNG" />
<img src="screenshot/166.PNG" /><br>
Now the feed is allocated , and its time to go back to student module and give feedback.<br>
<br>
<img src="screenshot/167.PNG" />
<img src="screenshot/168.PNG" />
<img src="screenshot/169.PNG" />
<img src="screenshot/170.PNG" /><br>
Now student has successfully submited feedback, Now its time to collect the report..<br><br>
<img src="screenshot/171.PNG" />
<img src="screenshot/172.PNG" />
<img src="screenshot/173.PNG" />
<img src="screenshot/174.PNG" />
<img src="screenshot/175.PNG" />
<img src="screenshot/176.PNG" />
<img src="screenshot/177.PNG" />
<hr/>
<h3>Attendance Module</h3>
	Now we came into an important module in which the college schedule the periods, the staff will provide the attendance for each students and the report is generated for
each subjects.Here through attendance module we can know the student percentage for each subject.The college has the functionality of updating the attendance on account of
<b>ON DUTY</b> Hours.....<br><br>
<img src="screenshot/178.PNG" />
<img src="screenshot/179.PNG" />
<img src="screenshot/180.PNG" />
<img src="screenshot/181.PNG" />
<img src="screenshot/182.PNG" />
<img src="screenshot/183.PNG" />
<img src="screenshot/184.PNG" />
<img src="screenshot/185.PNG" />
<img src="screenshot/186.PNG" />
<img src="screenshot/187.PNG" />
<img src="screenshot/188.PNG" />
<img src="screenshot/189.PNG" />
<img src="screenshot/190.PNG" />
<img src="screenshot/191.PNG" />
<img src="screenshot/192.PNG" />
<img src="screenshot/193.PNG" />
<img src="screenshot/194.PNG" />
<img src="screenshot/195.PNG" />
<img src="screenshot/196.PNG" />
<img src="screenshot/197.PNG" />
<img src="screenshot/198.PNG" />
<img src="screenshot/199.PNG" />
<img src="screenshot/200.PNG" />
<img src="screenshot/201.PNG" />
<img src="screenshot/202.PNG" />
<img src="screenshot/203.PNG" />
<img src="screenshot/204.PNG" />
<img src="screenshot/205.PNG" />
<img src="screenshot/206.PNG" />
<img src="screenshot/207.PNG" />
<img src="screenshot/208.PNG" />
<img src="screenshot/209.PNG" /><br><br>
<h3>Console Module</h3>
	The Application is fully maintained by superuser. So if there any fault in the application the superuser has to edit and update the changes..
<br><br>
<img src="screenshot/210.PNG" />
<img src="screenshot/211.PNG" />
<img src="screenshot/212.PNG" />
<img src="screenshot/213.PNG" />
<img src="screenshot/214.PNG" />
<img src="screenshot/215.PNG" />
<img src="screenshot/216.PNG" />
<img src="screenshot/217.PNG" />
<img src="screenshot/218.PNG" />
<img src="screenshot/219.PNG" />
<img src="screenshot/220.PNG" />
<img src="screenshot/221.PNG" />
<img src="screenshot/222.PNG" />
And similar for all.............
<center>Thankyou Guys!!!!!!!!!!!!!!!!!!!!!!!!!!</center>
<br>

	


