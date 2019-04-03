<%@ page import="java.util.*,student.kce.erp.model.*" %>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
</head>

<body>

<%List<GroupAttachment> groupAttachments = (List<GroupAttachment>)request.getAttribute("groupAttachments"); %>
<br><br>
<table id="example" class="table table-bordered">
        <thead>
            <tr>
                <th>Id</th>
                <th>College Id</th>
                <th>AdminId</th>
                <th>Batch</th>
                <th>Semester</th>
                <th>Department</th>
                <th>Section</th>
                <th>Student Id</th>
                <th>Group Id</th>
                <th>Group Code</th>
                <th>FileName</th>
                <th>FileData</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
        <%for(GroupAttachment temp:groupAttachments){ %>
            <tr>
                <td><%=temp.getId() %></td>
                <td><%=temp.getCollegeId() %></td>
                <td><%=temp.getAdminId() %></td>
                <td><%=temp.getBatch() %></td>
                <td><%=temp.getSemester() %></td>
                <td><%=temp.getDepartment() %></td>
                <td><%=temp.getSection() %></td>
                <td><%=temp.getStudentId() %></td>
                <td><%=temp.getGroupId() %></td>
                <td><%=temp.getGroupCode() %></td>
                <td><%=temp.getFileName() %></td>
                <td><a href="${pageContext.request.contextPath}/downloadStudentResourceFile?id=<%=temp.getId() %>"><button class="btn btn-primary"><span class="glyphicon glyphicon-download"></span> download</button></a></td>
            	<td>
					<form  action="/console/editGroupAttachment" method="post" style="margin:0px;padding:0px;border:0px;">
						<input type="hidden" name="id" value="<%=temp.getId() %>" />
						<button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-edit"></span> Edit</button>
					</form>
				</td>
				<td>
					<form onsubmit="return confirm('Do you really want to delete ?')"  action="/console/deleteGroupAttachment" method="post" style="margin:0px;padding:0px;border:0px;">
						<input type="hidden" name="id" value="<%=temp.getId() %>" />
						<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete</button>
					</form>
				</td>
            	</tr>
         <%} %>
        </tbody>
        <tfoot>
           <tr>
                <th>Id</th>
                <th>College Id</th>
                <th>AdminId</th>
                <th>Batch</th>
                <th>Semester</th>
                <th>Department</th>
                <th>Section</th>
                <th>Student Id</th>
                <th>Group Id</th>
                <th>Group Code</th>
                <th>FileName</th>
                <th>FileData</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
        </tfoot>
    </table>
    
 <script>
 $(document).ready(function() {
	    $('#example').DataTable();
	} );
 </script>

</body>
</html>
