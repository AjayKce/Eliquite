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

<%List<ResourceGroup> resourceGroups = (List<ResourceGroup>)request.getAttribute("resourceGroups"); %>
<br><br>
<table id="example" class="table table-bordered">
        <thead>
              <tr>
                <th>Id</th>
                <th>Student Id</th>
                <th>Group Code</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
        <%for(ResourceGroup temp:resourceGroups){ %>
            <tr>
                <td><%=temp.getId() %></td>
                <td><%=temp.getStudentId() %></td>
                <td><%=temp.getGroupCode() %></td>
            	<td>
					<form  action="/console/editResourceGroup" method="post" style="margin:0px;padding:0px;border:0px;">
						<input type="hidden" name="id" value="<%=temp.getId() %>" />
						<button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-edit"></span> Edit</button>
					</form>
				</td>
				<td>
					<form onsubmit="return confirm('Do you really want to delete ?')"  action="/console/deleteResourceGroup" method="post" style="margin:0px;padding:0px;border:0px;">
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
                <th>Student Id</th>
                <th>Group Code</th>
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
