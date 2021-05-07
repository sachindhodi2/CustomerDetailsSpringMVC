<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>
        
        <header style="text-align:center">
		  <h1 style="font-family:verdana;">Customer Details</h1>
		</header>

		<style>
.dropdown {
  position: absolute; right:5px;
  top: 123px;
  padding-right: 130px;
  display: inline-block;
}
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #F9F9F9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}
.dropdown-content a {
  color: black;
  text-decoration: none;
  display: block;
}
.dropdown-content a:hover {background-color: #F1F1F1}
.dropdown:hover .dropdown-content {
  display: block;
}
</style>
			        
        
        
           <body style="background-color:powderblue;">
           
           
           <%
			if(session.getAttribute("user")==null)
			{
				response.sendRedirect("/CustomerDetailsSpringMVC/login");
			}
		
		
		%>
			
			
			<div class="dropdown" >
		  <button class="dropbtn">Advance filter</button>
		  <div class="dropdown-content">
		    <a href="/CustomerDetailsSpringMVC/advancefilterdetail/startswith">starts with</a>
		    <a href="/CustomerDetailsSpringMVC/advancefilterdetail/inbetween">in between</a>
		    <a href="/CustomerDetailsSpringMVC/advancefilterdetail/endswith">ends with</a>
		    <a href="/CustomerDetailsSpringMVC/advancefilterdetail/wordsearch">word search</a>
		  </div>
		</div>
			
			
			
			<center>
			<form:form id="editForm" modelAttribute="customer" action="${pageContext.request.contextPath}/filterdetail/1" method="get"> 
			
			Search: <input type="text" name="keyword" id="keyword" size="50" value="${keyword}" placeholder="id,name,age,gender">
			<input type="submit" value="Search" />
			
			</form:form>
			</center>
			
			<br>
			
			<center>
			<form:form id="editForm" modelAttribute="customer" action="${pageContext.request.contextPath}/advancefilterdetails/1" method="get"> 
			
			Advance Search: <input type="text" name="keyword" id="keyword" size="50" value="${keyword}" placeholder="id,name,age,gender">
			<input type="submit" value="Search" />
			
			</form:form>
			</center>
			
			
			

        <table border="2" style="width:500px;margin-left:auto;margin-right:auto;" style="text-align:center" class="center">
				<tr>
					<th>id</th>
					<th><a href = "/CustomerDetailsSpringMVC/sort/name/1">Name</a></th>
					<th><a href = "/CustomerDetailsSpringMVC/sort/age/1">age</a></th>
					<th><a href = "/CustomerDetailsSpringMVC/sort/gender/1">gender</a></th>
				</tr>
				
				<!-- loop over and print our customers -->
				<c:forEach var="customer" items="${details}">
				
					<tr>
						<td> ${customer.id} </td>
						<td> ${customer.name} </td>
						<td> ${customer.age} </td>
						<td> ${customer.gender} </td>
						<td> <a href = "/CustomerDetailsSpringMVC/customereditdetails/${customer.id}"><button type = "button"> Edit</button></a></td>
						<td> <a href = "/CustomerDetailsSpringMVC/customerdeletedetails/${customer.id}"><button type = "button"> Delete</button></a></td>
	
					</tr>
				
				
				</c:forEach>
				
			<td> <a href = "/CustomerDetailsSpringMVC/adddetail"><button type = "button"> Add</button></a></td>
			
			
					
						
			</table>
			
			
			
			
			<br>
			
			
			
				
				<table align="center">
				<td>
				<c:forEach begin="${startpage}" end="${endpage}" var="p" >
			    <a href="/CustomerDetailsSpringMVC/advancefilterdetails/${p}">${p}</a>
			    </c:forEach>
			    </td>
                
           		 </table>
				
	
	  
	
	
	<br><br>
	
			<table align="center">
                <tr>
			<td> <a href = "/CustomerDetailsSpringMVC/customerreport/4"><button type = "button">Generate Report</button></a></td>
                </tr>
            </table> 
	
	

    </body>
    </html>