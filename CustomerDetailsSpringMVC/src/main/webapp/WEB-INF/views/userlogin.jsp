
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

		<header style="text-align:center">
		  <h1 style="font-family:courier;">Details</h1>
		</header>
	
       
        
		<body style="background-color:powderblue;">
		
		<%
			if(session.getAttribute("user")==null)
			{
				
				response.sendRedirect("/CustomerDetailsSpringMVC/login");
				
			}
		
		
		%>
		
		
			
			<form action="post" ></form>
			<table align="center">
                <tr>
                    <td style="font-style: italic; color: blue;">Welcome ${username}</td>
                </tr>
                
                
                <tr>
                        <td></td>
                        <td align="center">
                            <a href = "/CustomerDetailsSpringMVC/customerdetails/1"><button type = "button"> Customer Details</button></a>
                        </td>
                    </tr>
            </table> 
			
			</form>
		</body>