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

<%List<SetExam> setExams = (List<SetExam>)request.getAttribute("setExams"); %>
<br><br>
<table id="example" class="table table-bordered">
        <thead>
            <tr>
                <th>Id</th>
                <th>Admin Id</th>
                <th>Subject Id</th>
                <th>Exam Title</th>
                <th>FullMark</th>
                <th>Passmark</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
        <%for(SetExam temp:setExams){ %>
            <tr>
                <td><%=temp.getId() %></td>
                <td><%=temp.getAdminId() %></td>
                <td><%=temp.getSubjectId() %></td>
                <td><%=temp.getExamTitle() %></td>
                <td><%=temp.getFullmark() %></td>
                <td><%=temp.getPassmark() %></td>
            	<td>
					<form  action="/console/editSetExam" method="post" style="margin:0px;padding:0px;border:0px;">
						<input type="hidden" name="id" value="<%=temp.getId() %>" />
						<button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-edit"></span> Edit</button>
					</form>
				</td>
				<td>
					<form onsubmit="return confirm('Do you really want to delete ?')"  action="/console/deleteSetExam" method="post" style="margin:0px;padding:0px;border:0px;">
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
                <th>Admin Id</th>
                <th>Subject Id</th>
                <th>Exam Title</th>
                <th>FullMark</th>
                <th>Passmark</th>
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
