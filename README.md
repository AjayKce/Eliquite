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
<h4>Creating Superuser</h4>
	This is the first level of the Application in which the full functionality is handled by an organisation. Here the organisation refers to me.
The superusers are the users who develops and update the Application whenever a new modules is introduced.For the first time the Application will prompt
,username and password to authenticate the Application.
<img src="screenshot/1.PNG" />
<hr />
<h4>Starting Page</h4>
	<br />
	This is the starting page of my application which provides the login UI for superusers,college etc...
<img src="screenshot/2.PNG" />
<hr />
<h4>Requesting Application Permission</h4>
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
<h4>Superuser Homepage</h4>
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
<h4>College Homepage</h4>
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
<h4>College Homepage</h4>
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


