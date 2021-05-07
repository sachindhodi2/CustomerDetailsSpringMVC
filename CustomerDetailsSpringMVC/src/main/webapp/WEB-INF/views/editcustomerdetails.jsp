<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
    <html>
    		
    	<header style="text-align:center">
		  <h1 style="font-family:verdana;">Details</h1>
		</header>

       
        
		<body style="background-color:powderblue;">
		
		<%
			if(session.getAttribute("user")==null)
			{
				response.sendRedirect("/CustomerDetailsSpringMVC/login");
			}
		
		
		%>
		
		
        <h1>Update customer Details</h1>  
       <form:form id="editForm" modelAttribute="customer" action="${pageContext.request.contextPath}/saveeditdetail" method="get">    
        <table >    
        <tr>  
        <td></td>    
         <td><form:hidden  path="id"  /></td>  
         </tr>   
         <tr>    
          <td>Name : </td>   
          <td><form:input path="name"  /></td>  
         </tr>    
         <tr>    
          <td>Age :</td>    
          <td><form:input path="age" /></td>  
         </tr>   
         <tr>    
          <td>Gender :</td>    
          <td><form:select path="gender">  
        <form:option value="male" label="Male"/>  
        <form:option value="female" label="Female"/>  
        </form:select>
        </td>  
         </tr>   
           
         <tr>    
          <td> </td>    
          <td><input type="submit" value="Submit" /></td>    
         </tr>    
        </table>    
       </form:form> 
       </body>
       </html>